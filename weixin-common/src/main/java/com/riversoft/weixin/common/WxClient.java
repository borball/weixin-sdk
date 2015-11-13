package com.riversoft.weixin.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.riversoft.weixin.common.exception.WxError;
import com.riversoft.weixin.common.exception.WxRuntimeException;
import com.riversoft.weixin.common.util.JsonMapper;
import org.apache.commons.io.FileUtils;
import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by exizhai on 9/26/2015.
 */
public class WxClient {

    private static Logger logger = LoggerFactory.getLogger(WxClient.class);

    protected CloseableHttpClient httpClient;
    private AccessToken accessToken;
    private String clientId;
    private String clientSecret;
    private String tokenUrl;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public WxClient(String tokenUrl, String clientId, String clientSecret) {
        this.tokenUrl = tokenUrl;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        httpClient = HttpClients.createDefault();
    }

    public String get(String url) {
        return httpGet(appendAccessToken(url));
    }

    private String httpGet(String url) {
        HttpGet httpGet = new HttpGet(url);

        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
            StatusLine statusLine = response.getStatusLine();
            HttpEntity entity = response.getEntity();
            if (statusLine.getStatusCode() >= 300) {
                EntityUtils.consume(entity);
                throw new WxRuntimeException(statusLine.getStatusCode(), statusLine.getReasonPhrase());
            }
            String responseContent = entity == null ? null : EntityUtils.toString(entity, Consts.UTF_8);

            WxError wxError = WxError.fromJson(responseContent);
            if (wxError.getErrorCode() != 0) {
                throw new WxRuntimeException(wxError);
            }
            return responseContent;
        } catch (IOException ex) {
            logger.error("http get: {} failed.", url, ex);
            throw new WxRuntimeException(999, ex.getMessage());
        }
    }

    public File download(String url) {
        return httpDownload(appendAccessToken(url));
    }

    private File httpDownload(String url) {
        HttpGet httpGet = new HttpGet(url);

        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
            StatusLine statusLine = response.getStatusLine();
            HttpEntity entity = response.getEntity();
            if (statusLine.getStatusCode() >= 300) {
                EntityUtils.consume(entity);
                throw new WxRuntimeException(statusLine.getStatusCode(), statusLine.getReasonPhrase());
            } else {
                Header[] dispositionHeaders = response.getHeaders("Content-disposition");
                if (dispositionHeaders != null && dispositionHeaders.length > 0) {
                    String fileName = extractFileName(dispositionHeaders[0].getValue());
                    if (fileName == null || "".equals(fileName.trim())) {
                        logger.warn("Cannot get filename from Content-disposition");
                        throw new WxRuntimeException(999, "cannot get filename from Content-disposition");
                    } else {
                        InputStream inputStream = entity.getContent();
                        File tempFile = new File(FileUtils.getTempDirectory(), fileName);
                        FileUtils.copyInputStreamToFile(inputStream, tempFile);

                        return tempFile;
                    }
                } else {
                    String errors = entity == null ? null : EntityUtils.toString(entity, Consts.UTF_8);
                    logger.warn("download file : {} failed: {}", url, errors);
                    throw new WxRuntimeException(999, errors);
                }
            }
        } catch (IOException e) {
            logger.error("http download: {} failed.", url, e);
            throw new WxRuntimeException(999, e.getMessage());
        }
    }

    private String extractFileName(String headerValue) {
        String fileName = null;
        Pattern regex = Pattern.compile("(?<=filename=\").*?(?=\")");
        Matcher regexMatcher = regex.matcher(headerValue);
        if (regexMatcher.find()) {
            fileName = regexMatcher.group();
        }

        return fileName;
    }


    public String post(String url, String content) {
        return httpPost(appendAccessToken(url), content);
    }

    public String post(String url, InputStream inputStream, String extName) {
        File tempFile = new File(FileUtils.getTempDirectory(), UUID.randomUUID().toString() + "." + extName);

        try {
            FileUtils.copyInputStreamToFile(inputStream, tempFile);

            return httpPost(appendAccessToken(url), tempFile);
        } catch (IOException e) {
            logger.error("http post: {} failed", url, e);
            throw new WxRuntimeException(999, "Copy stream to file failed:" + e.getMessage());
        } finally {
            FileUtils.deleteQuietly(tempFile);
        }
    }

    private String httpPost(String url, String content) {
        HttpPost httpPost = new HttpPost(url);

        if (content != null) {
            StringEntity entity = new StringEntity(content, Consts.UTF_8);
            httpPost.setEntity(entity);
        }

        try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
            StatusLine statusLine = response.getStatusLine();
            HttpEntity entity = response.getEntity();
            if (statusLine.getStatusCode() >= 300) {
                EntityUtils.consume(entity);
                throw new WxRuntimeException(statusLine.getStatusCode(), statusLine.getReasonPhrase());
            }
            String responseContent = entity == null ? null : EntityUtils.toString(entity, Consts.UTF_8);

            WxError wxError = WxError.fromJson(responseContent);
            if (wxError.getErrorCode() != 0) {
                throw new WxRuntimeException(wxError);
            }
            return responseContent;
        } catch (IOException ex) {
            logger.error("http post: {} failed", url, ex);
            throw new WxRuntimeException(999, ex.getMessage());
        }
    }

    private String httpPost(String url, File file) {
        HttpPost httpPost = new HttpPost(url);

        if (file != null) {
            HttpEntity entity = MultipartEntityBuilder.create().addBinaryBody("media", file).build();
            httpPost.setEntity(entity);
            httpPost.setHeader("Content-Type", ContentType.MULTIPART_FORM_DATA.toString());
        }

        try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
            StatusLine statusLine = response.getStatusLine();
            HttpEntity entity = response.getEntity();
            if (statusLine.getStatusCode() >= 300) {
                EntityUtils.consume(entity);
                throw new WxRuntimeException(statusLine.getStatusCode(), statusLine.getReasonPhrase());
            }
            return entity == null ? null : EntityUtils.toString(entity, Consts.UTF_8);
        } catch (IOException ex) {
            logger.error("http post: {} failed", url, ex);
            throw new WxRuntimeException(999, ex.getMessage());
        }
    }

    private String appendAccessToken(String url) {
        if(accessToken == null || accessToken.expired()) {
            refreshToken();
        }
        String token = accessToken.getAccessToken();
        logger.debug("access token: {}", token);
        return url + (url.indexOf('?') == -1 ? "?access_token=" + token : "&access_token=" + token);
    }

    public synchronized void refreshToken() {
        logger.debug("requesting a new access token.");
        String content = httpGet(String.format(tokenUrl, clientId, clientSecret));
        AccessToken accessToken = AccessToken.fromJson(content);
        logger.debug("requested a new access token: {}", accessToken.accessToken);
        this.accessToken = accessToken;
    }

    public AccessToken getAccessToken() {
        if(accessToken == null) {
            refreshToken();
        }
        return accessToken;
    }

    public static class AccessToken {

        @JsonProperty("access_token")
        private String accessToken;

        @JsonProperty("expires_in")
        private long expiresIn;

        private long expiresTill;

        public static AccessToken fromJson(String json) {
            return JsonMapper.defaultMapper().fromJson(json, AccessToken.class);
        }

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }

        public long getExpiresIn() {
            return expiresIn;
        }

        public void setExpiresIn(long expiresIn) {
            this.expiresIn = expiresIn;
            this.expiresTill = System.currentTimeMillis() + (expiresIn * 1000) - 300000;
        }

        public long getExpiresTill() {
            return expiresTill;
        }

        public boolean expired() {
            return System.currentTimeMillis() > expiresTill;
        }
    }
}

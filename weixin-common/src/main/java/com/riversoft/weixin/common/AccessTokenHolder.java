package com.riversoft.weixin.common;

import com.riversoft.weixin.common.exception.WxError;
import com.riversoft.weixin.common.exception.WxRuntimeException;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * AccessToken holder
 * @borball on 8/14/2016.
 */
public abstract class AccessTokenHolder {

    private static Logger logger = LoggerFactory.getLogger(AccessTokenHolder.class);

    private String clientId;
    private String clientSecret;
    private String tokenUrl;
    private CloseableHttpClient httpClient;

    public AccessTokenHolder(String tokenUrl, String clientId, String clientSecret) {
        this.tokenUrl = tokenUrl;
        this.clientId = clientId;
        this.clientSecret = clientSecret;

        httpClient = HttpClients.createDefault();
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public void setTokenUrl(String tokenUrl) {
        this.tokenUrl = tokenUrl;
    }

    protected String fetchAccessToken() {
        logger.debug("[{}]:fetching a new access token.", clientId);

        String url = String.format(this.tokenUrl, this.clientId, this.clientSecret);
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
            logger.error("fetching a new token failed: {} failed.", url, ex);
            throw new WxRuntimeException(999, ex.getMessage());
        }
    }

    /**
     * 获取access token
     * @return
     */
    public abstract AccessToken getAccessToken();

    /**
     * 强制刷新
     */
    public abstract void refreshToken();

    /**
     * 强制设置token过期
     */
    public abstract void expireToken();
}

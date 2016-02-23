package com.riversoft.weixin.common;

import com.riversoft.weixin.common.exception.WxError;
import com.riversoft.weixin.common.exception.WxRuntimeException;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;

/**
 * Created by exizhai on 12/1/2015.
 */
public class WxSslClient {

    private static Logger logger = LoggerFactory.getLogger(WxSslClient.class);

    protected CloseableHttpClient httpClient;
    RequestConfig requestConfig;

    public WxSslClient(String certPath, String certPassword) {
        KeyStore keyStore = null;
        SSLContext sslcontext = null;
        try {
            keyStore = KeyStore.getInstance("PKCS12");
            FileInputStream inputStream = new FileInputStream(new File(certPath));
            keyStore.load(inputStream, certPassword.toCharArray());
            sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, certPassword.toCharArray()).build();
        } catch (Exception e) {
            logger.error("initializing WxHttpsClient failed.", e);
            throw new WxRuntimeException(999, e.getMessage());
        }

        // Allow TLSv1 protocol only
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[]{"TLSv1"}, null, SSLConnectionSocketFactory.getDefaultHostnameVerifier());

        httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).build();;

        requestConfig = RequestConfig.custom().setSocketTimeout(10000).setConnectTimeout(30000).setConnectionRequestTimeout(30000).build();

    }

    public String get(String url) {
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(requestConfig);

        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
            StatusLine statusLine = response.getStatusLine();
            HttpEntity entity = response.getEntity();
            if (statusLine.getStatusCode() >= 300) {
                EntityUtils.consume(entity);
                throw new WxRuntimeException(statusLine.getStatusCode(), statusLine.getReasonPhrase());
            }
            String responseContent = entity == null ? null : EntityUtils.toString(entity, Consts.UTF_8);
            return responseContent;
        } catch (IOException ex) {
            logger.error("http get: {} failed.", url, ex);
            throw new WxRuntimeException(999, ex.getMessage());
        }
    }

    public String post(String url, String content) {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(requestConfig);
        httpPost.addHeader("Content-Type", "text/xml");

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
            return responseContent;
        } catch (IOException ex) {
            logger.error("http post: {} failed", url, ex);
            throw new WxRuntimeException(999, ex.getMessage());
        }
    }
}

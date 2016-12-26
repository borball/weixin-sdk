package com.riversoft.weixin.qy.jsapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.riversoft.weixin.common.WxClient;
import com.riversoft.weixin.common.decrypt.AesException;
import com.riversoft.weixin.common.decrypt.SHA1;
import com.riversoft.weixin.common.exception.WxRuntimeException;
import com.riversoft.weixin.common.jsapi.JsAPISignature;
import com.riversoft.weixin.common.util.JsonMapper;
import com.riversoft.weixin.common.util.RandomStringGenerator;
import com.riversoft.weixin.qy.QyWxClientFactory;
import com.riversoft.weixin.qy.base.CorpSetting;
import com.riversoft.weixin.qy.base.WxEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 微信JSAPI
 * Created by exizhai on 1/27/2016.
 */
public class JsAPIs {

    private static Logger logger = LoggerFactory.getLogger(JsAPIs.class);

    private APITicket jsAPITicket;
    private APITicket jsAPIGroupTicket;

    private WxClient wxClient;

    public static JsAPIs defaultJsAPIs() {
        return with(CorpSetting.defaultSettings());
    }

    public static JsAPIs with(CorpSetting corpSetting) {
        JsAPIs jsAPIs = new JsAPIs();
        jsAPIs.setWxClient(QyWxClientFactory.getInstance().with(corpSetting));
        return jsAPIs;
    }

    public void setWxClient(WxClient wxClient) {
        this.wxClient = wxClient;
    }

    /**
     * 调用微信JS接口的临时票据
     */
    private synchronized void getJsAPITicket(){
        if(jsAPITicket == null || jsAPITicket.expired()) {//double check
            String url = WxEndpoint.get("url.jsapi.ticket.get");
            String response = wxClient.get(url);
            this.jsAPITicket = APITicket.fromJson(response);
        }
    }

    /**
     * 获取管理组临时票据
     */
    private synchronized void getJsAPIGroupTicket(){
        if(jsAPIGroupTicket == null || jsAPIGroupTicket.expired()) {//double check
            String url = WxEndpoint.get("url.jsapi.ticket.group.get");
            String response = wxClient.get(url);
            this.jsAPIGroupTicket = APITicket.fromJson(response);
        }
    }

    /**
     * 创建JsAPI签名
     * @param url
     * @return
     */
    public JsAPISignature createJsAPISignature(String url){
        if(jsAPITicket == null || jsAPITicket.expired()) {
            getJsAPITicket();
        }

        long timestamp = System.currentTimeMillis() / 1000;
        String nonce = RandomStringGenerator.getRandomStringByLength(16);
        String ticket = jsAPITicket.getTicket();

        try {
            String signature = SHA1.getSHA1("jsapi_ticket=" + ticket + "&noncestr=" + nonce + "&timestamp=" + timestamp + "&url=" + url);

            JsAPISignature jsAPISignature = new JsAPISignature();
            jsAPISignature.setAppId(wxClient.getClientId());
            jsAPISignature.setNonce(nonce);
            jsAPISignature.setTimestamp(timestamp);
            jsAPISignature.setSignature(signature);
            jsAPISignature.setUrl(url);
            return jsAPISignature;
        } catch (AesException e) {
            logger.error("createJsAPISignature failed", e);
            throw new WxRuntimeException(999, e.getMessage());
        }
    }

    /**
     * 创建企业号管理组权限签名
     * @param url
     * @return
     */
    public JsAPISignature createJsAPIGroupSignature(String url){
        if(jsAPIGroupTicket == null || jsAPIGroupTicket.expired()) {
            getJsAPIGroupTicket();
        }

        long timestamp = System.currentTimeMillis() / 1000;
        String nonce = RandomStringGenerator.getRandomStringByLength(16);
        String ticket = jsAPIGroupTicket.getTicket();

        try {
            String signature = SHA1.getSHA1("group_ticket=" + ticket + "&noncestr=" + nonce + "&timestamp=" + timestamp + "&url=" + url);

            JsAPISignature jsAPISignature = new JsAPISignature();
            jsAPISignature.setAppId(wxClient.getClientId());
            jsAPISignature.setNonce(nonce);
            jsAPISignature.setTimestamp(timestamp);
            jsAPISignature.setSignature(signature);
            jsAPISignature.setUrl(url);
            jsAPISignature.setGroupId(jsAPIGroupTicket.getGroupId());
            return jsAPISignature;
        } catch (AesException e) {
            logger.error("createJsAPIGroupSignature failed", e);
            throw new WxRuntimeException(999, e.getMessage());
        }
    }

    public static class APITicket {

        @JsonProperty("group_id")
        private String groupId;

        private String ticket;

        @JsonProperty("expires_in")
        private long expiresIn;

        private long expiresTill;

        public static APITicket fromJson(String json) {
            return JsonMapper.defaultMapper().fromJson(json, APITicket.class);
        }

        public String getGroupId() {
            return groupId;
        }

        public void setGroupId(String groupId) {
            this.groupId = groupId;
        }

        public String getTicket() {
            return ticket;
        }

        public void setTicket(String ticket) {
            this.ticket = ticket;
        }

        public long getExpiresIn() {
            return expiresIn;
        }

        public void setExpiresIn(long expiresIn) {
            this.expiresIn = expiresIn;
            this.expiresTill = System.currentTimeMillis() + (expiresIn * 1000) - 60000;
        }

        public long getExpiresTill() {
            return expiresTill;
        }

        public boolean expired() {
            return System.currentTimeMillis() > expiresTill;
        }
    }
}

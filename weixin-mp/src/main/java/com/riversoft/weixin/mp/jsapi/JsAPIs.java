package com.riversoft.weixin.mp.jsapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.riversoft.weixin.common.WxClient;
import com.riversoft.weixin.common.decrypt.AesException;
import com.riversoft.weixin.common.decrypt.SHA1;
import com.riversoft.weixin.common.exception.WxRuntimeException;
import com.riversoft.weixin.common.jsapi.JsAPISignature;
import com.riversoft.weixin.common.jsapi.WxCardAPISignature;
import com.riversoft.weixin.common.util.JsonMapper;
import com.riversoft.weixin.common.util.RandomStringGenerator;
import com.riversoft.weixin.mp.MpWxClientFactory;
import com.riversoft.weixin.mp.base.AppSetting;
import com.riversoft.weixin.mp.base.WxEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 微信JSAPI
 * Created by exizhai on 1/27/2016.
 */
public class JsAPIs {

    private static Logger logger = LoggerFactory.getLogger(JsAPIs.class);

    private APITicket jsAPITicket;
    private APITicket wxCardAPITicket;

    private WxClient wxClient;

    public static JsAPIs defaultJsAPIs() {
        return with(AppSetting.defaultSettings());
    }

    public static JsAPIs with(AppSetting appSetting) {
        JsAPIs jsAPIs = new JsAPIs();
        jsAPIs.setWxClient(MpWxClientFactory.getInstance().with(appSetting));
        return jsAPIs;
    }

    public void setWxClient(WxClient wxClient) {
        this.wxClient = wxClient;
    }

    private synchronized void getJsAPITicket(){
        if(jsAPITicket == null || jsAPITicket.expired()) {//double check
            String url = WxEndpoint.get("url.jsapi.ticket.get");
            String response = wxClient.get(url);
            this.jsAPITicket = APITicket.fromJson(response);
        }
    }


    private synchronized void getWxCardAPITicket(){
        if(wxCardAPITicket == null || wxCardAPITicket.expired()) {//double check
            String url = WxEndpoint.get("url.wxcard.jsapi.ticket.get");
            String response = wxClient.get(url);
            this.wxCardAPITicket = APITicket.fromJson(response);
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
     * 创建微信卡券JSAPI签名
     * @param wxCardAPISignature
     * @return
     */
    public WxCardAPISignature createWxCardJsAPISignature(WxCardAPISignature wxCardAPISignature){
        if(wxCardAPITicket == null || wxCardAPITicket.expired()) {
            getWxCardAPITicket();
        }

        long timestamp = System.currentTimeMillis() / 1000;
        String nonce = RandomStringGenerator.getRandomStringByLength(16);
        String ticket = wxCardAPITicket.getTicket();

        List<String> parameters = new ArrayList<>();
        if(wxCardAPISignature.isChooseCard()) {
            parameters.add(wxClient.getClientId());
        }

        parameters.add(ticket);
        parameters.add(wxCardAPISignature.getCardId());
        parameters.add(nonce);
        parameters.add(String.valueOf(timestamp));

        if(!(wxCardAPISignature.getCardType() == null || "".equals(wxCardAPISignature.getCardType()))) {
            parameters.add(wxCardAPISignature.getCardType());
        }
        if(!(wxCardAPISignature.getCode() == null || "".equals(wxCardAPISignature.getCode()))) {
            parameters.add(wxCardAPISignature.getCode());
        }
        if(!(wxCardAPISignature.getBalance() == null || "".equals(wxCardAPISignature.getBalance()))) {
            parameters.add(wxCardAPISignature.getBalance());
        }
        if(!(wxCardAPISignature.getOpenId() == null || "".equals(wxCardAPISignature.getOpenId()))) {
            parameters.add(wxCardAPISignature.getOpenId());
        }
        if(!(wxCardAPISignature.getLocationId() == null || "".equals(wxCardAPISignature.getLocationId()))) {
            parameters.add(wxCardAPISignature.getLocationId());
        }

        try {
            String signature = SHA1.getSHA1((String[])parameters.toArray());

            wxCardAPISignature.setNonce(nonce);
            wxCardAPISignature.setTimestamp(timestamp);
            wxCardAPISignature.setSignature(signature);
            return wxCardAPISignature;
        } catch (AesException e) {
            logger.error("createWxCardJsAPISignature failed", e);
            throw new WxRuntimeException(999, e.getMessage());
        }
    }

    public static class APITicket {

        private String ticket;

        @JsonProperty("expires_in")
        private long expiresIn;

        private long expiresTill;

        public static APITicket fromJson(String json) {
            return JsonMapper.defaultMapper().fromJson(json, APITicket.class);
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

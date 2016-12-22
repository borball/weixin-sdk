package com.riversoft.weixin.pay.redpack;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.riversoft.weixin.common.WxSslClient;
import com.riversoft.weixin.common.exception.WxRuntimeException;
import com.riversoft.weixin.common.util.JsonMapper;
import com.riversoft.weixin.common.util.XmlObjectMapper;
import com.riversoft.weixin.pay.PayWxClientFactory;
import com.riversoft.weixin.pay.base.PaySetting;
import com.riversoft.weixin.pay.base.WxEndpoint;
import com.riversoft.weixin.pay.redpack.bean.RedPackRequest;
import com.riversoft.weixin.pay.redpack.bean.RedPackResponse;
import com.riversoft.weixin.pay.redpack.bean.RedPackResult;
import com.riversoft.weixin.common.util.RandomStringGenerator;
import com.riversoft.weixin.pay.util.SignatureUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.SortedMap;

/**
 * Created by exizhai on 11/22/2015.
 */
public class RedPacks {

    private static Logger logger = LoggerFactory.getLogger(RedPacks.class);

    private PaySetting paySetting;

    public void setPaySetting(PaySetting paySetting) {
        this.paySetting = paySetting;
    }

    private WxSslClient wxSslClient;

    public static RedPacks defaultRedPacks() {
        return with(PaySetting.defaultSetting());
    }

    public static RedPacks with(PaySetting paySetting) {
        RedPacks redPacks = new RedPacks();
        redPacks.setPaySetting(paySetting);
        redPacks.setWxSslClient(PayWxClientFactory.getInstance().with(paySetting));
        return redPacks;
    }

    public void setWxSslClient(WxSslClient wxSslClient) {
        this.wxSslClient = wxSslClient;
    }

    public RedPackResponse sendSingle(RedPackRequest redPackRequest) {
        String url = WxEndpoint.get("url.pay.redpack.send.single");
        return send(url, redPackRequest);
    }

    public RedPackResponse sendGroup(RedPackRequest redPackRequest) {
        String url = WxEndpoint.get("url.pay.redpack.send.group");
        redPackRequest.setAmtType("ALL_RAND");
        return send(url, redPackRequest);
    }

    public RedPackResult query(String billNumber) {
        GetRedPackWrapper getRedPackWrapper = new GetRedPackWrapper();
        getRedPackWrapper.setBillNumber(billNumber);
        getRedPackWrapper.setBillType("MCHT");

        SortedMap<String, Object> redPackRequestMap = JsonMapper.defaultMapper().getMapper().convertValue(getRedPackWrapper, SortedMap.class);

        redPackRequestMap.put("appid", paySetting.getAppId());
        AppSettingMixin appSettingMixin = prepareAppSettingMixin(redPackRequestMap);

        getRedPackWrapper.setAppId(paySetting.getAppId());
        getRedPackWrapper.setAppSettingMixin(appSettingMixin);

        String url = WxEndpoint.get("url.pay.redpack.get");
        try {
            String xml = XmlObjectMapper.defaultMapper().toXml(getRedPackWrapper);
            logger.info("send query redpack request: {}", xml);
            String response = wxSslClient.post(url, xml);
            logger.info("got query response: {}", response);
            RedPackResultWrapper redPackResultWrapper = XmlObjectMapper.defaultMapper().fromXml(response, RedPackResultWrapper.class);
            return redPackResultWrapper.getRedPackResult();
        } catch (Exception e) {
            throw new WxRuntimeException(999, " redpack failed:" + e.getMessage());
        }

    }

    private RedPackResponse send(String url, RedPackRequest redPackRequest) {
        if(redPackRequest.getAppId() == null || "".equals(redPackRequest.getAppId())) {
            redPackRequest.setAppId(paySetting.getAppId());
        }
        SortedMap<String, Object> redPackRequestMap = JsonMapper.defaultMapper().getMapper().convertValue(redPackRequest, SortedMap.class);

        AppSettingMixin appSettingMixin = prepareAppSettingMixin(redPackRequestMap);

        RedPackRequestWrapper redPackRequestWrapper = new RedPackRequestWrapper();
        redPackRequestWrapper.setAppSettingMixin(appSettingMixin);
        redPackRequestWrapper.setRedPackRequest(redPackRequest);

        try {
            String xml = XmlObjectMapper.defaultMapper().toXml(redPackRequestWrapper);
            logger.info("send redpack request: {}", xml);
            String response = wxSslClient.post(url, xml);
            logger.info("got response: {}", response);

            RedPackResponseWrapper redPackResponseWrapper = XmlObjectMapper.defaultMapper().fromXml(response, RedPackResponseWrapper.class);
            return redPackResponseWrapper.getRedPackResponse();
        } catch (Exception e) {
            throw new WxRuntimeException(999, "send redpack failed:" + e.getMessage());
        }
    }

    private AppSettingMixin prepareAppSettingMixin(SortedMap<String, Object> generals) {
        AppSettingMixin appSettingMixin = new AppSettingMixin();
        String nonce = RandomStringGenerator.getRandomStringByLength(32);

        generals.put("nonce_str", nonce);
        generals.put("mch_id", paySetting.getMchId());

        appSettingMixin.setMchId(paySetting.getMchId());
        appSettingMixin.setNonce(nonce);
        appSettingMixin.setSign(SignatureUtil.sign(generals, paySetting.getKey()));

        return appSettingMixin;
    }

    @JacksonXmlRootElement(localName = "xml")
    class RedPackRequestWrapper {

        @JsonUnwrapped
        private RedPackRequest redPackRequest;

        @JsonUnwrapped
        private AppSettingMixin appSettingMixin;

        public RedPackRequest getRedPackRequest() {
            return redPackRequest;
        }

        public void setRedPackRequest(RedPackRequest redPackRequest) {
            this.redPackRequest = redPackRequest;
        }

        public AppSettingMixin getAppSettingMixin() {
            return appSettingMixin;
        }

        public void setAppSettingMixin(AppSettingMixin appSettingMixin) {
            this.appSettingMixin = appSettingMixin;
        }
    }

    @JacksonXmlRootElement(localName = "xml")
    public static class RedPackResponseWrapper {

        @JsonUnwrapped
        private RedPackResponse redPackResponse;

        @JsonProperty("wxappid")
        private String wxAppId;

        @JsonUnwrapped
        private AppSettingMixin appSettingMixin;

        public RedPackResponse getRedPackResponse() {
            return redPackResponse;
        }

        public void setRedPackResponse(RedPackResponse redPackResponse) {
            this.redPackResponse = redPackResponse;
        }

        public String getWxAppId() {
            return wxAppId;
        }

        public void setWxAppId(String wxAppId) {
            this.wxAppId = wxAppId;
        }

        public AppSettingMixin getAppSettingMixin() {
            return appSettingMixin;
        }

        public void setAppSettingMixin(AppSettingMixin appSettingMixin) {
            this.appSettingMixin = appSettingMixin;
        }
    }

    @JacksonXmlRootElement(localName = "xml")
    public static class GetRedPackWrapper {

        @JsonProperty("mch_billno")
        private String billNumber;

        @JsonProperty("bill_type")
        private String billType;

        @JsonUnwrapped
        private AppSettingMixin appSettingMixin;

        @JsonProperty("appid")
        private String appId;

        public String getBillNumber() {
            return billNumber;
        }

        public void setBillNumber(String billNumber) {
            this.billNumber = billNumber;
        }

        public String getBillType() {
            return billType;
        }

        public void setBillType(String billType) {
            this.billType = billType;
        }

        public String getAppId() {
            return appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }

        public AppSettingMixin getAppSettingMixin() {
            return appSettingMixin;
        }

        public void setAppSettingMixin(AppSettingMixin appSettingMixin) {
            this.appSettingMixin = appSettingMixin;
        }
    }

    public static class RedPackResultWrapper {

        @JsonUnwrapped
        private RedPackResult redPackResult;

        @JsonUnwrapped
        private AppSettingMixin appSettingMixin;

        public RedPackResult getRedPackResult() {
            return redPackResult;
        }

        public void setRedPackResult(RedPackResult redPackResult) {
            this.redPackResult = redPackResult;
        }

        public AppSettingMixin getAppSettingMixin() {
            return appSettingMixin;
        }

        public void setAppSettingMixin(AppSettingMixin appSettingMixin) {
            this.appSettingMixin = appSettingMixin;
        }
    }

    public static class AppSettingMixin {

        @JsonProperty("mch_id")
        private String mchId;

        @JsonProperty("nonce_str")
        private String nonce;

        private String sign;

        public String getMchId() {
            return mchId;
        }

        public void setMchId(String mchId) {
            this.mchId = mchId;
        }

        public String getNonce() {
            return nonce;
        }

        public void setNonce(String nonce) {
            this.nonce = nonce;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }
    }
}

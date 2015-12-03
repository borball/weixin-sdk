package com.riversoft.weixin.pay.transfer;

import com.fasterxml.jackson.annotation.JsonInclude;
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
import com.riversoft.weixin.pay.transfer.bean.TransferRequest;
import com.riversoft.weixin.pay.transfer.bean.TransferResponse;
import com.riversoft.weixin.pay.transfer.bean.TransferResult;
import com.riversoft.weixin.pay.util.RandomStringGenerator;
import com.riversoft.weixin.pay.util.Signature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.SortedMap;

/**
 * Created by exizhai on 12/2/2015.
 */
public class Transfers {

    private static Logger logger = LoggerFactory.getLogger(Transfers.class);

    private PaySetting paySetting;

    public void setPaySetting(PaySetting paySetting) {
        this.paySetting = paySetting;
    }

    private WxSslClient wxSslClient;

    public static Transfers defaultTransfers() {
        return with(PaySetting.defaultSetting());
    }

    public static Transfers with(PaySetting paySetting) {
        Transfers transfers = new Transfers();
        transfers.setPaySetting(paySetting);
        transfers.setWxSslClient(PayWxClientFactory.getInstance().with(paySetting));
        return transfers;
    }

    public void setWxSslClient(WxSslClient wxSslClient) {
        this.wxSslClient = wxSslClient;
    }

    public TransferResponse transfer(TransferRequest transferRequest) {
        TransferRequestWrapper transferRequestWrapper = new TransferRequestWrapper();
        transferRequestWrapper.setAppId(paySetting.getAppId());
        transferRequestWrapper.setMchId(paySetting.getMchId());
        transferRequestWrapper.setTransferRequest(transferRequest);

        String nonce = RandomStringGenerator.getRandomStringByLength(32);
        transferRequestWrapper.setNonce(nonce);

        SortedMap<String, Object> transferRequestMap = JsonMapper.defaultMapper().getMapper().convertValue(transferRequestWrapper, SortedMap.class);
        transferRequestWrapper.setSign(Signature.sign(transferRequestMap, paySetting.getKey()));

        String url = WxEndpoint.get("url.pay.transfer.do");
        try {
            String xml = XmlObjectMapper.defaultMapper().toXml(transferRequestWrapper);
            logger.info("transfer request: {}", xml);
            String response = wxSslClient.post(url, xml);
            logger.info("got response: {}", response);

            TransferResponseWrapper transferResponseWrapper = (TransferResponseWrapper)XmlObjectMapper.defaultMapper().fromXml(response, TransferResponseWrapper.class);
            return transferResponseWrapper.getTransferResponse();
        } catch (Exception e) {
            throw new WxRuntimeException(999, "transfer failed:" + e.getMessage());
        }
    }

    public TransferResult query(String partnerTradeNo) {
        QueryTransferResultRequestWrapper queryTransferResultRequestWrapper = new QueryTransferResultRequestWrapper();
        queryTransferResultRequestWrapper.setAppId(paySetting.getAppId());
        queryTransferResultRequestWrapper.setMchId(paySetting.getMchId());
        queryTransferResultRequestWrapper.setPartnerTradeNo(partnerTradeNo);
        String nonce = RandomStringGenerator.getRandomStringByLength(32);
        queryTransferResultRequestWrapper.setNonce(nonce);

        SortedMap<String, Object> transferRequestMap = JsonMapper.defaultMapper().getMapper().convertValue(queryTransferResultRequestWrapper, SortedMap.class);
        queryTransferResultRequestWrapper.setSign(Signature.sign(transferRequestMap, paySetting.getKey()));

        String url = WxEndpoint.get("url.pay.transfer.query");
        try {
            String xml = XmlObjectMapper.defaultMapper().toXml(queryTransferResultRequestWrapper);
            logger.info("transfer query: {}", xml);
            String response = wxSslClient.post(url, xml);
            logger.info("got response: {}", response);

            TransferResult transferResult = (TransferResult)XmlObjectMapper.defaultMapper().fromXml(response, TransferResult.class);
            return transferResult;
        } catch (Exception e) {
            throw new WxRuntimeException(999, "query transfer result failed:" + e.getMessage());
        }
    }

    @JacksonXmlRootElement(localName = "xml")
    public static class TransferRequestWrapper {

        @JsonUnwrapped
        private TransferRequest transferRequest;

        @JsonProperty("mch_appid")
        private String appId;

        @JsonProperty("mchid")
        private String mchId;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String sign;

        @JsonProperty("nonce_str")
        private String nonce;

        public TransferRequest getTransferRequest() {
            return transferRequest;
        }

        public void setTransferRequest(TransferRequest transferRequest) {
            this.transferRequest = transferRequest;
        }

        public String getAppId() {
            return appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }

        public String getMchId() {
            return mchId;
        }

        public void setMchId(String mchId) {
            this.mchId = mchId;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getNonce() {
            return nonce;
        }

        public void setNonce(String nonce) {
            this.nonce = nonce;
        }
    }

    @JacksonXmlRootElement(localName = "xml")
    public static class TransferResponseWrapper {

        @JsonUnwrapped
        private TransferResponse transferResponse;

        @JsonProperty("mch_appid")
        private String appId;

        @JsonProperty("mchid")
        private String mchId;

        public TransferResponse getTransferResponse() {
            return transferResponse;
        }

        public void setTransferResponse(TransferResponse transferResponse) {
            this.transferResponse = transferResponse;
        }

        public String getAppId() {
            return appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }

        public String getMchId() {
            return mchId;
        }

        public void setMchId(String mchId) {
            this.mchId = mchId;
        }
    }

    @JacksonXmlRootElement(localName = "xml")
    public static class QueryTransferResultRequestWrapper {

        @JsonProperty("partner_trade_no")
        private String partnerTradeNo;

        @JsonProperty("appid")
        private String appId;

        @JsonProperty("mch_id")
        private String mchId;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String sign;

        @JsonProperty("nonce_str")
        private String nonce;

        public String getPartnerTradeNo() {
            return partnerTradeNo;
        }

        public void setPartnerTradeNo(String partnerTradeNo) {
            this.partnerTradeNo = partnerTradeNo;
        }

        public String getAppId() {
            return appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }

        public String getMchId() {
            return mchId;
        }

        public void setMchId(String mchId) {
            this.mchId = mchId;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getNonce() {
            return nonce;
        }

        public void setNonce(String nonce) {
            this.nonce = nonce;
        }
    }

}

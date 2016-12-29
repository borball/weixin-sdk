package com.riversoft.weixin.pay.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.riversoft.weixin.common.WxSslClient;
import com.riversoft.weixin.common.exception.WxRuntimeException;
import com.riversoft.weixin.common.util.JsonMapper;
import com.riversoft.weixin.common.util.RandomStringGenerator;
import com.riversoft.weixin.common.util.XmlObjectMapper;
import com.riversoft.weixin.pay.PayWxClientFactory;
import com.riversoft.weixin.pay.base.BaseResponse;
import com.riversoft.weixin.pay.base.PaySetting;
import com.riversoft.weixin.pay.base.WxEndpoint;
import com.riversoft.weixin.pay.payment.bean.*;
import com.riversoft.weixin.pay.util.SignatureUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SortedMap;

/**
 * 支付相关
 * @borball on 5/15/2016.
 */
public class Payments {

    private static Logger logger = LoggerFactory.getLogger(Payments.class);

    private PaySetting paySetting;

    public void setPaySetting(PaySetting paySetting) {
        this.paySetting = paySetting;
    }

    private WxSslClient wxSslClient;

    public static Payments defaultOrders() {
        return with(PaySetting.defaultSetting());
    }

    public static Payments with(PaySetting paySetting) {
        Payments payments = new Payments();
        payments.setPaySetting(paySetting);
        payments.setWxSslClient(PayWxClientFactory.getInstance().with(paySetting));
        return payments;
    }

    public void setWxSslClient(WxSslClient wxSslClient) {
        this.wxSslClient = wxSslClient;
    }

    /**
     * 统一下单
     *
     * @param unifiedOrderRequest
     * @return
     */
    public UnifiedOrderResponse unifiedOrder(UnifiedOrderRequest unifiedOrderRequest) {
        UnifiedOrderRequestWrapper wrapper = new UnifiedOrderRequestWrapper();
        wrapper.setRequest(unifiedOrderRequest);
        setBaseSettings(wrapper);
        SortedMap<String, Object> unifiedOrderRequestMap = JsonMapper.nonEmptyMapper().getMapper().convertValue(wrapper, SortedMap.class);
        sign(wrapper, unifiedOrderRequestMap);

        String url = WxEndpoint.get("url.pay.payment.order.unified");
        try {
            String xml = XmlObjectMapper.nonEmptyMapper().toXml(wrapper);
            logger.info("支付 unified order request: {}", xml);
            String response = wxSslClient.post(url, xml);
            logger.info("支付 unified order response: {}", response);

            UnifiedOrderResponseWrapper responseWrapper = XmlObjectMapper.defaultMapper().fromXml(response, UnifiedOrderResponseWrapper.class);
            return responseWrapper.getResponse();
        } catch (Exception e) {
            throw new WxRuntimeException(999, "pre order failed:" + e.getMessage());
        }

    }

    /**
     * 查询订单状态
     * @param orderQueryRequest
     * @return
     */
    public OrderQueryResponse query(OrderQueryRequest orderQueryRequest) {
        OrderQueryRequestWrapper wrapper = new OrderQueryRequestWrapper();
        wrapper.setRequest(orderQueryRequest);
        setBaseSettings(wrapper);
        SortedMap<String, Object> queryRequestMap = JsonMapper.nonEmptyMapper().getMapper().convertValue(wrapper, SortedMap.class);
        sign(wrapper, queryRequestMap);

        String url = WxEndpoint.get("url.pay.payment.order.query");
        try {
            String xml = XmlObjectMapper.nonEmptyMapper().toXml(wrapper);
            logger.info("支付 query order request: {}", xml);
            String response = wxSslClient.post(url, xml);
            logger.info("支付 query order response: {}", response);

            OrderQueryResponseWrapper responseWrapper = XmlObjectMapper.defaultMapper().fromXml(response, OrderQueryResponseWrapper.class);
            return responseWrapper.getResponse();
        } catch (Exception e) {
            throw new WxRuntimeException(999, "query order failed:" + e.getMessage());
        }

    }

    /**
     * 关闭订单
     * @param tradeNumber
     * @return
     */
    public BaseResponse close(String tradeNumber) {
        OrderCloseRequestWrapper wrapper = new OrderCloseRequestWrapper();
        wrapper.setTradeNumber(tradeNumber);
        setBaseSettings(wrapper);

        SortedMap<String, Object> closeOrderRequestMap = JsonMapper.nonEmptyMapper().getMapper().convertValue(wrapper, SortedMap.class);
        sign(wrapper, closeOrderRequestMap);

        String url = WxEndpoint.get("url.pay.payment.order.close");
        try {
            String xml = XmlObjectMapper.nonEmptyMapper().toXml(wrapper);
            logger.info("支付 close order request: {}", xml);
            String response = wxSslClient.post(url, xml);
            logger.info("支付 close order response: {}", response);

            OrderCloseResponseWrapper responseWrapper = XmlObjectMapper.defaultMapper().fromXml(response, OrderCloseResponseWrapper.class);
            return responseWrapper.getResponse();
        } catch (Exception e) {
            throw new WxRuntimeException(999, "close order failed:" + e.getMessage());
        }

    }

    /**
     * check if sign is valid
     * @param notification
     * @return
     */
    public boolean checkSignature(PaymentNotification notification) {
        SortedMap<String, Object> notificationMap = JsonMapper.nonEmptyMapper().getMapper().convertValue(notification, SortedMap.class);
        notificationMap.remove("sign");
        return notification.getSign().equals(SignatureUtil.sign(notificationMap, paySetting.getKey()));
    }

    /**
     * 申请退款
     * @param refundRequest
     * @return
     */
    public RefundResponse refund(RefundRequest refundRequest) {
        RefundRequestWrapper wrapper = new RefundRequestWrapper();
        wrapper.setRequest(refundRequest);
        setBaseSettings(wrapper);

        SortedMap<String, Object> refundRequestMap = JsonMapper.nonEmptyMapper().getMapper().convertValue(wrapper, SortedMap.class);
        sign(wrapper, refundRequestMap);

        String url = WxEndpoint.get("url.pay.payment.refund.refund");
        try {
            String xml = XmlObjectMapper.nonEmptyMapper().toXml(wrapper);
            logger.info("支付 refund request: {}", xml);
            String response = wxSslClient.post(url, xml);
            logger.info("支付 refund response: {}", response);

            RefundResponseWrapper responseWrapper = XmlObjectMapper.defaultMapper().fromXml(response, RefundResponseWrapper.class);
            return responseWrapper.getResponse();
        } catch (Exception e) {
            throw new WxRuntimeException(999, "refund failed:" + e.getMessage());
        }
    }

    /**
     * 根据transactionId查询退款记录
     * @param transactionId
     * @return
     */
    public RefundQuery refundQueryByTransactionId(String transactionId) {
        RefundQueryRequest refundQueryRequest = new RefundQueryRequest();
        refundQueryRequest.setTransactionId(transactionId);
        return refundQuery(refundQueryRequest);
    }

    /**
     * 根据tradeNumber查询退款记录
     * @param tradeNumber
     * @return
     */
    public RefundQuery refundQueryByTradeNumber(String tradeNumber) {
        RefundQueryRequest refundQueryRequest = new RefundQueryRequest();
        refundQueryRequest.setTradeNumber(tradeNumber);
        return refundQuery(refundQueryRequest);
    }

    /**
     * 根据refundNumber查询退款记录
     * @param refundNumber
     * @return
     */
    public RefundQuery refundQueryByRefundNumber(String refundNumber) {
        RefundQueryRequest refundQueryRequest = new RefundQueryRequest();
        refundQueryRequest.setRefundNumber(refundNumber);
        return refundQuery(refundQueryRequest);
    }

    /**
     * 根据refundId查询退款记录
     * @param refundId
     * @return
     */
    public RefundQuery refundQueryByRefundId(String refundId) {
        RefundQueryRequest refundQueryRequest = new RefundQueryRequest();
        refundQueryRequest.setRefundId(refundId);
        return refundQuery(refundQueryRequest);
    }

    public RefundQuery refundQuery(RefundQueryRequest refundQueryRequest) {
        setBaseSettings(refundQueryRequest);

        SortedMap<String, Object> refundQueryRequestMap = JsonMapper.nonEmptyMapper().getMapper().convertValue(refundQueryRequest, SortedMap.class);
        sign(refundQueryRequest, refundQueryRequestMap);

        String url = WxEndpoint.get("url.pay.payment.refund.query");
        try {
            String xml = XmlObjectMapper.nonEmptyMapper().toXml(refundQueryRequest);
            logger.info("支付 refund query request: {}", xml);
            String response = wxSslClient.post(url, xml);
            logger.info("支付 refund query response: {}", response);

            RefundQueryWrapper refundQueryWrapper = XmlObjectMapper.defaultMapper().fromXml(response, RefundQueryWrapper.class);
            return refundQueryWrapper.getRefundQuery();
        } catch (Exception e) {
            throw new WxRuntimeException(999, "refund query failed:" + e.getMessage());
        }
    }

    /**
     * 获取所有订单
     * @param date
     * @return
     */
    public String downloadAllBill(Date date){
        return downloadBill(date, "ALL");
    }

    /**
     * 获取所有退款订单
     * @param date
     * @return
     */
    public String downloadRefundBill(Date date){
        return downloadBill(date, "REFUND");
    }

    /**
     * 获取所有退款订单
     * @param date
     * @return
     */
    public String downloadSuccessBill(Date date){
        return downloadBill(date, "SUCCESS");
    }

    private String downloadBill(Date date, String type){
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        BillRequest billRequest = new BillRequest();
        billRequest.setDate(dateFormat.format(date));
        billRequest.setType(type);
        setBaseSettings(billRequest);
        SortedMap<String, Object> billRequestMap = JsonMapper.nonEmptyMapper().getMapper().convertValue(billRequest, SortedMap.class);
        sign(billRequest, billRequestMap);

        String url = WxEndpoint.get("url.pay.payment.bill.download");
        try {
            String xml = XmlObjectMapper.nonEmptyMapper().toXml(billRequest);
            logger.info("支付 bill download request: {}", xml);
            String response = wxSslClient.post(url, xml);
            logger.info("支付 bill download response: {}", response);

            return response;
        } catch (Exception e) {
            throw new WxRuntimeException(999, "bill download failed:" + e.getMessage());
        }
    }

    private void setBaseSettings(BaseSettings wrapper) {
        wrapper.setAppId(paySetting.getAppId());
        wrapper.setMchId(paySetting.getMchId());
    }

    private void sign(BaseSettings wrapper, SortedMap<String, Object> generals) {
        String nonce = RandomStringGenerator.getRandomStringByLength(32);
        generals.put("nonce_str", nonce);
        generals.put("mch_id", paySetting.getMchId());

        wrapper.setNonce(nonce);
        wrapper.setSign(SignatureUtil.sign(generals, paySetting.getKey()));
    }

    @JacksonXmlRootElement(localName = "xml")
    public static class UnifiedOrderRequestWrapper extends BaseSettings {
        @JsonUnwrapped
        private UnifiedOrderRequest request;

        public void setRequest(UnifiedOrderRequest request) {
            this.request = request;
        }

        public UnifiedOrderRequest getRequest() {
            return request;
        }
    }

    public static class UnifiedOrderResponseWrapper extends BaseSettings {
        @JsonUnwrapped
        private UnifiedOrderResponse response;

        public UnifiedOrderResponse getResponse() {
            return response;
        }

        public void setResponse(UnifiedOrderResponse response) {
            this.response = response;
        }
    }

    @JacksonXmlRootElement(localName = "xml")
    public static class OrderQueryRequestWrapper extends BaseSettings {
        @JsonUnwrapped
        private OrderQueryRequest request;

        public void setRequest(OrderQueryRequest request) {
            this.request = request;
        }

        public OrderQueryRequest getRequest() {
            return request;
        }
    }

    public static class OrderQueryResponseWrapper extends BaseSettings {
        @JsonUnwrapped
        private OrderQueryResponse response;

        public OrderQueryResponse getResponse() {
            return response;
        }

        public void setResponse(OrderQueryResponse response) {
            this.response = response;
        }
    }

    public static class OrderCloseRequestWrapper extends BaseSettings {

        @JsonProperty("out_trade_no")
        private String tradeNumber;

        public String getTradeNumber() {
            return tradeNumber;
        }

        public void setTradeNumber(String tradeNumber) {
            this.tradeNumber = tradeNumber;
        }
    }

    public static class OrderCloseResponseWrapper extends BaseSettings {

        @JsonUnwrapped
        private BaseResponse response;

        public BaseResponse getResponse() {
            return response;
        }

        public void setResponse(BaseResponse response) {
            this.response = response;
        }
    }

    public static class RefundRequestWrapper extends BaseSettings {

        @JsonUnwrapped
        private RefundRequest request;

        public RefundRequest getRequest() {
            return request;
        }

        public void setRequest(RefundRequest request) {
            this.request = request;
        }
    }

    public static class RefundResponseWrapper extends BaseSettings {

        @JsonUnwrapped
        private RefundResponse response;

        public RefundResponse getResponse() {
            return response;
        }

        public void setResponse(RefundResponse response) {
            this.response = response;
        }
    }

    public static class RefundQueryRequest extends BaseSettings {

        @JsonProperty("transaction_id")
        private String transactionId;

        @JsonProperty("out_trade_no")
        private String tradeNumber;

        @JsonProperty("out_refund_no")
        private String refundNumber;

        @JsonProperty("refund_id")
        private String refundId;

        public String getTransactionId() {
            return transactionId;
        }

        public void setTransactionId(String transactionId) {
            this.transactionId = transactionId;
        }

        public String getTradeNumber() {
            return tradeNumber;
        }

        public void setTradeNumber(String tradeNumber) {
            this.tradeNumber = tradeNumber;
        }

        public String getRefundNumber() {
            return refundNumber;
        }

        public void setRefundNumber(String refundNumber) {
            this.refundNumber = refundNumber;
        }

        public String getRefundId() {
            return refundId;
        }

        public void setRefundId(String refundId) {
            this.refundId = refundId;
        }
    }

    public static class RefundQueryWrapper extends BaseSettings {

        @JsonUnwrapped
        private RefundQuery refundQuery;

        public RefundQuery getRefundQuery() {
            return refundQuery;
        }

        public void setRefundQuery(RefundQuery refundQuery) {
            this.refundQuery = refundQuery;
        }
    }

    public static class BillRequest extends BaseSettings {

        @JsonProperty("bill_date")
        private String date;

        @JsonProperty("bill_type")
        private String type;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    public static class BaseSettings {

        @JsonProperty("appid")
        private String appId;

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

        public String getAppId() {
            return appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }
    }

}

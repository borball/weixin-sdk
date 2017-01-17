package com.riversoft.weixin.pay.payment;

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
import com.riversoft.weixin.pay.payment.wrapper.*;
import com.riversoft.weixin.pay.util.SignatureUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SortedMap;

/**
 * 支付相关: 小程序或者公众号
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
    public com.riversoft.weixin.pay.payment.bean.OrderQueryResponse query(com.riversoft.weixin.pay.payment.bean.OrderQueryRequest orderQueryRequest) {
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
        RefundQueryRequestWrapper refundQueryRequestWrapper = new RefundQueryRequestWrapper();
        refundQueryRequestWrapper.setTransactionId(transactionId);
        return refundQuery(refundQueryRequestWrapper);
    }

    /**
     * 根据tradeNumber查询退款记录
     * @param tradeNumber
     * @return
     */
    public RefundQuery refundQueryByTradeNumber(String tradeNumber) {
        RefundQueryRequestWrapper refundQueryRequestWrapper = new RefundQueryRequestWrapper();
        refundQueryRequestWrapper.setTradeNumber(tradeNumber);
        return refundQuery(refundQueryRequestWrapper);
    }

    /**
     * 根据refundNumber查询退款记录
     * @param refundNumber
     * @return
     */
    public RefundQuery refundQueryByRefundNumber(String refundNumber) {
        RefundQueryRequestWrapper refundQueryRequestWrapper = new RefundQueryRequestWrapper();
        refundQueryRequestWrapper.setRefundNumber(refundNumber);
        return refundQuery(refundQueryRequestWrapper);
    }

    /**
     * 根据refundId查询退款记录
     * @param refundId
     * @return
     */
    public RefundQuery refundQueryByRefundId(String refundId) {
        RefundQueryRequestWrapper refundQueryRequestWrapper = new RefundQueryRequestWrapper();
        refundQueryRequestWrapper.setRefundId(refundId);
        return refundQuery(refundQueryRequestWrapper);
    }

    public RefundQuery refundQuery(RefundQueryRequestWrapper refundQueryRequestWrapper) {
        setBaseSettings(refundQueryRequestWrapper);

        SortedMap<String, Object> refundQueryRequestMap = JsonMapper.nonEmptyMapper().getMapper().convertValue(refundQueryRequestWrapper, SortedMap.class);
        sign(refundQueryRequestWrapper, refundQueryRequestMap);

        String url = WxEndpoint.get("url.pay.payment.refund.query");
        try {
            String xml = XmlObjectMapper.nonEmptyMapper().toXml(refundQueryRequestWrapper);
            logger.info("支付 refund query request: {}", xml);
            String response = wxSslClient.post(url, xml);
            logger.info("支付 refund query response: {}", response);

            RefundQueryWrapper refundQueryWrapper = XmlObjectMapper.defaultMapper().fromXml(response, RefundQueryWrapper.class);
            refundQueryWrapper.ready();
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
        BillRequestWrapper billRequestWrapper = new BillRequestWrapper();
        billRequestWrapper.setDate(dateFormat.format(date));
        billRequestWrapper.setType(type);
        setBaseSettings(billRequestWrapper);
        SortedMap<String, Object> billRequestMap = JsonMapper.nonEmptyMapper().getMapper().convertValue(billRequestWrapper, SortedMap.class);
        sign(billRequestWrapper, billRequestMap);

        String url = WxEndpoint.get("url.pay.payment.bill.download");
        try {
            String xml = XmlObjectMapper.nonEmptyMapper().toXml(billRequestWrapper);
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

}

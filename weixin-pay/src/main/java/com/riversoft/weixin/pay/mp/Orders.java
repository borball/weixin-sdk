package com.riversoft.weixin.pay.mp;

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
import com.riversoft.weixin.pay.mp.bean.OrderQueryRequest;
import com.riversoft.weixin.pay.mp.bean.OrderQueryResponse;
import com.riversoft.weixin.pay.mp.bean.UnifiedOrderRequest;
import com.riversoft.weixin.pay.mp.bean.UnifiedOrderResponse;
import com.riversoft.weixin.pay.util.Signature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.SortedMap;

/**
 * @borball on 5/15/2016.
 */
public class Orders {

    private static Logger logger = LoggerFactory.getLogger(Orders.class);

    private PaySetting paySetting;

    public void setPaySetting(PaySetting paySetting) {
        this.paySetting = paySetting;
    }

    private WxSslClient wxSslClient;

    public static Orders defaultOrders() {
        return with(PaySetting.defaultSetting());
    }

    public static Orders with(PaySetting paySetting) {
        Orders orders = new Orders();
        orders.setPaySetting(paySetting);
        orders.setWxSslClient(PayWxClientFactory.getInstance().with(paySetting));
        return orders;
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

        String url = WxEndpoint.get("url.pay.mp.order.unified");
        try {
            String xml = XmlObjectMapper.defaultMapper().toXml(wrapper);
            logger.info("公众号支付 unified order request: {}", xml);
            String response = wxSslClient.post(url, xml);
            logger.info("公众号支付 unified order response: {}", response);

            UnifiedOrderResponseWrapper responseWrapper = (UnifiedOrderResponseWrapper) XmlObjectMapper.defaultMapper().fromXml(response, UnifiedOrderResponseWrapper.class);
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

        String url = WxEndpoint.get("url.pay.mp.order.query");
        try {
            String xml = XmlObjectMapper.defaultMapper().toXml(wrapper);
            logger.info("公众号支付 query order request: {}", xml);
            String response = wxSslClient.post(url, xml);
            logger.info("公众号支付 query order response: {}", response);

            OrderQueryResponseWrapper responseWrapper = (OrderQueryResponseWrapper) XmlObjectMapper.defaultMapper().fromXml(response, OrderQueryResponseWrapper.class);
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

        String url = WxEndpoint.get("url.pay.mp.order.close");
        try {
            String xml = XmlObjectMapper.defaultMapper().toXml(wrapper);
            logger.info("公众号支付 close order request: {}", xml);
            String response = wxSslClient.post(url, xml);
            logger.info("公众号支付 close order response: {}", response);

            OrderCloseResponseWrapper responseWrapper = (OrderCloseResponseWrapper) XmlObjectMapper.defaultMapper().fromXml(response, OrderCloseResponseWrapper.class);
            return responseWrapper.getResponse();
        } catch (Exception e) {
            throw new WxRuntimeException(999, "close order failed:" + e.getMessage());
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
        wrapper.setSign(Signature.sign(generals, paySetting.getKey()));
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

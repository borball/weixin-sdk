package com.riversoft.weixin.pay.payment.wrapper;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.riversoft.weixin.pay.payment.bean.OrderQueryRequest;

/**
 * @borball on 1/13/2017.
 */
@JacksonXmlRootElement(localName = "xml")
public class OrderQueryRequestWrapper extends BaseSettings {

    @JsonUnwrapped
    private OrderQueryRequest request;

    public void setRequest(OrderQueryRequest request) {
        this.request = request;
    }

    public OrderQueryRequest getRequest() {
        return request;
    }
}

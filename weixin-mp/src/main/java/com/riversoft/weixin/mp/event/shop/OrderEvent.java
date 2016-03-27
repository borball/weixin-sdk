package com.riversoft.weixin.mp.event.shop;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.riversoft.weixin.common.event.EventRequest;

/**
 * @borball on 3/17/2016.
 */
public class OrderEvent extends EventRequest {

    @JsonProperty("OrderId")
    @JacksonXmlCData
    private String orderId;

    @JsonProperty("OrderStatus")
    private int orderStatus;

    @JsonProperty("ProductId")
    @JacksonXmlCData
    private String productId;

    @JsonProperty("SkuInfo")
    @JacksonXmlCData
    private String skuInfo;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getSkuInfo() {
        return skuInfo;
    }

    public void setSkuInfo(String skuInfo) {
        this.skuInfo = skuInfo;
    }
}

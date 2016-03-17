package com.riversoft.weixin.mp.shop;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.riversoft.weixin.common.util.DateDeserializer;
import com.riversoft.weixin.common.util.DateSerializer;

import java.util.Date;

/**
 * @borball on 3/17/2016.
 */
public class Order {

    @JsonProperty("order_id")
    private String id;

    @JsonProperty("order_status")
    private int status;

    @JsonProperty("order_total_price")
    private long totalPrice;

    @JsonProperty("order_create_time")
    @JsonSerialize(using = DateSerializer.class)
    @JsonDeserialize(using = DateDeserializer.class)
    private Date created;

    @JsonProperty("order_express_price")
    private long expressPrice;

    @JsonProperty("buyer_openid")
    private String buyerOpenId;

    @JsonProperty("buyer_nick")
    private String buyerNickName;

    @JsonProperty("receiver_name")
    private String receiverName;

    @JsonProperty("receiver_province")
    private String receiverProvince;

    @JsonProperty("receiver_city")
    private String receiverCity;

    @JsonProperty("receiver_zone")
    private String receiverZone;

    @JsonProperty("receiver_address")
    private String receiverAddress;

    @JsonProperty("receiver_mobile")
    private String receiverMobile;

    @JsonProperty("receiver_phone")
    private String receiverPhone;

    @JsonProperty("product_id")
    private String productId;

    @JsonProperty("product_name")
    private String productName;

    @JsonProperty("product_price")
    private long productPrice;

    @JsonProperty("product_sku")
    private String productSku;

    @JsonProperty("product_count")
    private int productCount;

    @JsonProperty("product_img")
    private String productImage;

    @JsonProperty("delivery_id")
    private String deliveryId;

    @JsonProperty("delivery_company")
    private String deliveryCompany;

    @JsonProperty("trans_id")
    private String transactionId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public long getExpressPrice() {
        return expressPrice;
    }

    public void setExpressPrice(long expressPrice) {
        this.expressPrice = expressPrice;
    }

    public String getBuyerOpenId() {
        return buyerOpenId;
    }

    public void setBuyerOpenId(String buyerOpenId) {
        this.buyerOpenId = buyerOpenId;
    }

    public String getBuyerNickName() {
        return buyerNickName;
    }

    public void setBuyerNickName(String buyerNickName) {
        this.buyerNickName = buyerNickName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverProvince() {
        return receiverProvince;
    }

    public void setReceiverProvince(String receiverProvince) {
        this.receiverProvince = receiverProvince;
    }

    public String getReceiverCity() {
        return receiverCity;
    }

    public void setReceiverCity(String receiverCity) {
        this.receiverCity = receiverCity;
    }

    public String getReceiverZone() {
        return receiverZone;
    }

    public void setReceiverZone(String receiverZone) {
        this.receiverZone = receiverZone;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public String getReceiverMobile() {
        return receiverMobile;
    }

    public void setReceiverMobile(String receiverMobile) {
        this.receiverMobile = receiverMobile;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public long getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(long productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductSku() {
        return productSku;
    }

    public void setProductSku(String productSku) {
        this.productSku = productSku;
    }

    public int getProductCount() {
        return productCount;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(String deliveryId) {
        this.deliveryId = deliveryId;
    }

    public String getDeliveryCompany() {
        return deliveryCompany;
    }

    public void setDeliveryCompany(String deliveryCompany) {
        this.deliveryCompany = deliveryCompany;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
}

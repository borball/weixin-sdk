package com.riversoft.weixin.pay.mp.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.riversoft.weixin.common.util.DateDeserializer;
import com.riversoft.weixin.pay.base.BaseResponse;

import java.util.Date;

/**
 * @borball on 5/15/2016.
 */
public class PaymentNotification extends BaseResponse {

    @JsonProperty("appid")
    private String appId;

    @JsonProperty("mch_id")
    private String mchId;

    @JsonProperty("nonce_str")
    private String nonce;

    private String sign;

    @JsonProperty("device_info")
    private String deviceInfo;

    @JsonProperty("openid")
    private String openId;

    @JsonProperty("is_subscribe")
    private String isSubscribed;

    @JsonIgnore
    private boolean subscribed;

    @JsonProperty("trade_type")
    private String tradeType;

    @JsonProperty("trade_state")
    private String tradeState;

    @JsonProperty("bank_type")
    private String bankType;

    @JsonProperty("total_fee")
    private int totalFee;

    @JsonProperty("settlement_total_fee")
    private int settlementTotalFee;

    @JsonProperty("fee_type")
    private String feeType;

    @JsonProperty("cash_fee")
    private int cashFee;

    @JsonProperty("cash_fee_type")
    private String caseFeeType;

    @JsonProperty("coupon_fee")
    private int couponFee;

    @JsonProperty("coupon_count")
    private int couponFeeCount;

    @JsonProperty("transaction_id")
    private String transactionId;

    @JsonProperty("out_trade_no")
    private String tradeNumber;

    private String attach;

    @JsonProperty("time_end")
    @JsonDeserialize(using = DateDeserializer.class)
    private Date timeEnd;

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

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getIsSubscribed() {
        return isSubscribed;
    }

    public void setIsSubscribed(String isSubscribed) {
        this.isSubscribed = isSubscribed;
        this.subscribed = "Y".equalsIgnoreCase(isSubscribed);
    }

    public boolean subscribed(){
        return this.subscribed;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getTradeState() {
        return tradeState;
    }

    public void setTradeState(String tradeState) {
        this.tradeState = tradeState;
    }

    public String getBankType() {
        return bankType;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType;
    }

    public int getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(int totalFee) {
        this.totalFee = totalFee;
    }

    public int getSettlementTotalFee() {
        return settlementTotalFee;
    }

    public void setSettlementTotalFee(int settlementTotalFee) {
        this.settlementTotalFee = settlementTotalFee;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public int getCashFee() {
        return cashFee;
    }

    public void setCashFee(int cashFee) {
        this.cashFee = cashFee;
    }

    public String getCaseFeeType() {
        return caseFeeType;
    }

    public void setCaseFeeType(String caseFeeType) {
        this.caseFeeType = caseFeeType;
    }

    public int getCouponFee() {
        return couponFee;
    }

    public void setCouponFee(int couponFee) {
        this.couponFee = couponFee;
    }

    public int getCouponFeeCount() {
        return couponFeeCount;
    }

    public void setCouponFeeCount(int couponFeeCount) {
        this.couponFeeCount = couponFeeCount;
    }

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

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public Date getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(Date timeEnd) {
        this.timeEnd = timeEnd;
    }
}

package com.riversoft.weixin.pay.payment.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.riversoft.weixin.common.util.DateDeserializer;
import com.riversoft.weixin.pay.base.BaseResponse;

import java.util.Date;

/**
 * @borball on 5/15/2016.
 */
public class OrderQueryResponse extends BaseResponse {

    @JsonProperty("device_info")
    private String deviceInfo;

    @JsonProperty("openid")
    private String openId;

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

    @JsonProperty("trade_state_desc")
    private String tradeStateDesc;

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

    public boolean isSubscribed() {
        return this.subscribed;
    }

    public void setSubscribed(String subscribed) {
        this.subscribed = "Y".equalsIgnoreCase(subscribed);
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

    public String getTradeStateDesc() {
        return tradeStateDesc;
    }

    public void setTradeStateDesc(String tradeStateDesc) {
        this.tradeStateDesc = tradeStateDesc;
    }
}

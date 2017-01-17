package com.riversoft.weixin.pay.payment.wrapper;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @borball on 1/13/2017.
 */
public class RefundQueryRequestWrapper extends BaseSettings {

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

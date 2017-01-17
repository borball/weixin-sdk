package com.riversoft.weixin.pay.payment.wrapper;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @borball on 1/13/2017.
 */
public class OrderCloseRequestWrapper extends BaseSettings {

    @JsonProperty("out_trade_no")
    private String tradeNumber;

    public String getTradeNumber() {
        return tradeNumber;
    }

    public void setTradeNumber(String tradeNumber) {
        this.tradeNumber = tradeNumber;
    }
}

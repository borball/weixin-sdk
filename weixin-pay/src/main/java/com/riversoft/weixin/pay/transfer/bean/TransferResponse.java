package com.riversoft.weixin.pay.transfer.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.riversoft.weixin.pay.base.BaseResponse;

/**
 * Created by exizhai on 12/2/2015.
 */
public class TransferResponse extends BaseResponse {

    @JsonProperty("device_info")
    private String deviceInfo;

    @JsonProperty("partner_trade_no")
    private String partnerTradeNo;

    @JsonProperty("payment_no")
    private String paymentNo;

    @JsonProperty("payment_time")
    private String paymentTime;

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public String getPartnerTradeNo() {
        return partnerTradeNo;
    }

    public void setPartnerTradeNo(String partnerTradeNo) {
        this.partnerTradeNo = partnerTradeNo;
    }

    public String getPaymentNo() {
        return paymentNo;
    }

    public void setPaymentNo(String paymentNo) {
        this.paymentNo = paymentNo;
    }

    public String getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(String paymentTime) {
        this.paymentTime = paymentTime;
    }
}

package com.riversoft.weixin.pay.mp.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.riversoft.weixin.pay.base.BaseResponse;

/**
 * @borball on 5/15/2016.
 */
public class UnifiedOrderResponse extends BaseResponse {

    @JsonProperty("device_info")
    private String deviceInfo;

    @JsonProperty("trade_type")
    private String tradeType;

    @JsonProperty("prepay_id")
    private String prepayId;

    @JsonProperty("code_url")
    private String codeUrl;

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }

    public String getCodeUrl() {
        return codeUrl;
    }

    public void setCodeUrl(String codeUrl) {
        this.codeUrl = codeUrl;
    }
}

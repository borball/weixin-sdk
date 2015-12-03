package com.riversoft.weixin.pay.transfer.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by exizhai on 12/2/2015.
 */
public class TransferRequest {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("device_info")
    private String deviceInfo;

    @JsonProperty("partner_trade_no")
    private String partnerTradeNo;

    @JsonProperty("openid")
    private String openId;

    @JsonProperty("check_name")
    private String checkName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("re_user_name")
    private String userName;

    private int amount;

    private String desc;

    @JsonProperty("spbill_create_ip")
    private String clientIp;

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

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getCheckName() {
        return checkName;
    }

    public void setCheckName(String checkName) {
        this.checkName = checkName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }
}

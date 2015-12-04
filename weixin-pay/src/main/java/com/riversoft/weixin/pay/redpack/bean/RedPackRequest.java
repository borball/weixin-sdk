package com.riversoft.weixin.pay.redpack.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Created by exizhai on 11/22/2015.
 */
public class RedPackRequest {

    @JsonProperty("wxappid")
    private String appId;

    @JsonProperty("send_name")
    private String sendName;

    @JsonProperty("mch_billno")
    private String billNumber;

    @JsonProperty("re_openid")
    private String openId;

    @JsonProperty("total_amount")
    private int amount;

    @JsonProperty("total_num")
    private int number;

    private String wishing;

    @JsonProperty("act_name")
    private String activityName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("client_ip")
    private String clientIp;

    private String remark;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("amt_type")
    private String amtType;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(String billNumber) {
        this.billNumber = billNumber;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getWishing() {
        return wishing;
    }

    public void setWishing(String wishing) {
        this.wishing = wishing;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSendName() {
        return sendName;
    }

    public void setSendName(String sendName) {
        this.sendName = sendName;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getAmtType() {
        return amtType;
    }

    public void setAmtType(String amtType) {
        this.amtType = amtType;
    }
}

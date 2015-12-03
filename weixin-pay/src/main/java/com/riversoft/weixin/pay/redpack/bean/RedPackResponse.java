package com.riversoft.weixin.pay.redpack.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * Created by exizhai on 11/22/2015.
 */
public class RedPackResponse {


    @JsonProperty("return_code")
    private String returnCode;

    @JsonProperty("return_msg")
    private String returnMessage;

    private String sign;

    @JsonProperty("result_code")
    private String resultCode;

    @JsonProperty("err_code")
    private String errorCode;

    @JsonProperty("err_code_des")
    private String errorCodeDesc;

    @JsonProperty("mch_billno")
    private String billNumber;

    @JsonProperty("re_openid")
    private String openId;

    @JsonProperty("total_amount")
    private int amount;

    @JsonProperty("total_num")
    private int number;

    @JsonProperty("send_time")
    private Date sendTime;

    @JsonProperty("send_listid")
    private String sendListId;

    public boolean success() {
        return "SUCCESS".equals(returnCode) && "SUCCESS".equals(resultCode);
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorCodeDesc() {
        return errorCodeDesc;
    }

    public void setErrorCodeDesc(String errorCodeDesc) {
        this.errorCodeDesc = errorCodeDesc;
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

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getSendListId() {
        return sendListId;
    }

    public void setSendListId(String sendListId) {
        this.sendListId = sendListId;
    }
}

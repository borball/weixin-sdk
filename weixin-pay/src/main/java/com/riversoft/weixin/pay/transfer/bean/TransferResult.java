package com.riversoft.weixin.pay.transfer.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * Created by exizhai on 12/2/2015.
 */
public class TransferResult {

    @JsonProperty("return_code")
    private String returnCode;

    @JsonProperty("return_msg")
    private String returnMessage;

    @JsonProperty("result_code")
    private String resultCode;

    @JsonProperty("err_code")
    private String errorCode;

    @JsonProperty("err_code_des")
    private String errorCodeDesc;

    @JsonProperty("partner_trade_no")
    private String partnerTradeNo;

    @JsonProperty("detail_id")
    private String detailId;

    private String status;

    private String reason;

    @JsonProperty("openid")
    private String openId;

    @JsonProperty("transfer_name")
    private String transferName;

    @JsonProperty("payment_amount")
    private int amount;

    @JsonProperty("transfer_time")
    private Date transferTime;

    private String desc;

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

    public String getPartnerTradeNo() {
        return partnerTradeNo;
    }

    public void setPartnerTradeNo(String partnerTradeNo) {
        this.partnerTradeNo = partnerTradeNo;
    }

    public String getDetailId() {
        return detailId;
    }

    public void setDetailId(String detailId) {
        this.detailId = detailId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getTransferName() {
        return transferName;
    }

    public void setTransferName(String transferName) {
        this.transferName = transferName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getTransferTime() {
        return transferTime;
    }

    public void setTransferTime(Date transferTime) {
        this.transferTime = transferTime;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}

package com.riversoft.weixin.pay.redpack.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

/**
 * Created by exizhai on 12/2/2015.
 */
public class RedPackResult {

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

    @JsonProperty("total_amount")
    private int totalAmount;

    @JsonProperty("total_num")
    private int number;

    @JsonProperty("send_type")
    private String sendType;

    private String reason;

    @JsonProperty("hb_type")
    private String readPackType;

    @JsonProperty("send_time")
    private Date sendTime;

    @JsonProperty("refund_time")
    private Date refundTime;

    @JsonProperty("refund_amount")
    private int refundAmount;

    @JsonProperty("hblist")
    private List<RedPackReport> reports;

    public boolean isSuccess() {
        return "SUCCESS".equals(returnCode) && "SUCCESS".equals(resultCode);
    }

    public List<RedPackReport> getReports() {
        return reports;
    }

    public void setReports(List<RedPackReport> reports) {
        this.reports = reports;
    }

    public int getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(int refundAmount) {
        this.refundAmount = refundAmount;
    }

    public Date getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(Date refundTime) {
        this.refundTime = refundTime;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getReadPackType() {
        return readPackType;
    }

    public void setReadPackType(String readPackType) {
        this.readPackType = readPackType;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getSendType() {
        return sendType;
    }

    public void setSendType(String sendType) {
        this.sendType = sendType;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(String billNumber) {
        this.billNumber = billNumber;
    }

    public String getErrorCodeDesc() {
        return errorCodeDesc;
    }

    public void setErrorCodeDesc(String errorCodeDesc) {
        this.errorCodeDesc = errorCodeDesc;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public static class RedPackReport {

        @JsonProperty("openid")
        private String openId;

        private int amount;

        private String status;

        @JsonProperty("rcv_time")
        private Date receivedTime;

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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Date getReceivedTime() {
            return receivedTime;
        }

        public void setReceivedTime(Date receivedTime) {
            this.receivedTime = receivedTime;
        }
    }
}

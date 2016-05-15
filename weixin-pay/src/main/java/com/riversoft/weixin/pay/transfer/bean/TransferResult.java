package com.riversoft.weixin.pay.transfer.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.riversoft.weixin.pay.base.BaseResponse;

import java.util.Date;

/**
 * Created by exizhai on 12/2/2015.
 */
public class TransferResult extends BaseResponse {

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

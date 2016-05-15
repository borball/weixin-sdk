package com.riversoft.weixin.pay.redpack.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.riversoft.weixin.pay.base.BaseResponse;

import java.util.Date;

/**
 * Created by exizhai on 11/22/2015.
 */
public class RedPackResponse extends BaseResponse {

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

package com.riversoft.weixin.mp.care.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.riversoft.weixin.common.util.DateDeserializer;

import java.util.Date;

/**
 * Created by exizhai on 11/22/2015.
 */
public class SessionLog {

    @JsonProperty("openid")
    private String openId;

    @JsonProperty("opercode")
    private int operationCode;

    private String text;

    @JsonDeserialize(using = DateDeserializer.class)
    private Date time;

    @JsonProperty("worker")
    private String account;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public int getOperationCode() {
        return operationCode;
    }

    public void setOperationCode(int operationCode) {
        this.operationCode = operationCode;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}

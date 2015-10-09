package com.riversoft.weixin.message.xml;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.riversoft.weixin.message.base.Message;
import com.riversoft.weixin.message.base.MsgType;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by exizhai on 9/25/2015.
 */
public abstract class XmlMessageHeader implements Message, Serializable {

    @JsonProperty("MsgType")
    @JacksonXmlCData
    protected MsgType msgType;
    @JsonProperty("ToUserName")
    @JacksonXmlCData
    private String toUser;
    @JsonProperty("FromUserName")
    @JacksonXmlCData
    private String fromUser;
    @JsonProperty("CreateTime")
    private Date createTime;

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public MsgType getMsgType() {
        return msgType;
    }

    public XmlMessageHeader fromUser(String fromUser) {
        this.fromUser = fromUser;
        return this;
    }

    public XmlMessageHeader toUser(String toUser) {
        this.toUser = toUser;
        return this;
    }

}

package com.riversoft.weixin.common.message;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.riversoft.weixin.common.util.DateDeserializer;
import com.riversoft.weixin.common.util.DateSerializer;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by exizhai on 9/25/2015.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class XmlMessageHeader implements Serializable {

    @JacksonXmlProperty(localName = "MsgType")
    @JacksonXmlCData
    protected MsgType msgType;

    @JacksonXmlProperty(localName = "ToUserName")
    @JacksonXmlCData
    private String toUser;

    @JacksonXmlProperty(localName = "FromUserName")
    @JacksonXmlCData
    private String fromUser;

    @JacksonXmlProperty(localName = "CreateTime")
    @JsonDeserialize(using = DateDeserializer.class)
    @JsonSerialize(using = DateSerializer.class)
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

    public void setMsgType(MsgType msgType) {
        this.msgType = msgType;
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

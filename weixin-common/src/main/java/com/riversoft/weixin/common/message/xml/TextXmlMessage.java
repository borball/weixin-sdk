package com.riversoft.weixin.common.message.xml;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.riversoft.weixin.common.message.MsgType;
import com.riversoft.weixin.common.message.XmlMessageHeader;

import java.util.Date;

/**
 * Created by exizhai on 9/26/2015.
 */
@JacksonXmlRootElement(localName = "xml")
public class TextXmlMessage extends XmlMessageHeader {

    @JsonProperty("Content")
    @JacksonXmlCData
    private String content;

    public TextXmlMessage() {
        this.msgType = MsgType.text;
        this.setCreateTime(new Date());
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public TextXmlMessage content(String content) {
        this.content = content;
        return this;
    }
}

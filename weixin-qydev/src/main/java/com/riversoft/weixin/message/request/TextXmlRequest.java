package com.riversoft.weixin.message.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * Created by exizhai on 9/29/2015.
 */
@JacksonXmlRootElement(localName = "xml")
public class TextXmlRequest extends XmlRequest {

    @JsonProperty("MsgId")
    private String msgId;

    @JsonProperty("Content")
    @JacksonXmlCData
    private String content;

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

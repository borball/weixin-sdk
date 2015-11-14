package com.riversoft.weixin.common.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.riversoft.weixin.common.message.XmlMessageHeader;

/**
 * Created by exizhai on 9/29/2015.
 */
@JacksonXmlRootElement(localName = "xml")
public class LocationRequest extends XmlMessageHeader {

    @JsonProperty("MsgId")
    private String msgId;

    @JsonProperty("Location_X")
    private String x;

    @JsonProperty("Location_Y")
    private String y;

    @JsonProperty("Scale")
    private String scale;

    @JsonProperty("Label")
    @JacksonXmlCData
    private String label;

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}

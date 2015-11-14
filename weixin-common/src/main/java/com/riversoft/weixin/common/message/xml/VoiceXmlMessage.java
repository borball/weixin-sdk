package com.riversoft.weixin.common.message.xml;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.riversoft.weixin.common.message.Media;
import com.riversoft.weixin.common.message.MsgType;
import com.riversoft.weixin.common.message.XmlMessageHeader;

import java.util.Date;

/**
 * Created by exizhai on 9/26/2015.
 */
@JacksonXmlRootElement(localName = "xml")
public class VoiceXmlMessage extends XmlMessageHeader {

    @JsonProperty("Voice")
    private Media media;

    public VoiceXmlMessage() {
        this.msgType = MsgType.voice;
        this.setCreateTime(new Date());
    }

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    public VoiceXmlMessage voice(String mediaId) {
        this.media = new Media(mediaId);
        return this;
    }
}

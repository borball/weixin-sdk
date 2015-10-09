package com.riversoft.weixin.message.xml;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.riversoft.weixin.message.base.Media;
import com.riversoft.weixin.message.base.MsgType;

import java.util.Date;

/**
 * Created by exizhai on 9/26/2015.
 */
@JacksonXmlRootElement(localName = "xml")
public class ImageXmlMessage extends XmlMessageHeader {

    @JsonProperty("Image")
    private Media media;

    public ImageXmlMessage() {
        this.msgType = MsgType.image;
        this.setCreateTime(new Date());
    }

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    public ImageXmlMessage image(String mediaId) {
        this.media = new Media(mediaId);
        return this;
    }
}

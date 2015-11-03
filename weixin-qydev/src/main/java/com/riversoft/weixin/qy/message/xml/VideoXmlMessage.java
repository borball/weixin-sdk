package com.riversoft.weixin.qy.message.xml;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.riversoft.weixin.qy.message.base.MsgType;
import com.riversoft.weixin.qy.message.base.Video;

import java.util.Date;

/**
 * Created by exizhai on 9/26/2015.
 */
@JacksonXmlRootElement(localName = "xml")
public class VideoXmlMessage extends XmlMessageHeader {

    @JsonProperty("Video")
    private Video video;

    public VideoXmlMessage() {
        this.msgType = MsgType.video;
        this.setCreateTime(new Date());
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public VideoXmlMessage video(Video video) {
        this.video = video;
        return this;
    }
}

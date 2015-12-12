package com.riversoft.weixin.qy.message.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.riversoft.weixin.common.message.Media;
import com.riversoft.weixin.common.message.MsgType;

/**
 * Created by exizhai on 11/14/2015.
 */
public class MpNewsMediaIdMessage extends JsonMessage {

    @JsonProperty("mpnews")
    private Media media;

    public MpNewsMediaIdMessage(String mediaId) {
        this.msgType = MsgType.mpnews;
        Media m = new Media(mediaId);
        this.media = m;
    }

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }
}

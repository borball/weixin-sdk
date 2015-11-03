package com.riversoft.weixin.qy.message.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.riversoft.weixin.qy.message.base.Media;
import com.riversoft.weixin.qy.message.base.MsgType;
import com.riversoft.weixin.qy.util.BooleanDeserializer;
import com.riversoft.weixin.qy.util.BooleanSerializer;

/**
 * Created by exizhai on 9/26/2015.
 */
public class FileMessage extends JsonMessage {

    @JsonProperty("media_id")
    private Media media;
    @JsonSerialize(using = BooleanSerializer.class)
    @JsonDeserialize(using = BooleanDeserializer.class)
    private boolean safe;

    public FileMessage() {
        this.msgType = MsgType.file;
    }

    public boolean isSafe() {
        return safe;
    }

    public void setSafe(boolean safe) {
        this.safe = safe;
    }

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    public FileMessage file(String mediaId) {
        this.media = new Media(mediaId);
        return this;
    }

    public FileMessage safe() {
        this.safe = true;
        return this;
    }
}

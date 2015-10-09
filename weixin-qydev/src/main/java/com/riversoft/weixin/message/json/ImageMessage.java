package com.riversoft.weixin.message.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.riversoft.weixin.message.base.Media;
import com.riversoft.weixin.message.base.MsgType;
import com.riversoft.weixin.util.BooleanDeserializer;
import com.riversoft.weixin.util.BooleanSerializer;

/**
 * Created by exizhai on 9/26/2015.
 */
public class ImageMessage extends JsonMessage {

    @JsonProperty("media_id")
    private Media media;
    @JsonSerialize(using = BooleanSerializer.class)
    @JsonDeserialize(using = BooleanDeserializer.class)
    private boolean safe;

    public ImageMessage() {
        this.msgType = MsgType.image;
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

    public ImageMessage image(String mediaId) {
        this.media = new Media(mediaId);
        return this;
    }

    public ImageMessage safe() {
        this.safe = true;
        return this;
    }
}

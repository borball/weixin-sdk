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
public class VoiceMessage extends JsonMessage {

    @JsonSerialize(using = BooleanSerializer.class)
    @JsonDeserialize(using = BooleanDeserializer.class)
    private boolean safe;
    @JsonProperty("media_id")
    private Media media;

    public VoiceMessage() {
        this.msgType = MsgType.voice;
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

    public VoiceMessage voice(String mediaId) {
        this.media = new Media(mediaId);
        return this;
    }

    public VoiceMessage safe() {
        this.safe = true;
        return this;
    }
}

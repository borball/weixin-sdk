package com.riversoft.weixin.qy.message.json;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.riversoft.weixin.common.message.MsgType;
import com.riversoft.weixin.common.message.Video;
import com.riversoft.weixin.common.util.BooleanDeserializer;
import com.riversoft.weixin.common.util.BooleanSerializer;

/**
 * Created by exizhai on 9/26/2015.
 */
public class VideoMessage extends JsonMessage {

    @JsonSerialize(using = BooleanSerializer.class)
    @JsonDeserialize(using = BooleanDeserializer.class)
    private boolean safe;
    private Video video;

    public VideoMessage() {
        this.msgType = MsgType.video;
    }

    public boolean isSafe() {
        return safe;
    }

    public void setSafe(boolean safe) {
        this.safe = safe;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public VideoMessage video(Video video) {
        this.video = video;
        return this;
    }

    public VideoMessage safe() {
        this.safe = true;
        return this;
    }
}

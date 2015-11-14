package com.riversoft.weixin.qy.message.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.riversoft.weixin.common.message.MpNews;
import com.riversoft.weixin.common.message.MsgType;
import com.riversoft.weixin.common.util.BooleanDeserializer;
import com.riversoft.weixin.common.util.BooleanSerializer;

/**
 * Created by exizhai on 9/26/2015.
 */
public class MpNewsMessage extends JsonMessage {

    @JsonSerialize(using = BooleanSerializer.class)
    @JsonDeserialize(using = BooleanDeserializer.class)
    private boolean safe;
    @JsonProperty("mpnews")
    private MpNews mpNews;

    public MpNewsMessage() {
        this.msgType = MsgType.mpnews;
    }

    public boolean isSafe() {
        return safe;
    }

    public void setSafe(boolean safe) {
        this.safe = safe;
    }

    public MpNews getMpNews() {
        return mpNews;
    }

    public void setMpNews(MpNews mpNews) {
        this.mpNews = mpNews;
    }

    public MpNewsMessage mpNews(MpNews mpNews) {
        this.mpNews = mpNews;
        return this;
    }

    public MpNewsMessage safe() {
        this.safe = true;
        return this;
    }

}

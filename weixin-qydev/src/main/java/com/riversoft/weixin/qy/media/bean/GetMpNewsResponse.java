package com.riversoft.weixin.qy.media.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.riversoft.weixin.common.message.MpNews;

/**
 * Created by exizhai on 11/15/2015.
 */
public class GetMpNewsResponse {

    private String type;

    @JsonProperty("mpnews")
    private MpNews mpNews;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public MpNews getMpNews() {
        return mpNews;
    }

    public void setMpNews(MpNews mpNews) {
        this.mpNews = mpNews;
    }
}

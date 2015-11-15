package com.riversoft.weixin.qy.media.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.riversoft.weixin.common.message.MpNews;

/**
 * Created by exizhai on 11/14/2015.
 */
public class MpNewsMedia {

    @JsonProperty("agentid")
    private int agentId;

    @JsonProperty("mpnews")
    private MpNews mpNews;

    public int getAgentId() {
        return agentId;
    }

    public void setAgentId(int agentId) {
        this.agentId = agentId;
    }

    public MpNews getMpNews() {
        return mpNews;
    }

    public void setMpNews(MpNews mpNews) {
        this.mpNews = mpNews;
    }

    public MpNewsMedia agent(int agentId){
        this.agentId = agentId;
        return this;
    }

    public MpNewsMedia mpNews(MpNews mpNews) {
        this.mpNews = mpNews;
        return this;
    }
}

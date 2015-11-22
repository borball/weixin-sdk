package com.riversoft.weixin.common.media;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by exizhai on 11/21/2015.
 */
public class Video {

    private String title;

    @JsonProperty("description")
    private String desc;

    @JsonProperty("down_url")
    private String url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}

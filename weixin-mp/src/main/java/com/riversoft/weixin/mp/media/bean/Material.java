package com.riversoft.weixin.mp.media.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 永久素材
 * Created by exizhai on 11/19/2015.
 */
public class Material {

    @JsonProperty("media_id")
    private String mediaId;

    /**
     * 服务号和订阅号特有
     */
    private String url;

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

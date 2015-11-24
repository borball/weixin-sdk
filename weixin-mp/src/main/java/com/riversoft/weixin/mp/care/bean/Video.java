package com.riversoft.weixin.mp.care.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by exizhai on 11/23/2015.
 */
public class Video {

    @JsonProperty("media_id")
    private String mediaId;

    @JsonProperty("thumb_media_id")
    private String thumbMediaId;

    private String title;

    private String description;

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getThumbMediaId() {
        return thumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Video mediaId(String mediaId) {
        this.mediaId = mediaId;
        return this;
    }

    public Video thumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
        return this;
    }

    public Video title(String title) {
        this.title = title;
        return this;
    }

    public Video description(String description) {
        this.description = description;
        return this;
    }
}

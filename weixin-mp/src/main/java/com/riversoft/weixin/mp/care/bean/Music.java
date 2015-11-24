package com.riversoft.weixin.mp.care.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by exizhai on 11/23/2015.
 */
public class Music {

    @JsonProperty("musicurl")
    private String musicUrl;

    @JsonProperty("hqmusicurl")
    private String hqMusicUrl;

    @JsonProperty("thumb_media_id")
    private String thumbMediaId;

    private String title;

    private String description;

    public String getMusicUrl() {
        return musicUrl;
    }

    public void setMusicUrl(String musicUrl) {
        this.musicUrl = musicUrl;
    }

    public String getHqMusicUrl() {
        return hqMusicUrl;
    }

    public void setHqMusicUrl(String hqMusicUrl) {
        this.hqMusicUrl = hqMusicUrl;
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

    public Music musicUrl(String musicUrl) {
        this.musicUrl = musicUrl;
        return this;
    }

    public Music hqMusicUrl(String hqMusicUrl) {
        this.hqMusicUrl = hqMusicUrl;
        return this;
    }

    public Music thumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
        return this;
    }

    public Music title(String title) {
        this.title = title;
        return this;
    }

    public Music description(String description) {
        this.description = description;
        return this;
    }
}

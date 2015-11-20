package com.riversoft.weixin.mp.media.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.riversoft.weixin.common.media.MediaType;

import java.util.Date;

/**
 * Created by exizhai on 11/19/2015.
 */
public class MediaUploadResult {

    private MediaType type;

    @JsonProperty("media_id")
    private String mediaId;

    @JsonProperty("created_at")
    private Date createdAt;

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public MediaType getType() {
        return type;
    }

    public void setType(MediaType type) {
        this.type = type;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}

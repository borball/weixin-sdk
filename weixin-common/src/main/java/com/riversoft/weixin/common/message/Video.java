package com.riversoft.weixin.common.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.io.Serializable;

/**
 * Created by exizhai on 9/25/2015.
 */
public class Video implements Serializable {

    @JsonProperty("media_id")
    @JacksonXmlProperty(localName = "MediaId")
    @JacksonXmlCData
    private String mediaId;

    @JacksonXmlProperty(localName = "Title")
    @JacksonXmlCData
    private String title;

    @JacksonXmlProperty(localName = "Description")
    @JacksonXmlCData
    private String description;

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
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

    public Video title(String title) {
        this.title = title;
        return this;
    }

    public Video description(String description) {
        this.description = description;
        return this;
    }
}

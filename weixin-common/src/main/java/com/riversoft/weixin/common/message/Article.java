package com.riversoft.weixin.common.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.io.Serializable;

/**
 * Created by exizhai on 9/25/2015.
 */
@JacksonXmlRootElement(localName = "item")
public class Article implements Serializable {

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "Title")
    private String title;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "Description")
    private String description;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "PicUrl")
    @JsonProperty("picurl")
    private String picUrl;

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "Url")
    private String url;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public Article title(String title) {
        this.title = title;
        return this;
    }

    public Article url(String url) {
        this.url = url;
        return this;
    }

    public Article description(String description) {
        this.description = description;
        return this;
    }

    public Article picUrl(String picUrl) {
        this.picUrl = picUrl;
        return this;
    }
}

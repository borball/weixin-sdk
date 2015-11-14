package com.riversoft.weixin.mp.message;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.io.Serializable;

/**
 * Created by exizhai on 11/14/2015.
 */
public class Music implements Serializable {

    @JacksonXmlProperty(localName = "Title")
    @JacksonXmlCData
    private String title;

    @JacksonXmlProperty(localName = "Description")
    @JacksonXmlCData
    private String desc;

    @JacksonXmlProperty(localName = "MusicUrl")
    @JacksonXmlCData
    private String musicUrl;

    @JacksonXmlProperty(localName = "HQMusicUrl")
    @JacksonXmlCData
    private String hqMusicUrl;

    @JacksonXmlProperty(localName = "ThumbMediaId")
    @JacksonXmlCData
    private String thumbMediaId;

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

    public Music title(String title) {
        this.title = title;
        return this;
    }

    public Music desc(String desc) {
        this.desc = desc;
        return this;
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
}

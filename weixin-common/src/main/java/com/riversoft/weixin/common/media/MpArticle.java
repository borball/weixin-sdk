package com.riversoft.weixin.common.media;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.riversoft.weixin.common.util.BooleanDeserializer;
import com.riversoft.weixin.common.util.BooleanSerializer;

import java.io.Serializable;

/**
 * Created by exizhai on 9/25/2015.
 */
public class MpArticle implements Serializable {

    private String title;
    private String author;
    private String content;
    private String digest;

    @JsonProperty("thumb_media_id")
    private String thumbMediaId;

    @JsonProperty("thumb_url")
    private String thumbUrl;

    @JsonProperty("content_source_url")
    private String contentSourceUrl;

    @JsonSerialize(using = BooleanSerializer.class)
    @JsonDeserialize(using = BooleanDeserializer.class)
    @JsonProperty("show_cover_pic")
    private boolean showCover;

    private String url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getThumbMediaId() {
        return thumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
    }

    public String getContentSourceUrl() {
        return contentSourceUrl;
    }

    public void setContentSourceUrl(String contentSourceUrl) {
        this.contentSourceUrl = contentSourceUrl;
    }

    public boolean isShowCover() {
        return showCover;
    }

    public void setShowCover(boolean showCover) {
        this.showCover = showCover;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public MpArticle title(String title) {
        this.title = title;
        return this;
    }

    public MpArticle author(String author) {
        this.author = author;
        return this;
    }

    public MpArticle content(String content) {
        this.content = content;
        return this;
    }

    public MpArticle digest(String digest) {
        this.digest = digest;
        return this;
    }

    public MpArticle thumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
        return this;
    }

    public MpArticle contentSourceUrl(String contentSourceUrl) {
        this.contentSourceUrl = contentSourceUrl;
        return this;
    }

    public MpArticle showCover() {
        this.showCover = true;
        return this;
    }

}

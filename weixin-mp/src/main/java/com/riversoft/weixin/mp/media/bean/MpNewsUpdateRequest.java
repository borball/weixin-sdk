package com.riversoft.weixin.mp.media.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.riversoft.weixin.common.media.MpArticle;

/**
 * Created by exizhai on 11/19/2015.
 */
public class MpNewsUpdateRequest {

    @JsonProperty("media_id")
    private String mediaId;

    private int index;

    @JsonProperty("articles")
    private MpArticle article;

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public MpArticle getArticle() {
        return article;
    }

    public void setArticle(MpArticle article) {
        this.article = article;
    }
}

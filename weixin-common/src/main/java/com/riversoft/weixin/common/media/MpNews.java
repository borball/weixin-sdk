package com.riversoft.weixin.common.media;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by exizhai on 9/26/2015.
 */
public class MpNews implements Serializable {

    private List<MpArticle> articles = new ArrayList<>();

    public List<MpArticle> getArticles() {
        return articles;
    }

    public void setArticles(List<MpArticle> articles) {
        this.articles = articles;
    }

    @JsonProperty("news_item")
    public void setItems(List<MpArticle> articles) {
        this.articles = articles;
    }

    public void add(MpArticle mpArticle) {
        this.getArticles().add(mpArticle);
    }

    public MpNews article(MpArticle article) {
        this.getArticles().add(article);
        return this;
    }
}

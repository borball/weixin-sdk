package com.riversoft.weixin.qy.media.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.riversoft.weixin.common.media.MpArticle;
import com.riversoft.weixin.common.util.DateDeserializer;

import java.util.Date;
import java.util.List;

/**
 * Created by exizhai on 10/2/2015.
 */
public class MpNewsPagination {

    @JsonProperty("total_count")
    private int totalCount;

    @JsonProperty("item_count")
    private int currentCount;

    @JsonProperty("itemlist")
    @JsonUnwrapped()
    private List<MpNewsItem> items;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getCurrentCount() {
        return currentCount;
    }

    public void setCurrentCount(int currentCount) {
        this.currentCount = currentCount;
    }

    public List<MpNewsItem> getItems() {
        return items;
    }

    public void setItems(List<MpNewsItem> items) {
        this.items = items;
    }

    public static class MpNewsItem {

        @JsonProperty("media_id")
        private String mediaId;

        private Content content;

        @JsonProperty("update_time")
        @JsonDeserialize(using = DateDeserializer.class)
        private Date updateTime;

        public String getMediaId() {
            return mediaId;
        }

        public void setMediaId(String mediaId) {
            this.mediaId = mediaId;
        }

        public Content getContent() {
            return content;
        }

        public void setContent(Content content) {
            this.content = content;
        }

        public Date getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Date updateTime) {
            this.updateTime = updateTime;
        }

        public static class Content {

            @JsonProperty("articles")
            private List<MpArticle> news;

            public List<MpArticle> getNews() {
                return news;
            }

            public void setNews(List<MpArticle> news) {
                this.news = news;
            }
        }

    }
}

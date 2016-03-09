package com.riversoft.weixin.mp.media.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.riversoft.weixin.common.media.MpArticle;
import com.riversoft.weixin.common.util.DateDeserializer;

import java.util.Date;
import java.util.List;

/**
 * Created by exizhai on 11/19/2015.
 */

/**
 * 图文素材
 */
public class MpNewsPagination {

    /**
     * 总素材数
     */
    @JsonProperty("total_count")
    private int totalCount;

    /**
     * 当前页返回素材数
     */
    @JsonProperty("item_count")
    private int currentCount;

    /**
     * 素材列表
     */
    @JsonProperty("item")
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

            @JsonProperty("news_item")
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

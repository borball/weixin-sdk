package com.riversoft.weixin.mp.media.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.riversoft.weixin.common.util.DateDeserializer;

import java.util.Date;
import java.util.List;

/**
 * Created by exizhai on 11/19/2015.
 */

/**
 * 图片，语音和视频
 */
public class MaterialSearchResult {

    /**
     * 总素材数
     */
    @JsonProperty("total_count")
    private int totalCount;

    /**
     * 当前页返回素材数母
     */
    @JsonProperty("item_count")
    private int currentCount;

    /**
     * 素材列表
     */
    @JsonProperty("item")
    private List<Media> medias;

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

    public List<Media> getMedias() {
        return medias;
    }

    public void setMedias(List<Media> medias) {
        this.medias = medias;
    }

    public class Media {

        @JsonProperty("media_id")
        private String mediaId;
        private String name;

        @JsonProperty("update_time")
        @JsonDeserialize(using = DateDeserializer.class)
        private Date updateTime;

        private String url;

        public String getMediaId() {
            return mediaId;
        }

        public void setMediaId(String mediaId) {
            this.mediaId = mediaId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Date getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Date updateTime) {
            this.updateTime = updateTime;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}

package com.riversoft.weixin.qy.media.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import java.util.Date;
import java.util.List;

/**
 * Created by exizhai on 10/2/2015.
 */
public class SearchResult {

    private MediaType type;

    @JsonProperty("total_count")
    private int totalCount;

    @JsonProperty("item_count")
    private int currentCount;

    @JsonProperty("itemlist")
    @JsonUnwrapped()
    private List<Item> items;

    public MediaType getType() {
        return type;
    }

    public void setType(MediaType type) {
        this.type = type;
    }

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

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public static class Item {

        @JsonProperty("media_id")
        private String mediaId;

        @JsonProperty("filename")
        private String fileName;

        @JsonProperty("update_time")
        private Date updateTime;

        public String getMediaId() {
            return mediaId;
        }

        public void setMediaId(String mediaId) {
            this.mediaId = mediaId;
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public Date getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Date updateTime) {
            this.updateTime = updateTime;
        }
    }
}

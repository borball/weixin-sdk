package com.riversoft.weixin.mp.media.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import java.util.Date;
import java.util.List;

/**
 * Created by exizhai on 10/2/2015.
 */
public class MaterialPagination {

    @JsonProperty("total_count")
    private int totalCount;

    @JsonProperty("item_count")
    private int currentCount;

    @JsonProperty("item")
    @JsonUnwrapped()
    private List<Material> items;

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

    public List<Material> getItems() {
        return items;
    }

    public void setItems(List<Material> items) {
        this.items = items;
    }

    public static class Material {

        private String mediaId;
        private String fileName;
        private Date updateTime;
        private String url;

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

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}

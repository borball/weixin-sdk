package com.riversoft.weixin.qy.shake.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @borball on 3/27/2016.
 */
public class Shake {

    @JsonProperty("page_id")
    private long pageId;

    @JsonProperty("beacon_info")
    private Beacon beacon;

    @JsonProperty("openid")
    private String openId;

    @JsonProperty("userid")
    private String userId;

    public long getPageId() {
        return pageId;
    }

    public void setPageId(long pageId) {
        this.pageId = pageId;
    }

    public Beacon getBeacon() {
        return beacon;
    }

    public void setBeacon(Beacon beacon) {
        this.beacon = beacon;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}

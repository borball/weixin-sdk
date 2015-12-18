package com.riversoft.weixin.qy.oauth2.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by exizhai on 10/4/2015.
 */
public class QyUser {

    @JsonProperty("UserId")
    private String userId;

    @JsonProperty("OpenId")
    private String openId;

    @JsonProperty("DeviceId")
    private String deviceId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}

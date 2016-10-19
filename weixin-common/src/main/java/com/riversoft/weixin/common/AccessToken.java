package com.riversoft.weixin.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.riversoft.weixin.common.util.JsonMapper;

/**
 * @borball on 8/14/2016.
 */
public class AccessToken {

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("expires_in")
    private long expiresIn;

    private long expiresTill;

    public static AccessToken fromJson(String json) {
        return JsonMapper.defaultMapper().fromJson(json, AccessToken.class);
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
        this.expiresTill = System.currentTimeMillis() + (expiresIn * 1000) - 300000;
    }

    public long getExpiresTill() {
        return expiresTill;
    }

    public boolean expired() {
        return System.currentTimeMillis() > expiresTill;
    }
}

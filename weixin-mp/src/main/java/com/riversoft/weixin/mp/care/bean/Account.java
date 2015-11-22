package com.riversoft.weixin.mp.care.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by exizhai on 11/22/2015.
 */
public class Account {

    @JsonProperty("kf_id")
    private String id;

    @JsonProperty("kf_account")
    private String account;

    @JsonProperty("kf_nick")
    private String nickName;

    @JsonProperty("kf_headimgurl")
    private String headImgUrl;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }
}

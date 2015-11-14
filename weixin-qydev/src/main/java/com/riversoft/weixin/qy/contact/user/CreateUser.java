package com.riversoft.weixin.qy.contact.user;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by exizhai on 10/4/2015.
 */
public class CreateUser extends AbstractUser {

    @JsonProperty("avatar_mediaid")
    private String avatarMediaId;

    public String getAvatarMediaId() {
        return avatarMediaId;
    }

    public void setAvatarMediaId(String avatarMediaId) {
        this.avatarMediaId = avatarMediaId;
    }
}

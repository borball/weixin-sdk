package com.riversoft.weixin.qy.contact.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.riversoft.weixin.qy.util.UserStatusDeserializer;

/**
 * Created by exizhai on 10/4/2015.
 */
public class ReadUser extends AbstractUser {

    private String avatar;
    @JsonIgnore
    private UserStatus status;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @JsonIgnore
    public UserStatus getStatus() {
        return status;
    }

    @JsonDeserialize(using = UserStatusDeserializer.class)
    public void setStatus(UserStatus status) {
        this.status = status;
    }

}

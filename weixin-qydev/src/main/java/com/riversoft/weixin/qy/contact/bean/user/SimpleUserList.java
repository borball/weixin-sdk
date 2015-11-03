package com.riversoft.weixin.qy.contact.bean.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import java.util.List;

/**
 * Created by exizhai on 10/4/2015.
 */
public class SimpleUserList {

    @JsonProperty("userlist")
    @JsonUnwrapped
    private List<SimpleUser> users;

    public List<SimpleUser> getUsers() {
        return users;
    }

    public void setUsers(List<SimpleUser> users) {
        this.users = users;
    }

}

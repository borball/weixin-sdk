package com.riversoft.weixin.contact.bean.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import java.util.List;

/**
 * Created by exizhai on 10/4/2015.
 */
public class ReadUserList {


    @JsonProperty("userlist")
    @JsonUnwrapped
    private List<ReadUser> users;

    public List<ReadUser> getUsers() {
        return users;
    }

    public void setUsers(List<ReadUser> users) {
        this.users = users;
    }
}

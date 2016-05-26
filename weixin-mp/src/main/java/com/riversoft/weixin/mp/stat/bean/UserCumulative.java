package com.riversoft.weixin.mp.stat.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @borball on 5/25/2016.
 */
public class UserCumulative {

    @JsonProperty("ref_date")
    private String date;

    @JsonProperty("cumulate_user")
    private int users;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getUsers() {
        return users;
    }

    public void setUsers(int users) {
        this.users = users;
    }
}

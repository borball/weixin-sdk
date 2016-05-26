package com.riversoft.weixin.mp.stat.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @borball on 5/25/2016.
 */
public class UserSummary {

    @JsonProperty("ref_date")
    private String date;

    @JsonProperty("user_source")
    private int sourceUsers;

    @JsonProperty("new_user")
    private int newUsers;

    @JsonProperty("cancel_user")
    private int canceledUsers;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getSourceUsers() {
        return sourceUsers;
    }

    public void setSourceUsers(int sourceUsers) {
        this.sourceUsers = sourceUsers;
    }

    public int getNewUsers() {
        return newUsers;
    }

    public void setNewUsers(int newUsers) {
        this.newUsers = newUsers;
    }

    public int getCanceledUsers() {
        return canceledUsers;
    }

    public void setCanceledUsers(int canceledUsers) {
        this.canceledUsers = canceledUsers;
    }
}

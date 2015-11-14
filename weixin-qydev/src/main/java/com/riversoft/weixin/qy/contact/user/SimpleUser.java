package com.riversoft.weixin.qy.contact.user;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by exizhai on 10/4/2015.
 */
public class SimpleUser {

    @JsonProperty("userid")
    private String userId;
    private String name;
    private int[] department;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int[] getDepartment() {
        return department;
    }

    public void setDepartment(int[] department) {
        this.department = department;
    }
}

package com.riversoft.weixin.qy.contact.tag;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by exizhai on 10/5/2015.
 */
public class TagUsers {

    @JsonProperty("tagid")
    private int tagId;

    @JsonProperty("userlist")
    private List<String> users;

    @JsonProperty("partylist")
    private List<Integer> departments;

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }

    public List<Integer> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Integer> departments) {
        this.departments = departments;
    }
}

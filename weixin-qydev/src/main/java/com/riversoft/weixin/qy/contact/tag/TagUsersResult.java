package com.riversoft.weixin.qy.contact.tag;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.List;

/**
 * Created by exizhai on 10/5/2015.
 */
public class TagUsersResult {

    @JsonProperty("errcode")
    private int code;

    @JsonProperty("errmsg")
    private String message;

    @JsonIgnore
    private String invalidUsers;

    @JsonProperty("partylist")
    private List<Integer> invalidDepartments;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @JsonProperty("invalidlist")
    public void setInvalidUsers(String invalidUsers) {
        this.invalidUsers = invalidUsers;
    }


    public List<String> getInvalidUserList() {
        //fuck, 为何不返回数组
        String[] users = this.invalidUsers.split("|");
        return Arrays.asList(users);
    }

    public List<Integer> getInvalidDepartments() {
        return invalidDepartments;
    }

    public void setInvalidDepartments(List<Integer> invalidDepartments) {
        this.invalidDepartments = invalidDepartments;
    }
}

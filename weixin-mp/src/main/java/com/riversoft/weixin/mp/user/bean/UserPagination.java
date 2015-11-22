package com.riversoft.weixin.mp.user.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by exizhai on 11/21/2015.
 */
public class UserPagination {

    private int total;
    private int count;

    private Data users;

    @JsonProperty("next_openid")
    private String nextOpenId;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<String> getUsers() {
        return users.getUsers();
    }

    @JsonProperty("data")
    public void setUsers(Data users) {
        this.users = users;
    }

    public String getNextOpenId() {
        return nextOpenId;
    }

    public void setNextOpenId(String nextOpenId) {
        this.nextOpenId = nextOpenId;
    }

    public class Data {

        @JsonProperty("openid")
        private List<String> users;

        public List<String> getUsers() {
            return users;
        }

        public void setUsers(List<String> users) {
            this.users = users;
        }
    }
}

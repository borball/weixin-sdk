package com.riversoft.weixin.mp.user.bean;

import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Created by exizhai on 11/4/2015.
 */
@JsonRootName(value = "group")
public class Group {

    private int id;
    private String name;
    private int count;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

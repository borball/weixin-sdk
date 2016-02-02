package com.riversoft.weixin.qy.contact.tag;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by exizhai on 9/25/2015.
 */
public class Tag implements Serializable {

    public Tag() {
    }

    public Tag(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @JsonProperty("tagid")
    private int id;

    @JsonProperty("tagname")
    private String name;

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
}

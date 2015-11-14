package com.riversoft.weixin.qy.contact.department;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by exizhai on 10/4/2015.
 */
public class Department {

    private int id;
    private String name;

    @JsonProperty("parentid")
    private int parentId;
    private int order;

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

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}

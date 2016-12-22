package com.riversoft.weixin.common.menu;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by exizhai on 9/25/2015.
 */
public class MenuItem implements Serializable {

    private MenuType type;
    private String name;
    private String key;
    private String url;

    @JsonProperty("sub_button")
    private List<MenuItem> subItems = new ArrayList<MenuItem>();

    public MenuType getType() {
        return type;
    }

    public void setType(MenuType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<MenuItem> getSubItems() {
        return subItems;
    }

    public void setSubItems(List<MenuItem> subItems) {
        this.subItems = subItems;
    }

    public MenuItem name(String name) {
        this.name = name;
        return this;
    }

    public MenuItem key(String key) {
        this.key = key;
        return this;
    }

    public MenuItem url(String url) {
        this.setUrl(url);
        return this;
    }

    public MenuItem type(MenuType type) {
        this.type = type;
        return this;
    }

    public MenuItem add(MenuItem menuItem) {
        this.subItems.add(menuItem);
        return this;
    }
}

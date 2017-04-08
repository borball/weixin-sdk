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

    //view, miniprogram 必须
    private String url;

    //media_id,view_limited 必须
    @JsonProperty("media_id")
    private String mediaId;

    //小程序必须
    @JsonProperty("appid")
    private String appId;

    @JsonProperty("pagepath")
    private String pagePath;

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

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getPagePath() {
        return pagePath;
    }

    public void setPagePath(String pagePath) {
        this.pagePath = pagePath;
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

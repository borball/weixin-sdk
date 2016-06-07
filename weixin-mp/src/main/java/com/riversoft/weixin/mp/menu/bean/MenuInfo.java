package com.riversoft.weixin.mp.menu.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * @borball on 6/7/2016.
 */
public class MenuInfo {

    @JsonProperty("button")
    private List<MenuButton> menus = new ArrayList<>();

    public List<MenuButton> getMenus() {
        return menus;
    }

    public void setMenus(List<MenuButton> menus) {
        this.menus = menus;
    }

}

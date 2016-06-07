package com.riversoft.weixin.common.menu;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by exizhai on 9/25/2015.
 */
public class Menu implements Serializable {

    @JsonProperty("button")
    private List<MenuItem> menus = new ArrayList<>();

    public List<MenuItem> getMenus() {
        return menus;
    }

    public void setMenus(List<MenuItem> menus) {
        this.menus = menus;
    }

    public Menu add(MenuItem menuItem) {
        this.menus.add(menuItem);
        return this;
    }

}

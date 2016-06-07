package com.riversoft.weixin.mp.menu.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * 获取自定义菜单配置接口使用
 * 操蛋的微信，JSON的协议就不能统一一下？
 * @borball on 5/26/2016.
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

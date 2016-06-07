package com.riversoft.weixin.mp.menu.bean;

import java.util.List;

/**
 * 获取自定义菜单配置接口使用
 * 操蛋的微信，JSON的协议就不能统一一下？
 * @borball on 5/26/2016.
 */
public class MenuButtonList {

    private List<MenuButton> list;

    public List<MenuButton> getList() {
        return list;
    }

    public void setList(List<MenuButton> list) {
        this.list = list;
    }
}

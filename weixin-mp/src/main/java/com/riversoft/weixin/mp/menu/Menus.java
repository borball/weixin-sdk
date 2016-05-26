package com.riversoft.weixin.mp.menu;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.riversoft.weixin.common.WxClient;
import com.riversoft.weixin.common.menu.Menu;
import com.riversoft.weixin.common.util.JsonMapper;
import com.riversoft.weixin.mp.MpWxClientFactory;
import com.riversoft.weixin.mp.base.AppSetting;
import com.riversoft.weixin.mp.base.WxEndpoint;
import com.riversoft.weixin.mp.menu.bean.SelfMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 自定义菜单管理
 * Created by exizhai on 9/25/2015.
 */
public class Menus {

    private static Logger logger = LoggerFactory.getLogger(Menus.class);
    private WxClient wxClient;

    public static Menus defaultMenus() {
        return with(AppSetting.defaultSettings());
    }

    public static Menus with(AppSetting appSetting) {
        Menus menus = new Menus();
        menus.setWxClient(MpWxClientFactory.getInstance().with(appSetting));
        return menus;
    }

    public void setWxClient(WxClient wxClient) {
        this.wxClient = wxClient;
    }

    /**
     * 创建菜单，个性化菜单rule不能为空
     * @param menu
     */
    public void create(Menu menu) {
        String url = WxEndpoint.get("url.menu.create");
        if(menu.getRule() != null) {
            url = WxEndpoint.get("url.menu.create.condition");
        }
        String json = JsonMapper.nonEmptyMapper().toJson(menu);
        logger.debug("create menu: {}", json);
        wxClient.post(url, json);
    }

    /**
     * 删除所有菜单
     */
    public void delete() {
        String url = WxEndpoint.get("url.menu.delete");
        wxClient.get(url);
    }

    /**
     * 删除个性化菜单
     * @param menuId
     */
    public void delete(String menuId) {
        String url = WxEndpoint.get("url.menu.delete.condition");
        String json = String.format("{\"menuid\":\"%s\"}", menuId);
        logger.debug("delete menu: {}", menuId);
        wxClient.post(url, json);
    }

    /**
     * 获取default 菜单
     * @return
     */
    public Menu get() {
        String url = WxEndpoint.get("url.menu.get");
        String content = wxClient.get(url);
        logger.debug("get menu: {}", content);
        MenuWrapper menuWrapper = JsonMapper.nonEmptyMapper().fromJson(content, MenuWrapper.class);
        return menuWrapper.getMenu();
    }

    /**
     * 获取自定义菜单配置
     * @return
     */
    public SelfMenu getSelf() {
        String url = WxEndpoint.get("url.menu.get.self");
        String content = wxClient.get(url);
        logger.debug("get self menu: {}", content);
        return JsonMapper.nonEmptyMapper().fromJson(content, SelfMenu.class);
    }

    /**
     * 获取匹配的菜单
     * @param userId 用户openId或者微信号
     * @return
     */
    public Menu match(String userId) {
        String url = WxEndpoint.get("url.menu.match");
        String request = String.format("{\"user_id\":\"%s\"}", userId);
        String response = wxClient.post(url, request);
        logger.debug("matched menu: {}", response);
        MenuWrapper menuWrapper = JsonMapper.nonEmptyMapper().fromJson(response, MenuWrapper.class);
        return menuWrapper.getMenu();
    }

    /**
     * 获取所有的菜单，包括默认的和个性化的菜单
     * @return
     */
    public List<Menu> list() {
        String url = WxEndpoint.get("url.menu.get");
        String content = wxClient.get(url);
        logger.debug("lit menu: {}", content);
        MenuWrapper menuWrapper = JsonMapper.nonEmptyMapper().fromJson(content, MenuWrapper.class);
        List<Menu> menus = new ArrayList<>();
        menus.add(menuWrapper.getMenu());
        if (menuWrapper.getConditionalMenus() != null && !menuWrapper.getConditionalMenus().isEmpty()) {
            menus.addAll(menuWrapper.getConditionalMenus());
        }
        return menus;
    }

    /**
     * Created by exizhai on 9/27/2015.
     */
    public static class MenuWrapper implements Serializable {

        private Menu menu;

        @JsonProperty("conditionalmenu")
        private List<Menu> conditionalMenus;

        public Menu getMenu() {
            return menu;
        }

        public void setMenu(Menu menu) {
            this.menu = menu;
        }

        public List<Menu> getConditionalMenus() {
            return conditionalMenus;
        }

        public void setConditionalMenus(List<Menu> conditionalMenus) {
            this.conditionalMenus = conditionalMenus;
        }
    }
}

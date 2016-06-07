package com.riversoft.weixin.mp.menu;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.riversoft.weixin.common.WxClient;
import com.riversoft.weixin.common.exception.WxRuntimeException;
import com.riversoft.weixin.common.menu.Menu;
import com.riversoft.weixin.common.menu.RuleMenu;
import com.riversoft.weixin.common.util.JsonMapper;
import com.riversoft.weixin.mp.MpWxClientFactory;
import com.riversoft.weixin.mp.base.AppSetting;
import com.riversoft.weixin.mp.base.WxEndpoint;
import com.riversoft.weixin.mp.menu.bean.MenuConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
     * 创建菜单
     * @param menu
     */
    public void create(Menu menu) {
        String url = WxEndpoint.get("url.menu.create");
        String json = JsonMapper.nonEmptyMapper().toJson(menu);
        logger.debug("create menu: {}", json);
        wxClient.post(url, json);
    }

    /**
     * 创建个性化菜单，菜单rule不能为空
     * @param menu
     * @return
     */
    public String createRuleMenu(RuleMenu menu) {
        String url = WxEndpoint.get("url.menu.create.condition");
        if(menu.getRule() == null) {
            throw new IllegalArgumentException("个性化菜单rule不能为空");
        }
        String json = JsonMapper.nonEmptyMapper().toJson(menu);
        logger.debug("create rule menu: {}", json);
        String response = wxClient.post(url, json);
        Map<String, Object> map = JsonMapper.defaultMapper().json2Map(response);
        if(map.containsKey("menuid")) {
            return (String) map.get("menuid");
        } else {
            throw new WxRuntimeException(999, "create rule menu failed");
        }
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
        logger.debug("get default menu: {}", content);
        MenuWrapper menuWrapper = JsonMapper.nonEmptyMapper().fromJson(content, MenuWrapper.class);
        return menuWrapper.getMenu();
    }

    /**
     * 获取个性化菜单
     * @return
     */
    public List<RuleMenu> getRuleMenus() {
        String url = WxEndpoint.get("url.menu.get");
        String content = wxClient.get(url);
        logger.debug("get rule menus: {}", content);
        MenuWrapper menuWrapper = JsonMapper.nonEmptyMapper().fromJson(content, MenuWrapper.class);
        return menuWrapper.getRuleMenus();
    }

    /**
     * 获取自定义菜单配置
     * 操蛋的微信，JSON太混乱了；本接口拒绝支持 '公众号是在公众平台官网通过网站功能发布菜单'
     * @return
     */
    public MenuConfig getMenuConfig() {
        String url = WxEndpoint.get("url.menu.get.self");
        String content = wxClient.get(url);
        logger.debug("get menu config: {}", content);
        return JsonMapper.nonEmptyMapper().fromJson(content, MenuConfig.class);
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
        if (menuWrapper.getRuleMenus() != null && !menuWrapper.getRuleMenus().isEmpty()) {
            menus.addAll(menuWrapper.getRuleMenus());
        }
        return menus;
    }

    public static class MenuWrapper implements Serializable {

        private Menu menu;

        @JsonProperty("conditionalmenu")
        private List<RuleMenu> ruleMenus;

        public Menu getMenu() {
            return menu;
        }

        public void setMenu(Menu menu) {
            this.menu = menu;
        }

        public List<RuleMenu> getRuleMenus() {
            return ruleMenus;
        }

        public void setRuleMenus(List<RuleMenu> ruleMenus) {
            this.ruleMenus = ruleMenus;
        }
    }
}

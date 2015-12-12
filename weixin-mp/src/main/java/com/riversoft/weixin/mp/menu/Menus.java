package com.riversoft.weixin.mp.menu;

import com.riversoft.weixin.common.WxClient;
import com.riversoft.weixin.common.menu.Menu;
import com.riversoft.weixin.common.menu.MenuWrapper;
import com.riversoft.weixin.common.util.JsonMapper;
import com.riversoft.weixin.mp.MpWxClientFactory;
import com.riversoft.weixin.mp.base.AppSetting;
import com.riversoft.weixin.mp.base.WxEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    public void create(Menu menu) {
        String url = WxEndpoint.get("url.menu.create");
        String json = JsonMapper.nonEmptyMapper().toJson(menu);
        logger.info("update menu: {}", json);
        wxClient.post(url, json);
    }

    public void delete() {
        String url = WxEndpoint.get("url.menu.delete");
        wxClient.get(url);
    }

    public Menu get() {
        String url = WxEndpoint.get("url.menu.get");
        String content = wxClient.get(url);
        logger.debug("get menu: {}", content);
        MenuWrapper menuWrapper = JsonMapper.nonEmptyMapper().fromJson(content, MenuWrapper.class);
        return menuWrapper.getMenu();
    }

}

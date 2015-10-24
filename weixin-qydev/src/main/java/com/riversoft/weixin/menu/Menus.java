package com.riversoft.weixin.menu;

import com.riversoft.weixin.base.Settings;
import com.riversoft.weixin.base.WxClient;
import com.riversoft.weixin.menu.bean.Menu;
import com.riversoft.weixin.menu.bean.MenuWrapper;
import com.riversoft.weixin.util.JsonMapper;
import com.riversoft.weixin.base.WxEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by exizhai on 9/25/2015.
 */
public class Menus {

    private static Logger logger = LoggerFactory.getLogger(Menus.class);
    private static Menus menus = null;
    private WxClient wxClient;

    public static Menus defaultMenus() {
        if (menus == null) {
            menus = new Menus();
            menus.setWxClient(WxClient.defaultWxClient());
        }

        return menus;
    }

    public void setWxClient(WxClient wxClient) {
        this.wxClient = wxClient;
    }

    public void create(int agent, Menu menu) {
        String url = WxEndpoint.get("url.menu.create");

        String json = JsonMapper.nonEmptyMapper().toJson(menu);
        logger.info("update menu: {}", json);
        wxClient.post(String.format(url, agent), json);
    }

    public void create(Menu menu) {
        create(Settings.defaultSettings().getDefaultAgent(), menu);
    }

    public void delete() {
        delete(Settings.defaultSettings().getDefaultAgent());
    }

    public void delete(int agent) {
        String url = WxEndpoint.get("url.menu.delete");
        wxClient.get(String.format(url, agent));
    }

    public Menu list(int agent) {
        String url = WxEndpoint.get("url.menu.list");
        String content = wxClient.get(String.format(url, agent));

        logger.debug("list menu: {}", content);

        MenuWrapper menuWrapper = JsonMapper.nonEmptyMapper().fromJson(content, MenuWrapper.class);
        return menuWrapper.getMenu();
    }

    public Menu list() {
        return list(Settings.defaultSettings().getDefaultAgent());
    }

}

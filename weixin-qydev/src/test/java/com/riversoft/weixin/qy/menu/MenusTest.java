package com.riversoft.weixin.qy.menu;

import com.riversoft.weixin.qy.base.DefaultSettings;
import com.riversoft.weixin.qy.menu.bean.Menu;
import com.riversoft.weixin.qy.menu.bean.MenuItem;
import com.riversoft.weixin.qy.menu.bean.MenuType;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by exizhai on 9/27/2015.
 */
public class MenusTest {

    @Test
    public void testList() {
        Menu menu = Menus.defaultMenus().list(DefaultSettings.defaultSettings().getDefaultAgent());
        Assert.assertNotNull(menu);
    }

    @Ignore
    public void testCreate() {
        Menu menu = Menus.defaultMenus().list(DefaultSettings.defaultSettings().getDefaultAgent());
        MenuItem menuItem = new MenuItem().key("test-click").type(MenuType.click).name("点我");
        menu.add(menuItem);
        Menus.defaultMenus().create(menu);
    }

}

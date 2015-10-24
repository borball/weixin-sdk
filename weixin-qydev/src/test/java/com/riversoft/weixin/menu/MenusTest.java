package com.riversoft.weixin.menu;

import com.riversoft.weixin.base.Settings;
import com.riversoft.weixin.menu.bean.Menu;
import com.riversoft.weixin.menu.bean.MenuItem;
import com.riversoft.weixin.menu.bean.MenuType;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by exizhai on 9/27/2015.
 */
public class MenusTest {

    @Test
    public void testList() {
        Menu menu = Menus.defaultMenus().list(Settings.defaultSettings().getDefaultAgent());
        Assert.assertNotNull(menu);
    }

    @Ignore
    public void testCreate() {
        Menu menu = Menus.defaultMenus().list(Settings.defaultSettings().getDefaultAgent());
        MenuItem menuItem = new MenuItem().key("test-click").type(MenuType.click).name("点我");
        menu.add(menuItem);
        Menus.defaultMenus().create(menu);
    }

}

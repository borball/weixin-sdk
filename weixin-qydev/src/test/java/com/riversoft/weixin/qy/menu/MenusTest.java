package com.riversoft.weixin.qy.menu;

import com.riversoft.weixin.common.menu.Menu;
import com.riversoft.weixin.common.menu.MenuItem;
import com.riversoft.weixin.common.menu.MenuType;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by exizhai on 9/27/2015.
 */
public class MenusTest {

    @Test
    public void testList() {
        Menu menu = Menus.defaultMenus().get(71);
        Assert.assertNotNull(menu);
    }

    @Ignore
    public void testCreate() {
        Menu menu = Menus.defaultMenus().get(45);
        MenuItem menuItem = new MenuItem().key("test-click").type(MenuType.click).name("点我");
        menu.add(menuItem);
        Menus.defaultMenus().create(45, menu);
    }

}

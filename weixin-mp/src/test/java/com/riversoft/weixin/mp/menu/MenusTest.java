package com.riversoft.weixin.mp.menu;

import com.riversoft.weixin.common.menu.Menu;
import com.riversoft.weixin.common.menu.MenuItem;
import com.riversoft.weixin.common.menu.MenuType;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by exizhai on 9/27/2015.
 */
public class MenusTest {

    @Test
    public void testGet() {
        Menu menu = Menus.defaultMenus().get();
        Assert.assertNotNull(menu);
    }

    @Test
    public void testCreate() {
        Menu menu = new Menu();

        MenuItem menuWant = new MenuItem().key("want").type(MenuType.click).name("\ue415我要");
        MenuItem menuQuery = new MenuItem().key("query").type(MenuType.click).name("查询");
        MenuItem menuBuy = new MenuItem().key("buy").type(MenuType.click).name("购买");
        menuWant.add(menuQuery);
        menuWant.add(menuBuy);
        menu.add(menuWant);

        MenuItem menuService = new MenuItem().key("service").type(MenuType.click).name("\ue506服务");
        MenuItem menuAsk = new MenuItem().key("ask").type(MenuType.click).name("咨询");
        MenuItem menuComplain = new MenuItem().key("complain").type(MenuType.click).name("投诉");
        menuService.add(menuAsk);
        menuService.add(menuComplain);
        menu.add(menuService);

        MenuItem menuAbout = new MenuItem().key("about").type(MenuType.click).name("\ue418关于");
        MenuItem menuItem = new MenuItem().key("ask").type(MenuType.click).name("条款");
        MenuItem menuContact = new MenuItem().key("contact").type(MenuType.click).name("联系");
        menuAbout.add(menuItem);
        menuAbout.add(menuContact);
        menu.add(menuAbout);

        Menus.defaultMenus().create(menu);
    }

}

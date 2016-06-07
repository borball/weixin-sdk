package com.riversoft.weixin.mp.menu;

import com.riversoft.weixin.common.menu.*;
import com.riversoft.weixin.common.user.Gender;
import com.riversoft.weixin.mp.menu.bean.MenuConfig;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by exizhai on 9/27/2015.
 */
public class MenusTest {

    @Test
    public void testDelete() {
        Menus.defaultMenus().delete();
    }

    @Test
    public void testGet() {
        Menu menu = Menus.defaultMenus().get();
        Assert.assertNotNull(menu);
    }

    @Test
    public void testGetMenuConfig() {
        MenuConfig menu = Menus.defaultMenus().getMenuConfig();
        Assert.assertNotNull(menu);
    }

    @Test
    public void testList() {
        List<Menu> menus = Menus.defaultMenus().list();
        Assert.assertNotNull(menus);
    }

    @Test
    public void testCreate() {
        {
            Menu menuMale = new Menu();

            MenuItem menuWant = new MenuItem().key("want").type(MenuType.click).name("\ue415我要");
            MenuItem menuQuery = new MenuItem().key("query").type(MenuType.click).name("查询");
            MenuItem menuBuy = new MenuItem().key("buy").type(MenuType.click).name("购买");
            menuWant.add(menuQuery);
            menuWant.add(menuBuy);
            menuMale.add(menuWant);

            MenuItem menuService = new MenuItem().key("service").type(MenuType.click).name("\ue506服务");
            MenuItem menuAsk = new MenuItem().key("ask").type(MenuType.click).name("\ue418咨询");
            MenuItem menuComplain = new MenuItem().key("complain").type(MenuType.click).name("投诉");
            menuService.add(menuAsk);
            menuService.add(menuComplain);
            menuMale.add(menuService);

            MenuItem menuAbout = new MenuItem().key("about").type(MenuType.click).name("\ue418关于");
            MenuItem menuItem = new MenuItem().key("ask").type(MenuType.click).name("条款");
            MenuItem menuContact = new MenuItem().key("contact").type(MenuType.click).name("联系");
            menuAbout.add(menuItem);
            menuAbout.add(menuContact);
            menuMale.add(menuAbout);

            Menus.defaultMenus().create(menuMale);

        }
        {
            RuleMenu menuFemale = new RuleMenu();

            MenuItem menuWant = new MenuItem().key("want").type(MenuType.click).name("女人我要");
            MenuItem menuRefer = new MenuItem().key("refer").type(MenuType.click).name("女人推荐");
            MenuItem menuQuery = new MenuItem().key("query").type(MenuType.click).name("女人查询");
            menuWant.add(menuRefer);
            menuWant.add(menuQuery);
            menuFemale.add(menuWant);

            MenuItem menuService = new MenuItem().key("service").type(MenuType.click).name("女人服务");
            MenuItem menuAsk = new MenuItem().key("ask").type(MenuType.click).name("女人咨询");
            MenuItem menuComplain = new MenuItem().key("complain").type(MenuType.click).name("女人投诉");
            MenuItem menuH5 = new MenuItem().key("h5").type(MenuType.view).name("H5").url("http://wxtest.gzriver.com/oS-I8EwVL9X.view");
            menuService.add(menuAsk);
            menuService.add(menuComplain);
            menuService.add(menuH5);
            menuFemale.add(menuService);

            MenuItem menuAbout = new MenuItem().key("about").type(MenuType.click).name("女人客服");
            MenuItem menuItem = new MenuItem().key("item").type(MenuType.click).name("女人条款");
            MenuItem menuContact = new MenuItem().key("contact").type(MenuType.click).name("女人联系");
            menuAbout.add(menuItem);
            menuAbout.add(menuContact);
            menuFemale.add(menuAbout);

            Rule rule = new Rule();
            rule.setSex(Gender.FEMALE);
            menuFemale.rule(rule);
            Menus.defaultMenus().create(menuFemale);
        }
    }

}

package com.riversoft.weixin.menu;

import com.riversoft.weixin.base.Settings;
import com.riversoft.weixin.menu.bean.Menu;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by exizhai on 9/27/2015.
 */
public class MenusTest {

    @Test
    public void testList() {
        Menu menu = Menus.defaultMenus().list(Settings.buildIn().getDefaultAgent());
        Assert.assertNotNull(menu);
    }

}

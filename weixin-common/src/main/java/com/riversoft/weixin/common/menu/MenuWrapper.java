package com.riversoft.weixin.common.menu;

import java.io.Serializable;

/**
 * Created by exizhai on 9/27/2015.
 */
public class MenuWrapper implements Serializable {

    private Menu menu;

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
}

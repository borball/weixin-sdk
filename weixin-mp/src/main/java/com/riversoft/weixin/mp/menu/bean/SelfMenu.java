package com.riversoft.weixin.mp.menu.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.riversoft.weixin.common.menu.Menu;
import com.riversoft.weixin.common.util.BooleanDeserializer;
import com.riversoft.weixin.common.util.BooleanSerializer;

/**
 * @borball on 5/26/2016.
 */
public class SelfMenu {

    @JsonProperty("is_menu_open")
    @JsonDeserialize(using = BooleanDeserializer.class)
    @JsonSerialize(using = BooleanSerializer.class)
    private boolean enabled;

    @JsonProperty("selfmenu_info")
    private Menu menu;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
}

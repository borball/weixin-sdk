package com.riversoft.weixin.mp.template;

/**
 * Created by exizhai on 12/16/2015.
 */
public class Item {

    private String value;
    private String color;

    public Item() {
    }

    public Item(String value, String color) {
        this.value = value;
        this.color = color;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}

package com.riversoft.weixin.common.menu;

/**
 * Created by exizhai on 9/25/2015.
 */
public enum MenuType {

    /**
     * 订阅号，服务号和企业号支持
     */
    click,
    view,
    scancode_push,
    scancode_waitmsg,
    pic_sysphoto,
    pic_photo_or_album,
    pic_weixin,
    location_select,

    /**
     * 订阅号和服务号支持
     */
    media_id,
    view_limited,

    /**
     * 小程序
     */
    miniprogram

}

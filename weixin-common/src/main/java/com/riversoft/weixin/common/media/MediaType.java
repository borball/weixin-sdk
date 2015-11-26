package com.riversoft.weixin.common.media;

/**
 * Created by exizhai on 10/6/2015.
 */
public enum MediaType {

    image,
    voice,
    video,
    news,
    mpnews,

    /**
     * 企业号特有
     */
    file,

    /**
     * 订阅号服务号特有
     */
    thumb

}

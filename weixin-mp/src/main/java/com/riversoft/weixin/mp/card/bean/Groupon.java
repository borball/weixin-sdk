package com.riversoft.weixin.mp.card.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 优惠券
 * Created by exizhai on 11/28/2015.
 */
public class Groupon extends AbstractCard {

    /**
     * 团购券专用，团购详情。
     */
    @JsonProperty("default_detail")
    private String detail;

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

}

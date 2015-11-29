package com.riversoft.weixin.mp.card.bean;

/**
 * 礼品券
 * Created by exizhai on 11/28/2015.
 */
public class Gift extends AbstractCard {

    /**
     * 礼品券专用，填写礼品的名称
     */
    private String gift;

    public String getGift() {
        return gift;
    }

    public void setGift(String gift) {
        this.gift = gift;
    }
}

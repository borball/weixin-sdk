package com.riversoft.weixin.mp.card.bean;

/**
 * 折扣券
 * Created by exizhai on 11/28/2015.
 */
public class Discount extends AbstractCard {

    /**
     * 折扣券专用，表示打折额度（百分比）。填30就是七折。
     */
    private int discount;

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
}

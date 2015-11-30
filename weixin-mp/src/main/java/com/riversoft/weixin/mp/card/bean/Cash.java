package com.riversoft.weixin.mp.card.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 代金券
 * Created by exizhai on 11/28/2015.
 */
public class Cash extends AbstractCard {

    /**
     * 代金券专用，表示起用金额（单位为分）,如果无起用门槛则填0。
     */
    @JsonProperty("least_cost")
    private int leastCost;

    /**
     * 代金券专用，表示减免金额。（单位为分）
     */
    @JsonProperty("reduce_cost")
    private int reduceCost;

    public int getLeastCost() {
        return leastCost;
    }

    public void setLeastCost(int leastCost) {
        this.leastCost = leastCost;
    }

    public int getReduceCost() {
        return reduceCost;
    }

    public void setReduceCost(int reduceCost) {
        this.reduceCost = reduceCost;
    }
}

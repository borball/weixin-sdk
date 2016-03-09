package com.riversoft.weixin.mp.template;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @borball on 3/8/2016.
 */
public class Industry {

    @JsonProperty("primary_industry")
    private String primary;

    @JsonProperty("secondary_industry")
    private String secondary;

    public Industry() {
    }

    public Industry(String primary, String secondary) {
        this.primary = primary;
        this.secondary = secondary;
    }

    public String getPrimary() {
        return primary;
    }

    public void setPrimary(String primary) {
        this.primary = primary;
    }

    public String getSecondary() {
        return secondary;
    }

    public void setSecondary(String secondary) {
        this.secondary = secondary;
    }
}

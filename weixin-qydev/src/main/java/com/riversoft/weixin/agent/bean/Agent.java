package com.riversoft.weixin.agent.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.riversoft.weixin.util.BooleanDeserializer;
import com.riversoft.weixin.util.BooleanSerializer;

/**
 * Created by exizhai on 9/25/2015.
 */
public class Agent extends WritableAgent {

    @JsonProperty("square_logo_url")
    private String squareLogUrl;

    @JsonProperty("round_logo_url")
    private String roundLogUrl;

    @JsonProperty("close")
    @JsonSerialize(using = BooleanSerializer.class)
    @JsonDeserialize(using = BooleanDeserializer.class)
    private boolean close;

    public String getSquareLogUrl() {
        return squareLogUrl;
    }

    public void setSquareLogUrl(String squareLogUrl) {
        this.squareLogUrl = squareLogUrl;
    }

    public String getRoundLogUrl() {
        return roundLogUrl;
    }

    public void setRoundLogUrl(String roundLogUrl) {
        this.roundLogUrl = roundLogUrl;
    }

    public boolean isClose() {
        return close;
    }

    public void setClose(boolean close) {
        this.close = close;
    }

}

package com.riversoft.weixin.mp.event.care;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.riversoft.weixin.common.event.EventRequest;

/**
 * Created by exizhai on 11/22/2015.
 */
public class SessionForwardEvent extends EventRequest {

    @JsonProperty("FromKfAccount")
    private String fromCare;

    @JsonProperty("ToKfAccount")
    private String toCare;

    public String getFromCare() {
        return fromCare;
    }

    public void setFromCare(String fromCare) {
        this.fromCare = fromCare;
    }

    public String getToCare() {
        return toCare;
    }

    public void setToCare(String toCare) {
        this.toCare = toCare;
    }
}

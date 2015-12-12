package com.riversoft.weixin.mp.event.poi;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.riversoft.weixin.common.event.EventRequest;

/**
 * Created by exizhai on 11/30/2015.
 */
public class PoiApprovalEvent extends EventRequest {

    @JsonProperty("UniqId")
    @JacksonXmlCData
    private String sid;

    @JsonProperty("PoiId")
    @JacksonXmlCData
    private String poiId;

    @JsonProperty("Result")
    @JacksonXmlCData
    private String result;

    private boolean success;

    @JsonProperty("Msg")
    @JacksonXmlCData
    private String message;

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getPoiId() {
        return poiId;
    }

    public void setPoiId(String poiId) {
        this.poiId = poiId;
    }

    public boolean isSuccess() {
        return "succ".equalsIgnoreCase(this.result);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setResult(String result) {
        this.result = result;
    }
}

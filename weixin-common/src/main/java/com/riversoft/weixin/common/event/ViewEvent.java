package com.riversoft.weixin.common.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;

/**
 * Created by exizhai on 9/30/2015.
 */
public class ViewEvent extends EventRequest {

    @JsonProperty("EventKey")
    @JacksonXmlCData
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

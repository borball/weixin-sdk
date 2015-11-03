package com.riversoft.weixin.qy.message.request.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.riversoft.weixin.qy.message.base.EventType;
import com.riversoft.weixin.qy.message.request.XmlRequest;

/**
 * Created by exizhai on 9/30/2015.
 */
public class EventXmlRequest extends XmlRequest {

    @JsonProperty("Event")
    @JacksonXmlCData
    private EventType eventType;

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }
}

package com.riversoft.weixin.common.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;

/**
 * Created by exizhai on 11/14/2015.
 */
public class LocationSelectEvent extends EventRequest {

    @JsonProperty("EventKey")
    @JacksonXmlCData
    private String eventKey;

    @JsonProperty("SendLocationInfo")
    private SendLocationInfo sendLocationInfo;

    public String getEventKey() {
        return eventKey;
    }

    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }

    public SendLocationInfo getSendLocationInfo() {
        return sendLocationInfo;
    }

    public void setSendLocationInfo(SendLocationInfo sendLocationInfo) {
        this.sendLocationInfo = sendLocationInfo;
    }


    public static class SendLocationInfo {

        @JsonProperty("Location_X")
        @JacksonXmlCData
        private String x;

        @JsonProperty("Location_Y")
        @JacksonXmlCData
        private String y;

        @JsonProperty("Scale")
        @JacksonXmlCData
        private String scale;

        @JsonProperty("Label")
        @JacksonXmlCData
        private String label;

        @JsonProperty("Poiname")
        @JacksonXmlCData
        private String poiName;

        public String getX() {
            return x;
        }

        public void setX(String x) {
            this.x = x;
        }

        public String getY() {
            return y;
        }

        public void setY(String y) {
            this.y = y;
        }

        public String getScale() {
            return scale;
        }

        public void setScale(String scale) {
            this.scale = scale;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getPoiName() {
            return poiName;
        }

        public void setPoiName(String poiName) {
            this.poiName = poiName;
        }
    }
}

package com.riversoft.weixin.common.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;

/**
 * Created by exizhai on 11/14/2015.
 */
public class ScanEvent extends EventRequest {

    @JsonProperty("EventKey")
    @JacksonXmlCData
    private String eventKey;

    @JsonProperty("ScanCodeInfo")
    private ScanCodeInfo scanCodeInfo;

    public String getEventKey() {
        return eventKey;
    }

    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }

    public ScanCodeInfo getScanCodeInfo() {
        return scanCodeInfo;
    }

    public void setScanCodeInfo(ScanCodeInfo scanCodeInfo) {
        this.scanCodeInfo = scanCodeInfo;
    }

    public static class ScanCodeInfo {

        @JsonProperty("ScanType")
        @JacksonXmlCData
        private String scanType;

        @JsonProperty("ScanResult")
        @JacksonXmlCData
        private String scanResult;

        public String getScanType() {
            return scanType;
        }

        public void setScanType(String scanType) {
            this.scanType = scanType;
        }

        public String getScanResult() {
            return scanResult;
        }

        public void setScanResult(String scanResult) {
            this.scanResult = scanResult;
        }
    }

}

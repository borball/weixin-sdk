package com.riversoft.weixin.common.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;

import java.util.List;

/**
 * Created by exizhai on 11/14/2015.
 */
public class PhotoEvent extends EventRequest {

    @JsonProperty("EventKey")
    @JacksonXmlCData
    private String eventKey;

    @JsonProperty("SendPicsInfo")
    private SendPicsInfo sendPicsInfo;

    public String getEventKey() {
        return eventKey;
    }

    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }

    public SendPicsInfo getSendPicsInfo() {
        return sendPicsInfo;
    }

    public void setSendPicsInfo(SendPicsInfo sendPicsInfo) {
        this.sendPicsInfo = sendPicsInfo;
    }

    public static class SendPicsInfo {

        @JsonProperty("Count")
        private int count;
        @JsonProperty("PicList")
        private List<Item> items;

        public SendPicsInfo() {
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<Item> getItems() {
            return items;
        }

        public void setItems(List<Item> items) {
            this.items = items;
        }

        public static class Item {

            @JsonProperty("PicMd5Sum")
            @JacksonXmlCData
            private String picMd5Sum;

            public Item() {
            }

            public String getPicMd5Sum() {
                return picMd5Sum;
            }

            public void setPicMd5Sum(String picMd5Sum) {
                this.picMd5Sum = picMd5Sum;
            }
        }
    }

}

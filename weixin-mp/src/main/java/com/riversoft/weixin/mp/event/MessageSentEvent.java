package com.riversoft.weixin.mp.event;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.riversoft.weixin.common.event.EventRequest;

/**
 * Created by exizhai on 11/23/2015.
 */
public class MessageSentEvent extends EventRequest {

    @JsonProperty("MsgID")
    private int msgId;

    @JacksonXmlCData
    private String status;

    @JsonIgnore
    private int statusCode;

    @JsonProperty("TotalCount")
    private int total;

    @JsonProperty("FilterCount")
    private int filter;

    @JsonProperty("SentCount")
    private int sent;

    @JsonProperty("ErrorCount")
    private int error;

    public int getMsgId() {
        return msgId;
    }

    public void setMsgId(int msgId) {
        this.msgId = msgId;
    }

    /**
     * 0 成功
     * err(10001), 涉嫌广告      err(20001), 涉嫌政治
     * err(20004), 涉嫌社会      err(20002), 涉嫌色情
     * err(20006), 涉嫌违法犯罪   err(20008), 涉嫌欺诈
     * err(20013), 涉嫌版权      err(22000), 涉嫌互推(互相宣传)
     * err(21000), 涉嫌其他
     *
     * @return
     */
    public int getStatusCode() {
        //TODO
        return statusCode;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getFilter() {
        return filter;
    }

    public void setFilter(int filter) {
        this.filter = filter;
    }

    public int getSent() {
        return sent;
    }

    public void setSent(int sent) {
        this.sent = sent;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

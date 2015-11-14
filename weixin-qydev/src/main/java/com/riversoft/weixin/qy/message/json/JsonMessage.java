package com.riversoft.weixin.qy.message.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.riversoft.weixin.common.message.Message;
import com.riversoft.weixin.common.message.MsgType;

import java.io.Serializable;

/**
 * Created by exizhai on 9/25/2015.
 */
public abstract class JsonMessage implements Message, Serializable {

    @JsonProperty("msgtype")
    protected MsgType msgType;
    @JsonProperty("touser")
    private String toUser;
    @JsonProperty("toparty")
    private String toParty;
    @JsonProperty("totag")
    private String toTag;
    @JsonProperty("agentid")
    private int agentId;

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public String getToParty() {
        return toParty;
    }

    public void setToParty(String toParty) {
        this.toParty = toParty;
    }

    public String getToTag() {
        return toTag;
    }

    public void setToTag(String toTag) {
        this.toTag = toTag;
    }

    public int getAgentId() {
        return agentId;
    }

    public void setAgentId(int agentId) {
        this.agentId = agentId;
    }

    public MsgType getMsgType() {
        return msgType;
    }

    public JsonMessage agentId(int agentId) {
        this.agentId = agentId;
        return this;
    }

    public JsonMessage toUser(String toUser) {
        this.toUser = toUser;
        return this;
    }

    public JsonMessage toParty(String toParty) {
        this.toParty = toParty;
        return this;
    }

    public JsonMessage toTag(String toTag) {
        this.toTag = toTag;
        return this;
    }

}

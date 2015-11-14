package com.riversoft.weixin.qy.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.riversoft.weixin.common.event.ClickEvent;

/**
 * Created by exizhai on 9/30/2015.
 */
public class QyClickEvent extends ClickEvent {

    @JsonProperty("AgentID")
    private int agentId;

    public int getAgentId() {
        return agentId;
    }

    public void setAgentId(int agentId) {
        this.agentId = agentId;
    }

}

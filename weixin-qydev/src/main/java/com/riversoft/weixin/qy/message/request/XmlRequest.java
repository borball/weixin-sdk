package com.riversoft.weixin.qy.message.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.riversoft.weixin.qy.message.xml.XmlMessageHeader;

/**
 * Created by exizhai on 9/29/2015.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class XmlRequest extends XmlMessageHeader {

    @JsonProperty("AgentID")
    private int agentId;

    public int getAgentId() {
        return agentId;
    }

    public void setAgentId(int agentId) {
        this.agentId = agentId;
    }

}

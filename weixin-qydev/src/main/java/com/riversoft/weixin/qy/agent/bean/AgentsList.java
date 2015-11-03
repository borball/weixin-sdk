package com.riversoft.weixin.qy.agent.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by exizhai on 9/26/2015.
 */
public class AgentsList {

    @JsonProperty("agentlist")
    private List<Agent> agents;

    public List<Agent> getAgents() {
        return agents;
    }

    public void setAgents(List<Agent> agents) {
        this.agents = agents;
    }
}

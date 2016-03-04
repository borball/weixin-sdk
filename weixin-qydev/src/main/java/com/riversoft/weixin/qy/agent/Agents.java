package com.riversoft.weixin.qy.agent;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.riversoft.weixin.common.WxClient;
import com.riversoft.weixin.common.util.JsonMapper;
import com.riversoft.weixin.qy.QyWxClientFactory;
import com.riversoft.weixin.qy.agent.bean.Agent;
import com.riversoft.weixin.qy.agent.bean.WritableAgent;
import com.riversoft.weixin.qy.base.CorpSetting;
import com.riversoft.weixin.qy.base.WxEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by exizhai on 9/25/2015.
 */
public class Agents {

    private static Logger logger = LoggerFactory.getLogger(Agents.class);

    private WxClient wxClient;

    public static Agents defaultAgents() {
        return with(CorpSetting.defaultSettings());
    }

    public static Agents with(CorpSetting corpSetting) {
        Agents agents = new Agents();
        agents.setWxClient(QyWxClientFactory.getInstance().with(corpSetting));
        return agents;
    }

    public void setWxClient(WxClient wxClient) {
        this.wxClient = wxClient;
    }

    public List<Agent> list() {
        String url = WxEndpoint.get("url.agent.list");
        String content = wxClient.get(url);

        logger.debug("list agents: {}", content);
        AgentsList agentsList = JsonMapper.nonEmptyMapper().fromJson(content, AgentsList.class);
        return agentsList.getAgents();
    }

    public Agent get(int id) {
        String url = WxEndpoint.get("url.agent.get");

        String agent = wxClient.get(String.format(url, id));
        logger.debug("get agent: {}", agent);
        return JsonMapper.nonEmptyMapper().fromJson(agent, Agent.class);
    }

    public void update(Agent agent) {
        WritableAgent writableAgent = new WritableAgent();

        writableAgent.setAgentId(agent.getAgentId());
        writableAgent.setName(agent.getName());
        writableAgent.setDescription(agent.getDescription());
        writableAgent.setReportLocationFlag(agent.getReportLocationFlag());
        writableAgent.setReportUserChange(agent.isReportUserChange());
        writableAgent.setReportUserEnter(agent.isReportUserEnter());
        writableAgent.setLogoMediaId(agent.getLogoMediaId());
        writableAgent.setRedirectDomain(agent.getRedirectDomain());

        String url = WxEndpoint.get("url.agent.set");

        String json = JsonMapper.nonEmptyMapper().toJson(writableAgent);
        logger.info("update agent: {}", json);
        wxClient.post(url, json);
    }

    /**
     * Created by exizhai on 9/26/2015.
     */
    public static class AgentsList {

        @JsonProperty("agentlist")
        private List<Agent> agents;

        public List<Agent> getAgents() {
            return agents;
        }

        public void setAgents(List<Agent> agents) {
            this.agents = agents;
        }
    }
}
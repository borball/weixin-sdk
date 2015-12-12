package com.riversoft.weixin.qy.agent;

import com.riversoft.weixin.qy.agent.bean.Agent;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by exizhai on 9/26/2015.
 */
public class AgentsTest {

    @Test
    public void testGet() {
        Agent agent = Agents.defaultAgents().get(45);

        Assert.assertNotNull(agent);
    }

    @Test
    public void testList() {
        List<Agent> list = Agents.defaultAgents().list();
        Assert.assertNotNull(list);
    }

    @Test
    public void testUpdate() {
        Agent agent = Agents.defaultAgents().get(45);
        agent.setName(agent.getName());
        agent.setDescription(agent.getDescription());
        Agents.defaultAgents().update(agent);

        agent = Agents.defaultAgents().get(45);

        Assert.assertNotNull(agent);
    }
}

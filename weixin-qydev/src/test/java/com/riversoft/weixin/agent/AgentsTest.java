package com.riversoft.weixin.agent;

import com.riversoft.weixin.WxPropLoader;
import com.riversoft.weixin.agent.bean.Agent;
import com.riversoft.weixin.base.Settings;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by exizhai on 9/26/2015.
 */
public class AgentsTest {

    @Test
    public void testGet() {
        Agent agent = Agents.defaultAgents().get(Settings.buildIn().getDefaultAgent());

        Assert.assertNotNull(agent);
    }

    @Test
    public void testList() {
        List<Agent> list = Agents.defaultAgents().list();
        Assert.assertNotNull(list);
    }

    @Test
    public void testUpdate() {
        Agent agent = Agents.defaultAgents().get(Settings.buildIn().getDefaultAgent());
        agent.setName(agent.getName());
        agent.setDescription(agent.getDescription());
        Agents.defaultAgents().update(agent);

        agent = Agents.defaultAgents().get(Settings.buildIn().getDefaultAgent());

        Assert.assertNotNull(agent);
    }
}

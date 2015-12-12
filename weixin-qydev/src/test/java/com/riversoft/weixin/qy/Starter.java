package com.riversoft.weixin.qy;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.riversoft.weixin.common.menu.Menu;
import com.riversoft.weixin.common.util.JsonMapper;
import com.riversoft.weixin.qy.agent.Agents;
import com.riversoft.weixin.qy.agent.bean.Agent;
import com.riversoft.weixin.qy.media.Medias;
import com.riversoft.weixin.common.media.MediaType;
import com.riversoft.weixin.qy.menu.Menus;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by exizhai on 10/6/2015.
 */
public class Starter {

    @Test
    public void testStart() throws IOException {
        String json = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("agent.json"));
        ExampleAgent exampleAgent = JsonMapper.nonEmptyMapper().fromJson(json, ExampleAgent.class);

        String logo = Medias.defaultMedias().upload(MediaType.image, this.getClass().getClassLoader().getResourceAsStream("media/image.png"), "image.png");
        exampleAgent.getAgent().setLogoMediaId(logo);

        Agents.defaultAgents().update(exampleAgent.getAgent());
        Menus.defaultMenus().delete(45);
        Menus.defaultMenus().create(45, exampleAgent.getMenu());
    }


    @JsonRootName("example-agent")
    public static class ExampleAgent {

        private Agent agent;

        private Menu menu;

        public Agent getAgent() {
            return agent;
        }

        public void setAgent(Agent agent) {
            this.agent = agent;
        }

        public Menu getMenu() {
            return menu;
        }

        public void setMenu(Menu menu) {
            this.menu = menu;
        }
    }
}

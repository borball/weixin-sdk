package com.riversoft.weixin;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.riversoft.weixin.agent.Agents;
import com.riversoft.weixin.agent.bean.Agent;
import com.riversoft.weixin.media.Medias;
import com.riversoft.weixin.media.bean.MediaType;
import com.riversoft.weixin.menu.Menus;
import com.riversoft.weixin.menu.bean.Menu;
import com.riversoft.weixin.util.JsonMapper;
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

        String logo = Medias.defaultMedias().upload(MediaType.image, this.getClass().getClassLoader().getResourceAsStream("media/image.png"), "png");
        exampleAgent.getAgent().setLogoMediaId(logo);

        Agents.defaultAgents().update(exampleAgent.getAgent());
        Menus.defaultMenus().delete();
        Menus.defaultMenus().create(exampleAgent.getMenu());
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

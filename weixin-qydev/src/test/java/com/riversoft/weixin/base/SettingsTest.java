package com.riversoft.weixin.base;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.riversoft.weixin.util.XmlObjectMapper;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by exizhai on 10/2/2015.
 */
public class SettingsTest {

    @Test
    public void test2Xml() throws JsonProcessingException {
        String corpId = "corp-id-test";
        String corpSecret = "corp-secret-test";

        Settings settings = new Settings();

        settings.setCorpId(corpId);

        CorpSetting defaultCorpSetting = new CorpSetting(corpId, corpSecret);
        CorpSetting secondCorpSetting = new CorpSetting(corpId, "second one");
        CorpSetting thirdCorpSetting = new CorpSetting(corpId, "third");

        Map<String, CorpSetting> corpSettings = new HashMap<>();
        corpSettings.put("default", defaultCorpSetting);
        corpSettings.put("second", secondCorpSetting);
        corpSettings.put("third", thirdCorpSetting);
        settings.setCorpSettings(corpSettings);

        AgentSetting defaultAgentSetting = new AgentSetting();
        defaultAgentSetting.setAgentId(1);
        defaultAgentSetting.setToken("default-token");
        defaultAgentSetting.setAesKey("default-aes");
        defaultAgentSetting.setCallbackUrl("default-callback-url");

        AgentSetting anotherAgentSetting = new AgentSetting();
        anotherAgentSetting.setAgentId(2);
        anotherAgentSetting.setToken("another-token");
        anotherAgentSetting.setAesKey("another-aes");
        anotherAgentSetting.setCallbackUrl("another-callback-url");

        AgentSetting thirdAgentSetting = new AgentSetting();
        thirdAgentSetting.setAgentId(3);
        thirdAgentSetting.setToken("third-token");
        thirdAgentSetting.setAesKey("third-aes");
        thirdAgentSetting.setCallbackUrl("third-callback-url");

        Map<String, AgentSetting> agentSettings = new HashMap<>();
        agentSettings.put("default", defaultAgentSetting);
        agentSettings.put("another", anotherAgentSetting);
        agentSettings.put("third", thirdAgentSetting);
        settings.setAgentSettings(agentSettings);

        System.out.println(XmlObjectMapper.defaultMapper().toXml(settings));

    }

    @Test
    public void testFromXml() throws IOException {
        String xml = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("base/setting-test.xml"));

        Settings settings = (Settings) XmlObjectMapper.defaultMapper().fromXml(xml, Settings.class);

        Assert.assertNotNull(settings);
        Assert.assertEquals("corp-id-test", settings.getCorpId());
        Assert.assertEquals(20, settings.getDefaultAgent());
        Assert.assertEquals(3, settings.getAgentSettings().size());
        Assert.assertEquals(3, settings.getCorpSettings().size());

        Assert.assertEquals(20, settings.getDefaultAgentSetting().getAgentId());
    }
}

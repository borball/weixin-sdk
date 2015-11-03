package com.riversoft.weixin.qy.base;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.riversoft.weixin.qy.util.XmlObjectMapper;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by exizhai on 10/2/2015.
 */
public class DefaultSettingsTest {

    @Test
    public void test2Xml() throws JsonProcessingException {
        String corpId = "corp-id-test";
        String corpSecret = "corp-secret-test";

        DefaultSettings defaultSettings = new DefaultSettings();

        defaultSettings.setCorpSetting(new CorpSetting(corpId, corpSecret));

        AgentSetting defaultAgentSetting = new AgentSetting();
        defaultAgentSetting.setAgentId(20);
        defaultAgentSetting.setToken("default-token");
        defaultAgentSetting.setAesKey("default-aes");
        defaultAgentSetting.setCallbackUrl("default-callback-url");

        defaultSettings.setAgentSetting(defaultAgentSetting);

        System.out.println(XmlObjectMapper.defaultMapper().toXml(defaultSettings));
    }

    @Test
    public void testFromXml() throws IOException {
        String xml = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("base/setting-test.xml"));

        DefaultSettings defaultSettings = (DefaultSettings) XmlObjectMapper.defaultMapper().fromXml(xml, DefaultSettings.class);

        Assert.assertNotNull(defaultSettings);
        Assert.assertEquals("corp-id-test", defaultSettings.getCorpSetting().getCorpId());
        Assert.assertEquals(29, defaultSettings.getDefaultAgent());
    }
}

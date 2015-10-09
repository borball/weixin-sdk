package com.riversoft.weixin.base;

import com.riversoft.weixin.exception.WxRuntimeException;
import com.riversoft.weixin.util.XmlObjectMapper;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Map;

/**
 * Created by exizhai on 10/1/2015.
 */
public class Settings implements Serializable {

    private static Logger logger = LoggerFactory.getLogger(Settings.class);

    private static Settings settings = null;
    private String corpId;
    private String defaultCorpSecret;
    private Map<String, CorpSetting> corpSettings;
    private Map<String, AgentSetting> agentSettings;

    public synchronized static Settings buildIn() {
        if (settings == null) {
            String xml = null;
            try {
                InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("wx-settings-test.xml");

                if (inputStream == null) {
                    inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("wx-settings.xml");
                }

                if (inputStream != null) {
                    xml = IOUtils.toString(inputStream);
                    settings = (Settings) XmlObjectMapper.defaultMapper().fromXml(xml, Settings.class);
                }
            } catch (IOException e) {
                throw new WxRuntimeException(999, "init wx-setting.xml failed:" + e.getMessage());
            }
        }

        logger.debug("Current settings: default agent:{}, all agents:{}", settings.getDefaultAgent(), settings.getAgentSettings().keySet());

        return settings;

    }

    public String getCorpId() {
        return corpId;
    }

    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    public String getDefaultCorpSecret() {
        return defaultCorpSecret;
    }

    public void setDefaultCorpSecret(String defaultCorpSecret) {
        this.defaultCorpSecret = defaultCorpSecret;
    }

    public int getDefaultAgent() {
        return this.getDefaultAgentSetting().getAgentId();
    }

    public Map<String, CorpSetting> getCorpSettings() {
        return corpSettings;
    }

    public void setCorpSettings(Map<String, CorpSetting> corpSettings) {
        this.corpSettings = corpSettings;
    }

    public Map<String, AgentSetting> getAgentSettings() {
        return agentSettings;
    }

    public void setAgentSettings(Map<String, AgentSetting> agentSettings) {
        this.agentSettings = agentSettings;
    }

    public AgentSetting getDefaultAgentSetting() {
        return this.getAgentSettings().get("default");
    }

    public CorpSetting getDefaultCorpSetting() {
        return new CorpSetting(corpId, defaultCorpSecret);
    }

}

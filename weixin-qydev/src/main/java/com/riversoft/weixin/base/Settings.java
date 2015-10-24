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

    private static Settings defaultSetting = null;
    private String corpId;
    private String defaultCorpSecret;
    private Map<String, CorpSetting> corpSettings;
    private Map<String, AgentSetting> agentSettings;

    public static void setDefault(Settings settings) {
        defaultSetting = settings;
    }

    public static Settings defaultSettings() {
        if (defaultSetting == null) {
            loadFromClasspath();
        }

        if(defaultSetting == null) {
            throw new WxRuntimeException(999, "当前系统没有设置缺省的corpId和corpSecret,请使用setDefault方法或者在classpath下面创建wx-settings.xml文件.");
        }
        return defaultSetting;
    }

    private static void loadFromClasspath() {
        try {
            InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("wx-settings-test.xml");

            if (inputStream == null) {
                inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("wx-settings.xml");
            }

            if (inputStream != null) {
                String xml = IOUtils.toString(inputStream);
                Settings settings = (Settings) XmlObjectMapper.defaultMapper().fromXml(xml, Settings.class);
                defaultSetting = settings;
            }
        } catch (IOException e) {
            logger.error("read settings from wx-settings-test.xml or wx-settings.xml failed:", e);
        }
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

package com.riversoft.weixin.qy.base;

import com.riversoft.weixin.common.util.XmlObjectMapper;
import com.riversoft.weixin.qy.exception.WxRuntimeException;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

/**
 * Created by exizhai on 10/1/2015.
 */
public class DefaultSettings implements Serializable {

    private static Logger logger = LoggerFactory.getLogger(DefaultSettings.class);

    private static DefaultSettings defaultSetting = null;

    private CorpSetting corpSetting;
    private AgentSetting agentSetting;

    public static void setDefault(DefaultSettings defaultSettings) {
        defaultSetting = defaultSettings;
    }

    public static DefaultSettings defaultSettings() {
        if (defaultSetting == null) {
            loadFromSystemProperties();
        }

        if(defaultSetting == null) {
            loadFromClasspath();
        }

        if (defaultSetting == null) {
            throw new WxRuntimeException(999, "当前系统没有设置缺省的corpId和corpSecret,请使用setDefault方法或者在classpath下面创建wx-qy-settings.xml文件.");
        }
        return defaultSetting;
    }

    private static void loadFromSystemProperties() {
        if(System.getProperties().contains("qyconfig")) {
            String xml = System.getProperties().getProperty("qyconfig", "");
            if(xml == null || "".equals(xml)) {
                return;
            } else {
                try {
                    DefaultSettings defaultSettings = (DefaultSettings) XmlObjectMapper.defaultMapper().fromXml(xml, DefaultSettings.class);
                    defaultSetting = defaultSettings;
                } catch (IOException e) {
                }
            }
        }
    }

    private static void loadFromClasspath() {
        try {
            InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("wx-qy-settings-test.xml");

            if (inputStream == null) {
                inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("wx-qy-settings.xml");
            }

            if (inputStream != null) {
                String xml = IOUtils.toString(inputStream);
                DefaultSettings defaultSettings = (DefaultSettings) XmlObjectMapper.defaultMapper().fromXml(xml, DefaultSettings.class);
                defaultSetting = defaultSettings;
            }
        } catch (IOException e) {
            logger.error("read settings from wx-qy-settings-test.xml or wx-qy-settings.xml failed:", e);
        }
    }

    public CorpSetting getCorpSetting() {
        return corpSetting;
    }

    public void setCorpSetting(CorpSetting corpSetting) {
        this.corpSetting = corpSetting;
    }

    public AgentSetting getAgentSetting() {
        return agentSetting;
    }

    public void setAgentSetting(AgentSetting agentSetting) {
        this.agentSetting = agentSetting;
    }

    public int getDefaultAgent() {
        return agentSetting.getAgentId();
    }
}

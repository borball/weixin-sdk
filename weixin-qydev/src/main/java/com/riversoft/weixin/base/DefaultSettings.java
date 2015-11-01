package com.riversoft.weixin.base;

import com.riversoft.weixin.exception.WxRuntimeException;
import com.riversoft.weixin.util.XmlObjectMapper;
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
                DefaultSettings defaultSettings = (DefaultSettings) XmlObjectMapper.defaultMapper().fromXml(xml, DefaultSettings.class);
                defaultSetting = defaultSettings;
            }
        } catch (IOException e) {
            logger.error("read settings from wx-settings-test.xml or wx-settings.xml failed:", e);
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

    public int getDefaultAgent(){
        return agentSetting.getAgentId();
    }
}

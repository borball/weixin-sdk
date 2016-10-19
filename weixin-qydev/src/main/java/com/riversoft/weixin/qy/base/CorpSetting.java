package com.riversoft.weixin.qy.base;

import com.riversoft.weixin.common.exception.WxRuntimeException;
import com.riversoft.weixin.common.util.XmlObjectMapper;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by exizhai on 9/26/2015.
 */
public class CorpSetting {

    private static Logger logger = LoggerFactory.getLogger(CorpSetting.class);

    private static CorpSetting defaultSetting = null;

    private String corpId;
    private String corpSecret;
    private String tokenHolderClass;

    public CorpSetting() {
    }

    public CorpSetting(String corpId, String corpSecret) {
        this.corpId = corpId;
        this.corpSecret = corpSecret;
    }

    public static void setDefault(CorpSetting corpSetting) {
        defaultSetting = corpSetting;
    }

    public static CorpSetting defaultSettings() {
        if (defaultSetting == null) {
            loadFromSystemProperties();
        }

        if (defaultSetting == null) {
            loadFromClasspath();
        }

        if (defaultSetting == null) {
            throw new WxRuntimeException(999, "当前系统没有设置缺省的corpId和corpSecret,请使用setDefault方法或者在classpath下面创建wx-qy-settings.xml文件.");
        }
        return defaultSetting;
    }

    private static void loadFromSystemProperties() {
        if (System.getProperties().contains("qyconfig")) {
            logger.info("loading qy configuration from system properties...");
            String xml = System.getProperties().getProperty("qyconfig", "");
            logger.info("qyconfig: {}", xml);
            if (xml == null || "".equals(xml)) {
                return;
            } else {
                try {
                    CorpSetting defaultSettings = XmlObjectMapper.defaultMapper().fromXml(xml, CorpSetting.class);
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
                CorpSetting defaultSettings = XmlObjectMapper.defaultMapper().fromXml(xml, CorpSetting.class);
                defaultSetting = defaultSettings;
            }
        } catch (IOException e) {
            logger.error("read settings from wx-qy-settings-test.xml or wx-qy-settings.xml failed:", e);
        }
    }

    public String getCorpId() {
        return corpId;
    }

    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    public String getCorpSecret() {
        return corpSecret;
    }

    public void setCorpSecret(String corpSecret) {
        this.corpSecret = corpSecret;
    }

    public String getTokenHolderClass() {
        return tokenHolderClass;
    }

    public void setTokenHolderClass(String tokenHolderClass) {
        this.tokenHolderClass = tokenHolderClass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CorpSetting that = (CorpSetting) o;

        if (!corpId.equals(that.corpId)) return false;
        return corpSecret.equals(that.corpSecret);

    }

    @Override
    public int hashCode() {
        int result = corpId.hashCode();
        result = 31 * result + corpSecret.hashCode();
        return result;
    }
}

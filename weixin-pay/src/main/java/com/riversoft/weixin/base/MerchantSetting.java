package com.riversoft.weixin.base;

import com.riversoft.weixin.common.exception.WxRuntimeException;
import com.riversoft.weixin.common.util.XmlObjectMapper;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * 商户信息
 *
 * Created by exizhai on 11/22/2015.
 */
public class MerchantSetting {

    private static Logger logger = LoggerFactory.getLogger(MerchantSetting.class);

    private static MerchantSetting merchant = null;

    public static void setDefault(MerchantSetting merchantSetting) {
        merchant = merchantSetting;
    }

    public static MerchantSetting defaultMerchantSetting() {
        if (merchant == null) {
            loadFromSystemProperties();
        }

        if (merchant == null) {
            loadFromClasspath();
        }

        if (merchant == null) {
            throw new WxRuntimeException(999, "当前系统没有设置缺省的商户信息,请使用setDefault方法或者在classpath下面创建wx-pay-settings.xml文件.");
        }
        return merchant;
    }

    private static void loadFromSystemProperties() {
        if(System.getProperties().contains("payconfig")) {
            logger.info("loading pay configuration from system properties...");
            String xml = System.getProperties().getProperty("payconfig", "");
            logger.info("payconfig: {}", xml);
            if(xml == null || "".equals(xml)) {
                return;
            } else {
                try {
                    MerchantSetting setting = (MerchantSetting) XmlObjectMapper.defaultMapper().fromXml(xml, MerchantSetting.class);
                    merchant = setting;
                } catch (IOException e) {
                }
            }
        }
    }

    private static void loadFromClasspath() {
        try {
            InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("wx-apy-settings-test.xml");

            if (inputStream == null) {
                inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("wx-pay-settings.xml");
            }

            if (inputStream != null) {
                String xml = IOUtils.toString(inputStream);
                MerchantSetting setting = (MerchantSetting) XmlObjectMapper.defaultMapper().fromXml(xml, MerchantSetting.class);
                merchant = setting;
            }
        } catch (IOException e) {
            logger.error("read settings from wx-pay-settings-test.xml or wx-pay-settings.xml failed:", e);
        }
    }


    /**
     * 商户ID
     */
    private String id;

    /**
     * 商户名
     */
    private String name;

    /**
     * 商户的appId
     */
    private String appId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }
}

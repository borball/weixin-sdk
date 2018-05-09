package com.riversoft.weixin.pay.base;

import com.riversoft.weixin.common.cert.FilePathCertContent;
import com.riversoft.weixin.common.exception.WxRuntimeException;
import com.riversoft.weixin.common.util.XmlObjectMapper;
import com.riversoft.weixin.common.cert.CertContent;
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
public class PaySetting {

    private static class PayPathSetting {

        /**
         * 商户ID
         */
        private String mchId;

        /**
         * 商户的appId
         */
        private String appId;

        /**
         * 秘钥
         */
        private String key;

        /**
         * 证书密码
         */
        private String certPassword;
        /**
         * 证书路径
         */
        private String certPath;

        public String getMchId() {
            return mchId;
        }

        public void setMchId(String mchId) {
            this.mchId = mchId;
        }

        public String getAppId() {
            return appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getCertPassword() {
            return certPassword;
        }

        public void setCertPassword(String certPassword) {
            this.certPassword = certPassword;
        }

        public String getCertPath() {
            return certPath;
        }

        public void setCertPath(String certPath) {
            this.certPath = certPath;
        }

        public PaySetting toPaySetting(){
            PaySetting paySetting = new PaySetting();
            paySetting.setAppId(appId);
            paySetting.setKey(key);
            paySetting.setMchId(mchId);
            paySetting.setCertPassword(certPassword);
            paySetting.setCertContent(new FilePathCertContent(certPath));
            return paySetting;
        }
    }
    private static Logger logger = LoggerFactory.getLogger(PaySetting.class);

    private static PaySetting paySetting = null;

    public static void setDefault(PaySetting paySetting) {
        PaySetting.paySetting = paySetting;
    }

    public static PaySetting defaultSetting() {
        if (paySetting == null) {
            loadFromSystemProperties();
        }

        if (paySetting == null) {
            loadFromClasspath();
        }

        if (paySetting == null) {
            throw new WxRuntimeException(999, "当前系统没有设置缺省的商户信息,请使用setDefault方法或者在classpath下面创建wx-pay-settings.xml文件.");
        }
        return paySetting;
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
                    PayPathSetting setting = XmlObjectMapper.defaultMapper().fromXml(xml, PayPathSetting.class);
                    paySetting = setting.toPaySetting();
                } catch (IOException e) {
                }
            }
        }
    }

    private static void loadFromClasspath() {
        try {
            InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("wx-pay-settings-test.xml");

            if (inputStream == null) {
                inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("wx-pay-settings.xml");
            }

            if (inputStream != null) {
                String xml = IOUtils.toString(inputStream);
                PayPathSetting setting = XmlObjectMapper.defaultMapper().fromXml(xml, PayPathSetting.class);
                paySetting = setting.toPaySetting();
            }
        } catch (IOException e) {
            logger.error("read settings from wx-pay-settings-test.xml or wx-pay-settings.xml failed:", e);
        }
    }


    /**
     * 商户ID
     */
    private String mchId;

    /**
     * 商户的appId
     */
    private String appId;

    /**
     * 秘钥
     */
    private String key;

    /**
     * 证书密码
     */
    private String certPassword;

    private CertContent certContent;

    public CertContent getCertContent() {
        return certContent;
    }

    public void setCertContent(CertContent certContent) {
        this.certContent = certContent;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getCertPassword() {
        return certPassword;
    }

    public void setCertPassword(String certPassword) {
        this.certPassword = certPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PaySetting that = (PaySetting) o;

        if (!mchId.equals(that.mchId)) return false;
        return !(appId != null ? !appId.equals(that.appId) : that.appId != null);

    }

    @Override
    public int hashCode() {
        int result = mchId.hashCode();
        result = 31 * result + (appId != null ? appId.hashCode() : 0);
        return result;
    }
}

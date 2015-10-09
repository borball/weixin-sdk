package com.riversoft.weixin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Properties;

/**
 * Created by exizhai on 10/7/2015.
 */
public class WxPropLoader {

    private static Logger logger = LoggerFactory.getLogger(WxPropLoader.class);

    private final Properties properties;

    public WxPropLoader(InputStream inputStream) {
        properties = loadProperties(inputStream);
    }

    public Properties getProperties() {
        return properties;
    }

    private Properties loadProperties(InputStream inputStream) {
        Properties properties = new Properties();

        try {
            properties.load(inputStream);
        } catch (IOException e) {
            logger.error("Could not load properties:", e);
        }

        return properties;
    }

    private String getValue(String key) {
        String systemProperty = System.getProperty(key);
        if (systemProperty != null) {
            return systemProperty;
        }
        return properties.getProperty(key);
    }

    public String getProperty(String key) {
        String value = getValue(key);
        if (value == null) {
            throw new NoSuchElementException();
        }
        return value;
    }

    public String getProperty(String key, String defaultValue) {
        String value = getValue(key);
        return value != null ? value : defaultValue;
    }

    public Integer getInteger(String key) {
        String value = getValue(key);
        if (value == null) {
            throw new NoSuchElementException();
        }
        return Integer.valueOf(value);
    }

    public Integer getInteger(String key, Integer defaultValue) {
        String value = getValue(key);
        return value != null ? Integer.valueOf(value) : defaultValue;
    }

    public Double getDouble(String key) {
        String value = getValue(key);
        if (value == null) {
            throw new NoSuchElementException();
        }
        return Double.valueOf(value);
    }

    public Double getDouble(String key, Integer defaultValue) {
        String value = getValue(key);
        return value != null ? Double.valueOf(value) : defaultValue;
    }

    public Boolean getBoolean(String key) {
        String value = getValue(key);
        if (value == null) {
            throw new NoSuchElementException();
        }
        return Boolean.valueOf(value);
    }

    public Boolean getBoolean(String key, boolean defaultValue) {
        String value = getValue(key);
        return value != null ? Boolean.valueOf(value) : defaultValue;
    }
}

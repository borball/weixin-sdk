package com.riversoft.weixin.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Properties;

/**
 * Created by exizhai on 9/26/2015.
 */
public class PropertiesLoader {

    private static Logger logger = LoggerFactory.getLogger(PropertiesLoader.class);

    private static PropertiesLoader propertiesLoader = null;
    private final Properties properties;

    private PropertiesLoader() {
        properties = loadProperties();
    }

    public static synchronized PropertiesLoader getInstance() {
        if (propertiesLoader == null) {
            propertiesLoader = new PropertiesLoader();
        }

        return propertiesLoader;
    }

    public Properties getProperties() {
        return properties;
    }

    private Properties loadProperties() {
        Properties properties = new Properties();

        try {
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("wx.properties");
            properties.load(inputStream);
        } catch (IOException e) {
            logger.error("Could not load properties from classpath:wx.properties.", e);
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

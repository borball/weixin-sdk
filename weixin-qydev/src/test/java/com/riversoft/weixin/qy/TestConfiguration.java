package com.riversoft.weixin.qy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by exizhai on 10/7/2015.
 */
public class TestConfiguration {

    private static TestConfiguration instance;

    private static Logger logger = LoggerFactory.getLogger(TestConfiguration.class);

    private Properties properties;

    private TestConfiguration(){
        Properties properties = loadProperties(this.getClass().getClassLoader().getResourceAsStream("wx-test.properties"));
        this.properties = properties;
    }

    public static TestConfiguration getInstance(){
        if(instance == null) {
            instance = new TestConfiguration();
        }
        return instance;
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

    public String testUser(){
        return properties.getProperty("messages.test.user");
    }
}

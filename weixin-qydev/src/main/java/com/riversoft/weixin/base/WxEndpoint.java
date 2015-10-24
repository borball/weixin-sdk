package com.riversoft.weixin.base;

import com.riversoft.weixin.exception.WxRuntimeException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by exizhai on 9/26/2015.
 */
public class WxEndpoint {

    private static Properties endpoints;

    private static synchronized void loadProperties() {
        if(endpoints == null) {
            try {
                Properties properties = new Properties();
                InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream("wx-endpoint.properties");
                properties.load(inputStream);
                endpoints = properties;
            } catch (IOException e) {
                throw new WxRuntimeException(999, "cannot find resource wx-endpoint.properties from classpath.");
            }
        }
    }

    public static String get(String key) {
        loadProperties();
        return endpoints.getProperty(key);
    }

}

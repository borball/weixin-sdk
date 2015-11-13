package com.riversoft.weixin.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.IOException;

/**
 * Created by exizhai on 9/29/2015.
 */
public class XmlObjectMapper {

    private static XmlObjectMapper defaultXmlObjectMapper = null;
    private static XmlObjectMapper prettyFormatXmlObjectMapper = null;

    private XmlMapper xmlMapper;

    private XmlObjectMapper() {
        xmlMapper = new XmlMapper();
    }

    public synchronized static XmlObjectMapper defaultMapper() {
        if (defaultXmlObjectMapper == null) {
            defaultXmlObjectMapper = new XmlObjectMapper();
        }

        return defaultXmlObjectMapper;
    }

    public synchronized static XmlObjectMapper prettyFormatMapper() {
        if (prettyFormatXmlObjectMapper == null) {
            prettyFormatXmlObjectMapper = new XmlObjectMapper();
            prettyFormatXmlObjectMapper.xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
        }

        return prettyFormatXmlObjectMapper;
    }

    public String toXml(Object o) throws JsonProcessingException {
        return xmlMapper.writeValueAsString(o);
    }

    public Object fromXml(String xml, Class clazz) throws IOException {
        return xmlMapper.readValue(xml, clazz);
    }

}

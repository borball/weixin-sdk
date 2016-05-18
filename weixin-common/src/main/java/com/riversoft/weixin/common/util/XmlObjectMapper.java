package com.riversoft.weixin.common.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.IOException;

/**
 * Created by exizhai on 9/29/2015.
 */
public class XmlObjectMapper {

    private static XmlObjectMapper defaultXmlObjectMapper = null;
    private static XmlObjectMapper nonEmptyXmlObjectMapper = null;

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

    public synchronized static XmlObjectMapper nonEmptyMapper() {
        if (nonEmptyXmlObjectMapper == null) {
            nonEmptyXmlObjectMapper = new XmlObjectMapper();
            nonEmptyXmlObjectMapper.xmlMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        }
        return nonEmptyXmlObjectMapper;
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

    public <T> T fromXml(String xml, Class<T> clazz) throws IOException {
        return xmlMapper.readValue(xml, clazz);
    }

}

package com.riversoft.weixin.pay.payment;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @borball on 1/13/2017.
 */
public class AnySetter349Test {

    @Test
    public void testUnwrappedWithAny() throws Exception {
        final XmlMapper mapper = new XmlMapper();
        final String xml = "<xml>\n" +
                "<type>type</type>\n" +
                "<x>10</x>\n" +
                "<y>10</y>\n" +
                "<k1>k1</k1>\n" +
                "<k2>k1</k2>\n" +
                "<k3>k1</k3>\n" +
                "</xml>";

        Bean349 value = mapper.readValue(xml, Bean349.class);
        Assert.assertNotNull(value);
    }

    static class Bean349 {

        public String type;
        @JsonUnwrapped
        public IdentityDTO349 identity = null;

        private Map<String, Object> props = new HashMap<>();

        @JsonAnySetter
        public void addProperty(String key, Object value) {
            props.put(key, value);
        }

    }

    static class IdentityDTO349 {
        public int x, y;
    }
}

package com.riversoft.weixin.common.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.riversoft.weixin.common.user.Gender;

import java.io.IOException;

/**
 * Created by exizhai on 10/4/2015.
 */
public class GenderDeserializer extends JsonDeserializer<Gender> {

    @Override
    public Gender deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        return Gender.fromCode(Integer.valueOf(jsonParser.getValueAsString()));
    }
}

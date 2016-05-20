package com.riversoft.weixin.mp.poi.bean;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

/**
 * @borball on 5/20/2016.
 */
public class StateDeserializer extends JsonDeserializer<AvailableState> {

    @Override
    public AvailableState deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        return AvailableState.fromCode(Integer.valueOf(jsonParser.getValueAsString()));
    }
}

package com.riversoft.weixin.common.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.Date;

/**
 * Created by exizhai on 11/18/2015.
 */
public class DateDeserializer extends JsonDeserializer<Date> {

    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        //操蛋的微信，为啥不是毫秒
        long time = Long.valueOf(jsonParser.getText()) * 1000;
        return new Date(time);
    }
}

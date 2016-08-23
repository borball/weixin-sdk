package com.riversoft.weixin.common.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Date;

/**
 * Created by exizhai on 11/18/2015.
 */
public class DateSerializer extends JsonSerializer<Date> {

    @Override
    public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        //操蛋的微信，为啥不是毫秒
        long time = date.getTime();
        jsonGenerator.writeString(String.valueOf(time).substring(0, 10));
    }
}

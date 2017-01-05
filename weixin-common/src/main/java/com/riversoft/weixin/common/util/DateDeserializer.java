package com.riversoft.weixin.common.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by exizhai on 11/18/2015.
 */
public class DateDeserializer extends JsonDeserializer<Date> {

    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String date = jsonParser.getText();
        if(date.length() == 14) {
            SimpleDateFormat dataFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            try {
                return dataFormat.parse(date);
            } catch (ParseException e) {
                throw new IOException(e);
            }
        } else {
            //操蛋的微信，为啥不是毫秒
            long time = Long.valueOf(date) * 1000;
            return new Date(time);
        }
    }
}

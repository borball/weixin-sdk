package com.riversoft.weixin.qy.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.riversoft.weixin.qy.contact.user.AbstractUser;

import java.io.IOException;

/**
 * Created by exizhai on 10/4/2015.
 */
public class GenderSerializer extends JsonSerializer<AbstractUser.Gender> {

    @Override
    public void serialize(AbstractUser.Gender gender, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        //为何这里是string，status那里确实数字， 服务号也是数字，麻痹的
        if (AbstractUser.Gender.MALE == gender) jsonGenerator.writeString("1");
        else if (AbstractUser.Gender.FEMALE == gender) jsonGenerator.writeString("2");
        else jsonGenerator.writeString("0");
    }
}
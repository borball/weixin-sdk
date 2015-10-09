package com.riversoft.weixin.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.riversoft.weixin.contact.bean.user.AbstractUser;

import java.io.IOException;

/**
 * Created by exizhai on 10/4/2015.
 */
public class GenderSerializer extends JsonSerializer<AbstractUser.Gender> {

    @Override
    public void serialize(AbstractUser.Gender gender, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeNumber(AbstractUser.Gender.MALE == gender ? 1 : 2);
    }
}

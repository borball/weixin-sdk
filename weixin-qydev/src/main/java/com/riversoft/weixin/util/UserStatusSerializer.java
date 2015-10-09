package com.riversoft.weixin.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.riversoft.weixin.contact.bean.user.UserStatus;

import java.io.IOException;

/**
 * Created by exizhai on 10/4/2015.
 */
public class UserStatusSerializer extends JsonSerializer<UserStatus> {

    @Override
    public void serialize(UserStatus userStatus, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        if (UserStatus.FOLLOWED == userStatus) jsonGenerator.writeNumber(1);
        if (UserStatus.SUSPEND == userStatus) jsonGenerator.writeNumber(2);
        if (UserStatus.UN_FOLLOWED == userStatus) jsonGenerator.writeNumber(4);
    }
}

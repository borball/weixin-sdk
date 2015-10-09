package com.riversoft.weixin.contact.bean.user;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.riversoft.weixin.util.BooleanDeserializer;
import com.riversoft.weixin.util.BooleanSerializer;

/**
 * Created by exizhai on 10/4/2015.
 */
public class UpdateUser extends CreateUser {

    @JsonSerialize(using = BooleanSerializer.class)
    @JsonDeserialize(using = BooleanDeserializer.class)
    private boolean enable;

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}

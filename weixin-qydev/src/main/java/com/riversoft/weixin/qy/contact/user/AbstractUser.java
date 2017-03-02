package com.riversoft.weixin.qy.contact.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.riversoft.weixin.qy.util.GenderDeserializer;
import com.riversoft.weixin.qy.util.GenderSerializer;

import java.util.List;

/**
 * Created by exizhai on 9/25/2015.
 */
public abstract class AbstractUser extends SimpleUser {

    private String position;
    private String mobile;

    @JsonSerialize(using = GenderSerializer.class)
    @JsonDeserialize(using = GenderDeserializer.class)
    private Gender gender;

    private String email;

    @JsonProperty("weixinid")
    private String weixinId;

    @JsonProperty("extattr")
    private ExtAttr extAttr;

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWeixinId() {
        return weixinId;
    }

    public void setWeixinId(String weixinId) {
        this.weixinId = weixinId;
    }

    public ExtAttr getExtAttr() {
        return extAttr;
    }

    public void setExtAttr(ExtAttr extAttr) {
        this.extAttr = extAttr;
    }

    /**
     * Created by exizhai on 10/4/2015.
     */
    public enum Gender {

        MALE, FEMALE, UNKNOWN

    }

    public static class ExtAttr {

        @JsonUnwrapped
        private List<Attr> attrs;

        public List<Attr> getAttrs() {
            return attrs;
        }

        public void setAttrs(List<Attr> attrs) {
            this.attrs = attrs;
        }

        public static class Attr {

            private String name;
            private String value;

            public Attr(){
            }

            public Attr(String name, String value) {
                this.name = name;
                this.value = value;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

        }
    }
}

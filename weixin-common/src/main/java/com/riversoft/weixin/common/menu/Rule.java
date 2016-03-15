package com.riversoft.weixin.common.menu;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.riversoft.weixin.common.user.Gender;
import com.riversoft.weixin.common.util.GenderDeserializer;
import com.riversoft.weixin.common.util.GenderSerializer;

/**
 * @borball on 3/14/2016.
 */
public class Rule {

    @JsonProperty("group_id")
    private String group;

    @JsonDeserialize(using = GenderDeserializer.class)
    @JsonSerialize(using = GenderSerializer.class)
    private Gender sex;

    @JsonProperty("client_platform_type")
    private String platform;

    private String country;
    private String province;
    private String city;
    private String language;

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Gender getSex() {
        return sex;
    }

    public void setSex(Gender sex) {
        this.sex = sex;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}

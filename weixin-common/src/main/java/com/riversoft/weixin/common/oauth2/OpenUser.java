package com.riversoft.weixin.common.oauth2;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.riversoft.weixin.common.user.Gender;
import com.riversoft.weixin.common.util.GenderDeserializer;

/**
 * Created by exizhai on 12/17/2015.
 */
public class OpenUser {

    @JsonProperty(value = "openid")
    private String openId;

    @JsonProperty(value = "nickname")
    private String nickName;

    @JsonDeserialize(using = GenderDeserializer.class)
    private Gender sex;

    private String province;
    private String city;
    private String country;

    @JsonProperty(value = "headimgurl")
    private String headImgUrl;

    @JsonProperty(value = "unionid")
    private String unionId;

    private String[] privilege;

    public OpenUser() {
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Gender getSex() {
        return sex;
    }

    public void setSex(Gender sex) {
        this.sex = sex;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public String[] getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String[] privilege) {
        this.privilege = privilege;
    }
}

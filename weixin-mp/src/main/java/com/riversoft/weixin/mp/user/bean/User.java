package com.riversoft.weixin.mp.user.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.riversoft.weixin.common.user.Gender;
import com.riversoft.weixin.common.util.*;

import java.util.Date;
import java.util.List;

/**
 * Created by exizhai on 11/4/2015.
 */
public class User {

    @JsonProperty(value = "subscribe")
    @JsonSerialize(using = BooleanSerializer.class)
    @JsonDeserialize(using = BooleanDeserializer.class)
    private boolean subscribed;

    @JsonProperty(value = "openid")
    private String openId;

    @JsonProperty(value = "nickname")
    private String nickName;

    @JsonDeserialize(using = GenderDeserializer.class)
    @JsonSerialize(using = GenderSerializer.class)
    private Gender sex;
    private String city;
    private String country;
    private String province;
    private String language;

    @JsonProperty(value = "headimgurl")
    private String headImgUrl;

    @JsonProperty(value = "subscribe_time")
    @JsonDeserialize(using = DateDeserializer.class)
    private Date subscribedTime;

    @JsonProperty(value = "unionid")
    private String unionId;
    private String remark;

    @JsonProperty(value = "groupid")
    private int group;

    @JsonProperty(value = "tagid_list")
    private List<Integer> tags;

    public boolean isSubscribed() {
        return subscribed;
    }

    public void setSubscribed(boolean subscribed) {
        this.subscribed = subscribed;
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

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public Date getSubscribedTime() {
        return subscribedTime;
    }

    public void setSubscribedTime(Date subscribedTime) {
        this.subscribedTime = subscribedTime;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public List<Integer> getTags() {
        return tags;
    }

    public void setTags(List<Integer> tags) {
        this.tags = tags;
    }
}

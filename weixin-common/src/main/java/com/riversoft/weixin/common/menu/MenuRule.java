package com.riversoft.weixin.common.menu;

import com.riversoft.weixin.common.user.Gender;

/**
 * Created by exizhai on 1/30/2016.
 */
public class MenuRule {

    private String groupId;
    private Gender sex;
    private String country;
    private String province;
    private String city;
    private Platform platform;
    private String language;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public Gender getSex() {
        return sex;
    }

    public void setSex(Gender sex) {
        this.sex = sex;
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

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public static enum Platform {

        IOS("1"), Android("2"), OTHERS("3");

        private String code;

        private Platform(String code) {
            this.code = code;
        }

        public static Platform fromCode(String code) {
            for (Platform platform : Platform.values()) {
                if (platform.code == code) {
                    return platform;
                }
            }

            return Platform.OTHERS;
        }

        public String getCode() {
            return this.code;
        }
    }
}

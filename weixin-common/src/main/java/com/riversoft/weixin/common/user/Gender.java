package com.riversoft.weixin.common.user;

/**
 * Created by exizhai on 10/4/2015.
 */
public enum Gender {

    UNKNOWN(0), MALE(1), FEMALE(2);

    private int code;

    private Gender(int code) {
        this.code = code;
    }

    public static Gender fromCode(int code) {
        for (Gender gender : Gender.values()) {
            if (gender.code == code) {
                return gender;
            }
        }

        return Gender.UNKNOWN;
    }

    public int getCode() {
        return this.code;
    }
}

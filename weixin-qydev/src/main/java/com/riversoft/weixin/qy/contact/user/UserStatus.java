package com.riversoft.weixin.qy.contact.user;

/**
 * Created by exizhai on 10/4/2015.
 */
public enum UserStatus {

    FOLLOWED(1), UN_FOLLOWED(4), SUSPEND(2), UNKNOWN(-1);

    private int status;

    UserStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return this.status;
    }
}

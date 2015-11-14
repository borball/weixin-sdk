package com.riversoft.weixin.qy.contact.user;

/**
 * Created by exizhai on 10/4/2015.
 */
public enum Invitation {

    WX(1), EMAIL(2), ALREADY_FOLLOWED(0), ALREADY_INVITED(98), FAILED(99);

    private int type;

    Invitation(int type) {
        this.type = type;
    }

    public static Invitation format(int type) {
        if (type == 1)
            return WX;
        if (type == 2)
            return EMAIL;
        return FAILED;
    }

    public int getType() {
        return type;
    }
}

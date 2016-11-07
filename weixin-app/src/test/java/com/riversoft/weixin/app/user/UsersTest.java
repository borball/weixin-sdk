package com.riversoft.weixin.app.user;

import org.junit.Test;

/**
 * @borball on 11/7/2016.
 */
public class UsersTest {

    @Test
    public void testCode2Session() {
        SessionKey sessionKey = Users.defaultUsers().code2Session("code");
    }
}

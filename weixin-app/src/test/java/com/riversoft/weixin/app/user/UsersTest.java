package com.riversoft.weixin.app.user;

import org.junit.Test;

import com.riversoft.weixin.app.base.AppSetting;

/**
 * @borball on 11/7/2016.
 */
public class UsersTest {

	@Test
	public void testCode2Session() {
		SessionKey sessionKey = Users.with(new AppSetting("wx924243b01acbccbe", "2d7d9b1a3626f21fc127c4285b321b6f"))
				.code2Session("011RedXY1NegvX0eLWXY1vVfXY1RedXu");
		System.out.println(sessionKey.getOpenId());
		System.out.println(sessionKey.getSessionKey());
		System.out.println(sessionKey.getExpiresTill());
	}
}

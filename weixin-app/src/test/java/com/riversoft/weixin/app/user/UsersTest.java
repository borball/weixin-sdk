package com.riversoft.weixin.app.user;

import org.junit.Test;

import com.riversoft.weixin.app.base.AppSetting;

/**
 * @borball on 11/7/2016.
 */
public class UsersTest {

	@Test
	public void testCode2Session() {
		SessionKey sessionKey = Users.with(new AppSetting("appid", "screctkey"))
				.code2Session("011RedXY1NegvX0eLWXY1vVfXY1RedXu");
		System.out.println(sessionKey.getOpenId());
		System.out.println(sessionKey.getSessionKey());
		System.out.println(sessionKey.getExpiresTill());
	}
}

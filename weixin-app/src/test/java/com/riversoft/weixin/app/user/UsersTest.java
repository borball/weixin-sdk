package com.riversoft.weixin.app.user;

import static org.junit.Assert.assertNotNull;

import org.junit.Ignore;
import org.junit.Test;

import com.riversoft.weixin.app.base.AppSetting;

/**
 * @borball on 11/7/2016.
 */
public class UsersTest {

	@Test
	@Ignore
	public void testCode2Session() {
		SessionKey sessionKey = Users.with(new AppSetting("appid", "screctkey"))
				.code2Session("011RedXY1NegvX0eLWXY1vVfXY1RedXu");
		assertNotNull(sessionKey);
	}
}

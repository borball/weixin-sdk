package com.riversoft.weixin.contact;

import com.riversoft.weixin.WxPropLoader;
import com.riversoft.weixin.contact.bean.user.Invitation;
import com.riversoft.weixin.contact.bean.user.ReadUser;
import com.riversoft.weixin.contact.bean.user.SimpleUser;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by exizhai on 10/3/2015.
 */
public class UsersTest {

    WxPropLoader wxPropLoader = new WxPropLoader(this.getClass().getClassLoader().getResourceAsStream("wx-test.properties"));

    @Test
    public void testGet() {
        ReadUser user = Users.defaultUsers().get(getTestUser());
        Assert.assertNotNull(user);
    }

    @Test
    public void testSimpleList() {
        ReadUser user = Users.defaultUsers().get(getTestUser());
        List<SimpleUser> users = Users.defaultUsers().simpleList(user.getDepartment()[0], true, null);
        Assert.assertNotNull(users);
    }

    @Test
    public void testList() {
        ReadUser user = Users.defaultUsers().get(getTestUser());
        List<ReadUser> users = Users.defaultUsers().list(user.getDepartment()[0], true, null);
        Assert.assertNotNull(users);
    }

    @Test
    public void testInvite() {
        Invitation invitation = Users.defaultUsers().invite("smooth");
        Assert.assertNotNull(invitation);
    }

    public String getTestUser() {
        return wxPropLoader.getProperty("messages.test.user");
    }
}

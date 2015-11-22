package com.riversoft.weixin.qy.contact;

import com.riversoft.weixin.qy.TestConfiguration;
import com.riversoft.weixin.qy.contact.user.Invitation;
import com.riversoft.weixin.qy.contact.user.ReadUser;
import com.riversoft.weixin.qy.contact.user.SimpleUser;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by exizhai on 10/3/2015.
 */
public class UsersTest {

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
        return TestConfiguration.getInstance().testUser();
    }
}

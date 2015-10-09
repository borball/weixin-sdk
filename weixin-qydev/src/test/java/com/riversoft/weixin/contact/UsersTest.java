package com.riversoft.weixin.contact;

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

    @Test
    public void testGet() {
        ReadUser user = Users.defaultUsers().get("testuser");
        Assert.assertNotNull(user);
    }

    @Test
    public void testSimpleList() {
        List<SimpleUser> users = Users.defaultUsers().simpleList(102, true, null);
        Assert.assertNotNull(users);
    }

    @Test
    public void testList() {
        List<ReadUser> users = Users.defaultUsers().list(102, true, null);
        Assert.assertNotNull(users);
    }

    @Test
    public void testInvite() {
        Invitation invitation = Users.defaultUsers().invite("borball");
        Assert.assertNotNull(invitation);
    }
}

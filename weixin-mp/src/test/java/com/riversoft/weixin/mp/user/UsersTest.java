package com.riversoft.weixin.mp.user;

import com.riversoft.weixin.mp.user.bean.User;
import com.riversoft.weixin.mp.user.bean.UserPagination;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by exizhai on 11/4/2015.
 */
public class UsersTest {

    @Test
    public void testGet() {
        User user = Users.defaultUsers().get(TestConfiguration.getInstance().testUser());
        Assert.assertNotNull(user);
    }

    @Test
    public void testList() {
        UserPagination userPagination = Users.defaultUsers().list();

        Assert.assertNotNull(userPagination);

        int count = userPagination.getCount();
        int total = userPagination.getTotal();
        List<String> users = userPagination.getUsers();
        String next = userPagination.getNextOpenId();

        userPagination = Users.defaultUsers().list(next);
        Assert.assertNotNull(userPagination);
    }

    @Test
    public void testRemark() {
        Users.defaultUsers().remark(TestConfiguration.getInstance().testUser(), "borball");
        User user = Users.defaultUsers().get(TestConfiguration.getInstance().testUser());
        Assert.assertEquals("borball", user.getRemark());
    }
}

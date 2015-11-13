package com.riversoft.weixin.mp.user;

import com.riversoft.weixin.mp.user.bean.User;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by exizhai on 11/4/2015.
 */
public class UsersTest {

    @Test
    public void testGet(){
        User user = Users.defaultUsers().get("oUQlRwnlS4u6PABZdR6Lgtcwq0vk");
        Assert.assertNotNull(user);
    }

    @Test
    public void testList(){
        String users = Users.defaultUsers().list();
    }
}

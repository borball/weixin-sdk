package com.riversoft.weixin.mp.user;

import com.riversoft.weixin.mp.user.bean.UserPagination;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @borball on 4/7/2017.
 */
public class BlackListsTest {

    @Test
    public void testBlack() {
        List<String> users = new ArrayList<>();
        users.add("user1");
        users.add("user2");
        BlackLists.defaultBlackLists().black(users);
    }

    @Test
    public void testUnBlack() {
        List<String> users = new ArrayList<>();
        users.add("user1");
        users.add("user2");
        BlackLists.defaultBlackLists().unblack(users);
    }

    @Test
    public void testList() {
        UserPagination list = BlackLists.defaultBlackLists().list();
        Assert.assertNotNull(list);
    }
}

package com.riversoft.weixin.mp.user;

import com.riversoft.weixin.mp.TestConfiguration;
import com.riversoft.weixin.mp.user.bean.Group;
import com.riversoft.weixin.mp.user.bean.User;
import com.riversoft.weixin.mp.user.bean.UserPagination;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by exizhai on 11/5/2015.
 */
public class GroupsTest {

    @Test
    public void testUpdate(){
        Groups.defaultGroups().move(TestConfiguration.getInstance().testUser(), 0);

        int group = Groups.defaultGroups().getUserGroup(TestConfiguration.getInstance().testUser());
        Assert.assertTrue(group == 0);
        Groups.defaultGroups().move(TestConfiguration.getInstance().testUser(), 2);

        group = Groups.defaultGroups().getUserGroup(TestConfiguration.getInstance().testUser());
        Assert.assertTrue(group == 2);

        Groups.defaultGroups().move(TestConfiguration.getInstance().testUser(), 0);
    }

    @Test
    public void testMove(){
        Groups.defaultGroups().move(TestConfiguration.getInstance().testUser(), 0);

        int group = Groups.defaultGroups().getUserGroup(TestConfiguration.getInstance().testUser());
        Assert.assertTrue(group == 0);
        Groups.defaultGroups().move(TestConfiguration.getInstance().testUser(), 2);

        group = Groups.defaultGroups().getUserGroup(TestConfiguration.getInstance().testUser());
        Assert.assertTrue(group == 2);

        Groups.defaultGroups().move(TestConfiguration.getInstance().testUser(), 0);
    }

    @Test
    public void testMoves(){
        UserPagination userPagination = Users.defaultUsers().list();
        Assert.assertNotNull(userPagination);
        List<String> users = userPagination.getUsers();

        Group testGroup = Groups.defaultGroups().create("测试分组");

        sleep(5000);//貌似微信维护了cache之类

        Groups.defaultGroups().move(users, testGroup.getId());

        userPagination = Users.defaultUsers().list();
        users = userPagination.getUsers();
        for (String u: users) {
            User user = Users.defaultUsers().get(u);
            Assert.assertEquals(testGroup.getId(), user.getGroup());
        }

        Groups.defaultGroups().move(users, 0);

        userPagination = Users.defaultUsers().list();
        users = userPagination.getUsers();
        for (String u: users) {
            User user = Users.defaultUsers().get(u);
            Assert.assertEquals(0, user.getGroup());
        }

        Groups.defaultGroups().delete(testGroup.getId());
    }

    @Test
    public void testAll() {
        List<Group> groups = Groups.defaultGroups().list();

        Assert.assertNotNull(groups);
        int size1 = groups.size();

        Group group = Groups.defaultGroups().create("群组1");
        Assert.assertNotNull(group);

        sleep(5000);//貌似微信维护了cache之类

        groups = Groups.defaultGroups().list();
        Assert.assertNotNull(groups);

        int size2 = groups.size();
        Assert.assertEquals(size1 + 1, size2);

        Groups.defaultGroups().update(group.getId(), "群组2");

        sleep(2000);

        Groups.defaultGroups().delete(group.getId());

        sleep(5000);

        groups = Groups.defaultGroups().list();
        Assert.assertNotNull(groups);

        int size3 = groups.size();
        Assert.assertEquals(size1, size3);
    }

    private void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
        }
    }
}

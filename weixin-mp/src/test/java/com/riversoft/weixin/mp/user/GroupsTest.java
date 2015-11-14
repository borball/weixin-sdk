package com.riversoft.weixin.mp.user;

import com.riversoft.weixin.mp.user.bean.Group;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by exizhai on 11/5/2015.
 */
public class GroupsTest {

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

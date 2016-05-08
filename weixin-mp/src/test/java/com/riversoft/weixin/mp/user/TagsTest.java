package com.riversoft.weixin.mp.user;

import com.riversoft.weixin.mp.user.bean.Tag;
import com.riversoft.weixin.mp.user.bean.UserPagination;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * @borball on 5/8/2016.
 */
public class TagsTest {

    @Test
    public void testCreateUpdateDelete(){
        int size1 = Tags.defaultTags().list().size();
        Tag createdTag = Tags.defaultTags().create("VIP11");
        Assert.assertNotNull(createdTag);
        createdTag.setName("VIP12");
        Tags.defaultTags().update(createdTag);
        Tags.defaultTags().delete(createdTag.getId());
        sleep(2000);
        int size2 = Tags.defaultTags().list().size();
        Assert.assertEquals(size1, size2);
    }

    private void sleep(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
        }
    }

    @Test
    public void testList(){
        List<Tag> list = Tags.defaultTags().list();
        Assert.assertNotNull(list);
    }

    @Test
    public void testGetUserTag(){
        UserPagination userPagination = Users.defaultUsers().list();
        Assert.assertNotNull(userPagination);
        List<Integer> tags = Tags.defaultTags().getUserTags("oELhlt7Q-lRmLbRsPsaKeVX6pqjg");
        Assert.assertNotNull(tags);
    }

    @Test
    public void testTagUsers(){
        Tag createdTag = Tags.defaultTags().create("VIP11");

        UserPagination userPagination = Users.defaultUsers().list();
        Assert.assertNotNull(userPagination);
        Tags.defaultTags().tagUsers(createdTag.getId(), userPagination.getUsers());
        Tags.defaultTags().unTagUsers(createdTag.getId(), userPagination.getUsers());
        Tags.defaultTags().delete(createdTag.getId());
    }
}

package com.riversoft.weixin.qy.contact;

import com.riversoft.weixin.qy.contact.tag.TagUsers;
import org.junit.Test;

/**
 * @borball on 3/3/2016.
 */
public class TagsTest {

    @Test
    public void testGetUsers() {
        TagUsers tagUsers = Tags.defaultTags().getUsers(200);
    }
}

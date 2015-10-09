package com.riversoft.weixin.contact;

import com.riversoft.weixin.contact.bean.job.JobResult;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by exizhai on 10/7/2015.
 */
public class JobsTest {

    @Test
    public void testInvite(){
        List<String> users = new ArrayList<>();
        users.add("user1");
        users.add("user2");
        users.add("user3");

        List<Integer> departments = new ArrayList<>();
        departments.add(100);
        departments.add(101);
        departments.add(102);

        List<Integer> tags = new ArrayList<>();
        tags.add(1);
        tags.add(2);
        tags.add(3);

        String job = Jobs.defaultTags().invite(users, departments, tags);
        JobResult result = Jobs.defaultTags().getResult(job);

        Assert.assertNotNull(result);
    }

}

package com.riversoft.weixin.qy.contact;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.google.common.io.Files;
import com.riversoft.weixin.qy.contact.department.Department;
import com.riversoft.weixin.qy.contact.job.JobResult;
import com.riversoft.weixin.qy.contact.user.CreateUser;
import com.riversoft.weixin.qy.contact.user.ReadUser;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by exizhai on 10/7/2015.
 */
public class JobsTest {

    private final CsvSchema DEPARTMENT_SCHEMA = new CsvSchema.Builder().addColumn("name").addColumn("id")
            .addColumn("parentid").addColumn("order").build();
    private final CsvSchema USER_SCHEMA = new CsvSchema.Builder().addColumn("name").addColumn("userid")
            .addColumn("weixinid").addColumn("mobile").addColumn("email").addArrayColumn("department").addColumn("position").build();
    private final CsvMapper csvMapper = new CsvMapper();

    @Ignore
    public void testInvite() {
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

        String job = Jobs.defaultJobs().invite(users, departments, tags);
        JobResult result = Jobs.defaultJobs().getResult(job);

        Assert.assertNotNull(result);
    }

    @Test
    public void testReplaceDepartments() {
        List<Department> departments = Departments.defaultDepartments().list();

        File tmpDir = Files.createTempDir();
        File groups = new File(tmpDir, "departments.csv");

        try {
            PrintWriter groupPrintWriter = new PrintWriter(new BufferedWriter(new FileWriter(groups, false)));
            groupPrintWriter.println("部门名称,部门ID,父部门ID,排序");

            for (Department department : departments) {
                groupPrintWriter.print(csv(department));
            }
            groupPrintWriter.close();

            String job = Jobs.defaultJobs().replaceDepartments(groups);
            while (true) {
                JobResult jobResult = Jobs.defaultJobs().getResult(job);
                if (3 == jobResult.getStatus()) {
                    if (100 == jobResult.getPercentage()) {
                        Assert.assertTrue(true);
                    }
                    break;
                } else {
                    System.out.println("正在同步:" + jobResult.getPercentage());
                    sleep(100);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("test failed.");
        } finally {
            try {
                FileUtils.forceDelete(tmpDir);
            } catch (IOException e) {
            }
        }
    }

    private void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
        }
    }

    @Test
    public void testReplaceUsers() {
        List<ReadUser> allUsers = Users.defaultUsers().list();

        File tmpDir = Files.createTempDir();
        File users = new File(tmpDir, "users.csv");

        try {
            PrintWriter userPrintWriter = new PrintWriter(new BufferedWriter(new FileWriter(users, false)));
            userPrintWriter.println("姓名,帐号,微信号,手机号,邮箱,所在部门,职位");

            for (ReadUser user : allUsers) {
                CreateUser createUser = new CreateUser();
                createUser.setName(user.getName());
                createUser.setMobile(user.getMobile());
                createUser.setUserId(user.getUserId());
                createUser.setPosition(user.getPosition());
                createUser.setWeixinId(user.getWeixinId());
                createUser.setDepartment(user.getDepartment());
                createUser.setEmail(user.getEmail());
                userPrintWriter.print(csv(createUser));
            }

            userPrintWriter.close();

            String job = Jobs.defaultJobs().replaceUsers(users);
            while (true) {
                JobResult jobResult = Jobs.defaultJobs().getResult(job);
                if (3 == jobResult.getStatus()) {
                    if (100 == jobResult.getPercentage()) {
                        Assert.assertTrue(true);
                    }
                    break;
                } else {
                    System.out.println("正在同步:" + jobResult.getPercentage());
                    sleep(10);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("test failed.");
        } finally {
            try {
                FileUtils.forceDelete(tmpDir);
            } catch (IOException e) {
            }
        }
    }

    private String csv(CreateUser user) throws JsonProcessingException {
        csvMapper.enable(JsonGenerator.Feature.IGNORE_UNKNOWN);
        return csvMapper.writerFor(CreateUser.class).with(USER_SCHEMA).writeValueAsString(user);
    }

    public String csv(Department department) throws JsonProcessingException {
        csvMapper.enable(JsonGenerator.Feature.IGNORE_UNKNOWN);
        return csvMapper.writerFor(Department.class).with(DEPARTMENT_SCHEMA).writeValueAsString(department);
    }

}

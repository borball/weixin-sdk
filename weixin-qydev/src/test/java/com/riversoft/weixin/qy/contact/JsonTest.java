package com.riversoft.weixin.qy.contact;

import com.riversoft.weixin.common.util.JsonMapper;
import com.riversoft.weixin.qy.contact.user.AbstractUser;
import com.riversoft.weixin.qy.contact.user.CreateUser;
import com.riversoft.weixin.qy.contact.user.UpdateUser;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by exizhai on 10/4/2015.
 */
public class JsonTest {

    @Test
    public void testCreateUserToJson() {
        CreateUser user = new CreateUser();
        user.setUserId("uid");
        user.setName("username");
        user.setEmail("email@123.com");
        user.setPosition("position");
        user.setWeixinId("wxid");
        user.setDepartment(new int[]{1, 2});
        user.setGender(AbstractUser.Gender.FEMALE);
        user.setAvatarMediaId("avatar_media_id_test");

        List<AbstractUser.ExtAttr.Attr> attrs = new ArrayList<>();
        AbstractUser.ExtAttr.Attr attr1 = new AbstractUser.ExtAttr.Attr("爱好", "足球");
        AbstractUser.ExtAttr.Attr attr2 = new AbstractUser.ExtAttr.Attr("银行卡号", "test bank card number");
        attrs.add(attr1);
        attrs.add(attr2);

        AbstractUser.ExtAttr extAttr = new AbstractUser.ExtAttr();
        extAttr.setAttrs(attrs);
        user.setExtAttr(extAttr);

        String createJson = JsonMapper.nonEmptyMapper().toJson(user);

        System.out.println(createJson);
        Assert.assertTrue(createJson.contains("avatar_mediaid"));

    }

    @Test
    public void testUpdateUserToJson() {
        UpdateUser user = new UpdateUser();
        user.setUserId("uid");
        user.setEnable(true);

        String createJson = JsonMapper.nonEmptyMapper().toJson(user);

        System.out.println(createJson);
        Assert.assertFalse(createJson.contains("avatar_mediaid"));
        Assert.assertTrue(createJson.contains("enable"));
    }

    @Test
    public void testStringsToJson() {
        List<String> list = new ArrayList<>();
        list.add("id1");
        list.add("id2");
        list.add("id3");

        System.out.println(JsonMapper.defaultMapper().toJson(list));
    }

    @Test
    public void testMap2Json() {
        Map<String, String> map = new HashMap<>();
        map.put("touser", "123|321");
        map.put("toparty", "1|2|3");

        System.out.println(JsonMapper.defaultMapper().toJson(map));

        Map<String, String> map1 = new HashMap<>();
        map1.put("touser", "123|321");
        map1.put("toparty", "1|2|3");

        Map<String, String> map2 = new HashMap<>();
        map2.put("touser", "123|321");
        map2.put("toparty", "1|2|3");

        Map<String, String> map3 = new HashMap<>();
        map3.put("touser", "123|321");
        map3.put("toparty", "1|2|3");

        List<Map<String, String>> list = new ArrayList<>();
        list.add(map1);
        list.add(map2);
        list.add(map3);

        System.out.println(JsonMapper.defaultMapper().toJson(list));
    }

}

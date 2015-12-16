package com.riversoft.weixin.mp.template;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by exizhai on 12/16/2015.
 */
public class TemplatesTest {

    @Test
    public void testSetIndustry(){
        Templates.defaultTemplates().setIndustries("1", "2");
    }

    @Test
    public void testFetch(){
        String id = Templates.defaultTemplates().fetch("TM00015");
        System.out.println(id);
        Assert.assertNotNull(id);
    }

    @Test
    public void testSend(){
        String openId = "oELhlt7Q-lRmLbRsPsaKeVX6pqjg";
        String templateId = "sGy7O4ZbXfzF1suGoCR0Gst1IQNe0df5ewTZy7NaR9g";
        String url = "http://mp.weixin.qq.com/";
        Map<String, Item> data = new HashMap<>();
        data.put("productType", new Item("商品名", "#173177"));
        data.put("name", new Item("微信某某店某商品", "#173177"));
        data.put("accountType", new Item("会员卡号", "#173177"));
        data.put("account", new Item("11912345678", "#173177"));
        data.put("time", new Item("2013年8月20日 20:38", "#173177"));
        data.put("remark", new Item("您可以回复文字或语音对该商品及商家进行评价哦~", "#173177"));

        int messageId = Templates.defaultTemplates().send(openId, templateId, url, data);
        System.out.println(messageId);
        Assert.assertNotNull(messageId);

    }

}

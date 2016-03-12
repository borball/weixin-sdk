package com.riversoft.weixin.mp.template;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
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
    public void testGetIndustry(){
        Industry industry = Templates.defaultTemplates().getIndustries();
        Assert.assertNotNull(industry);
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
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxd1a32e23ee80bf7a&redirect_uri=http%3A%2F%2Fwxtest.gzriver.com%2Fcss%2Fthemes%2Fsmoothness%2Fjquery-ui.min.css&response_type=code&scope=snsapi_userinfo&state=state111#wechat_redirect";
        Map<String, Data> data = new HashMap<>();
        data.put("productType", new Data("商品名", "#173177"));
        data.put("name", new Data("微信某某店某商品", "#173177"));
        data.put("accountType", new Data("会员卡号", "#173177"));
        data.put("account", new Data("11912345678", "#173177"));
        data.put("time", new Data("2013年8月20日 20:38", "#173177"));
        data.put("remark", new Data("您可以回复文字或语音对该商品及商家进行评价哦~", "#173177"));

        long messageId = Templates.defaultTemplates().send(openId, templateId, url, data);
        System.out.println(messageId);
        Assert.assertNotNull(messageId);

    }

    @Test
     public void testList(){
        List<Template> list = Templates.defaultTemplates().list();
        Assert.assertNotNull(list);
    }

    @Test
    public void testDelete(){
        Templates.defaultTemplates().delete("3v146u0xli7fBnkdjExqwEo9mjhs0hn3LjQ0JgcgtDU");
    }

}

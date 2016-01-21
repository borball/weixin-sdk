package com.riversoft.weixin.mp.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.riversoft.weixin.common.message.*;
import com.riversoft.weixin.common.message.xml.*;
import com.riversoft.weixin.mp.message.xml.Forward2CareXmlMessage;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

/**
 * Created by exizhai on 9/30/2015.
 */
public class MpXmlMessagesTest {

    @Test
    public void testTextToXML() throws JsonProcessingException {
        TextXmlMessage textXmlMessage = new TextXmlMessage();
        textXmlMessage.setContent("content");
        textXmlMessage.setFromUser("from user");
        textXmlMessage.setToUser("to user");
        textXmlMessage.setCreateTime(new Date());

        String xml = MpXmlMessages.toXml(textXmlMessage);

        Assert.assertTrue(xml.contains("<ToUserName><![CDATA[to user]]></ToUserName>"));
        Assert.assertTrue(xml.contains("<FromUserName><![CDATA[from user]]>"));
        Assert.assertTrue(xml.contains("<MsgType><![CDATA[text]]></MsgType>"));
        Assert.assertTrue(xml.contains("<Content><![CDATA[content]]></Content>"));
    }

    @Test
    public void testImageToXML() throws JsonProcessingException {
        ImageXmlMessage imageXmlMessage = new ImageXmlMessage();
        imageXmlMessage.setMedia(new Media("media-id"));
        imageXmlMessage.setFromUser("from user");
        imageXmlMessage.setToUser("to user");
        imageXmlMessage.setCreateTime(new Date());

        String xml = MpXmlMessages.toXml(imageXmlMessage);

        Assert.assertTrue(xml.contains("<ToUserName><![CDATA[to user]]></ToUserName>"));
        Assert.assertTrue(xml.contains("<FromUserName><![CDATA[from user]]>"));
        Assert.assertTrue(xml.contains("<MsgType><![CDATA[image]]></MsgType>"));
        Assert.assertTrue(xml.contains("<Image><MediaId>media-id</MediaId></Image>"));
    }

    @Test
    public void testVoiceToXML() throws JsonProcessingException {
        VoiceXmlMessage voiceXmlMessage = new VoiceXmlMessage();
        voiceXmlMessage.setMedia(new Media("media-id"));
        voiceXmlMessage.setFromUser("from user");
        voiceXmlMessage.setToUser("to user");
        voiceXmlMessage.setCreateTime(new Date());

        String xml = MpXmlMessages.toXml(voiceXmlMessage);

        Assert.assertTrue(xml.contains("<ToUserName><![CDATA[to user]]></ToUserName>"));
        Assert.assertTrue(xml.contains("<FromUserName><![CDATA[from user]]>"));
        Assert.assertTrue(xml.contains("<MsgType><![CDATA[voice]]></MsgType>"));
        Assert.assertTrue(xml.contains("<Voice><MediaId>media-id</MediaId></Voice>"));
    }

    @Test
    public void testVideoToXML() throws JsonProcessingException {
        VideoXmlMessage videoXmlMessage = new VideoXmlMessage();

        Video video = new Video();
        video.setMediaId("video media id");
        video.setTitle("title");
        video.setDescription("desc");
        videoXmlMessage.setVideo(video);
        videoXmlMessage.setFromUser("from user");
        videoXmlMessage.setToUser("to user");
        videoXmlMessage.setCreateTime(new Date());

        String xml = MpXmlMessages.toXml(videoXmlMessage);

        Assert.assertTrue(xml.contains("<ToUserName><![CDATA[to user]]></ToUserName>"));
        Assert.assertTrue(xml.contains("<FromUserName><![CDATA[from user]]>"));
        Assert.assertTrue(xml.contains("<MsgType><![CDATA[video]]></MsgType>"));
        Assert.assertTrue(xml.contains("<Video><MediaId><![CDATA[video media id]]></MediaId><Title><![CDATA[title]]></Title><Description><![CDATA[desc]]></Description></Video>"));
    }

    @Test
    public void testNewsToXML() throws JsonProcessingException {
        NewsXmlMessage newsXmlMessage = new NewsXmlMessage();

        News news = new News();
        Article article1 = new Article();
        article1.setTitle("[1]测试news");
        article1.setDescription("[1]今日头条，正在调试message API, 测试是否能正常发送news类型。");
        article1.setUrl("http://riversoft.com.cn/Upload/Pic/banner4.jpg");
        article1.setPicUrl("http://riversoft.com.cn/Upload/Pic/banner2.jpg");
        news.add(article1);

        Article article2 = new Article();
        article2.setTitle("[2]测试news");
        article2.setDescription("[2]今日头条，正在调试message API, 测试是否能正常发送news类型。");
        article2.setUrl("http://riversoft.com.cn/Upload/Pic/banner4.jpg");
        article2.setPicUrl("http://riversoft.com.cn/Upload/Pic/banner2.jpg");
        news.add(article2);

        newsXmlMessage.setNews(news);
        newsXmlMessage.setFromUser("from user");
        newsXmlMessage.setToUser("to user");
        newsXmlMessage.setCreateTime(new Date());

        String xml = MpXmlMessages.toXml(newsXmlMessage);

        Assert.assertTrue(xml.contains("<ToUserName><![CDATA[to user]]></ToUserName>"));
        Assert.assertTrue(xml.contains("<FromUserName><![CDATA[from user]]>"));
        Assert.assertTrue(xml.contains("<MsgType><![CDATA[news]]></MsgType>"));
        Assert.assertTrue(xml.contains("<ArticleCount>2</ArticleCount>"));
        Assert.assertTrue(xml.contains("<Articles><item><Title><![CDATA[[1]测试news]]></Title>"));
    }

    @Test
    public void testKfMessageToXML() throws JsonProcessingException {
        Forward2CareXmlMessage kfXmlMessage = new Forward2CareXmlMessage();
        kfXmlMessage.setFromUser("from user");
        kfXmlMessage.setToUser("to user");
        kfXmlMessage.setCreateTime(new Date());

        String xml = MpXmlMessages.toXml(kfXmlMessage);
        Assert.assertTrue(xml.contains("<ToUserName><![CDATA[to user]]></ToUserName>"));
        Assert.assertTrue(xml.contains("<FromUserName><![CDATA[from user]]>"));

        kfXmlMessage.setAccount("kfaccount");
        xml = MpXmlMessages.toXml(kfXmlMessage);
        Assert.assertTrue(xml.contains("<TransInfo><KfAccount><![CDATA[kfaccount]]></KfAccount>"));
    }

}

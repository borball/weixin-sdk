package com.riversoft.weixin.qy.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.riversoft.weixin.qy.message.base.Article;
import com.riversoft.weixin.qy.message.base.Media;
import com.riversoft.weixin.qy.message.base.News;
import com.riversoft.weixin.qy.message.base.Video;
import com.riversoft.weixin.qy.message.request.*;
import com.riversoft.weixin.qy.message.request.event.*;
import com.riversoft.weixin.qy.message.xml.*;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;

/**
 * Created by exizhai on 9/30/2015.
 */
public class XmlMessagesTest {

    @Test
    public void testXmlToText() throws IOException {
        String xml = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("message/text.xml"));

        XmlRequest xmlRequest = XmlMessages.fromXml(xml);

        Assert.assertNotNull(xmlRequest);
        Assert.assertTrue(xmlRequest instanceof TextXmlRequest);

        Assert.assertEquals("this is a test", ((TextXmlRequest) xmlRequest).getContent());
    }


    @Test
    public void testXmlToImage() throws IOException {
        String xml = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("message/image.xml"));

        XmlRequest xmlRequest = XmlMessages.fromXml(xml);

        Assert.assertNotNull(xmlRequest);
        Assert.assertTrue(xmlRequest instanceof ImageXmlRequest);

        Assert.assertEquals("media_id", ((ImageXmlRequest) xmlRequest).getMediaId());
    }

    @Test
    public void testXmlToVoice() throws IOException {
        String xml = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("message/voice.xml"));

        XmlRequest xmlRequest = XmlMessages.fromXml(xml);

        Assert.assertNotNull(xmlRequest);
        Assert.assertTrue(xmlRequest instanceof VoiceXmlRequest);

        Assert.assertEquals("media_id", ((VoiceXmlRequest) xmlRequest).getMediaId());
        Assert.assertEquals("Format", ((VoiceXmlRequest) xmlRequest).getFormat());
    }

    @Test
    public void testXmlToVideo() throws IOException {
        String xml = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("message/video.xml"));

        XmlRequest xmlRequest = XmlMessages.fromXml(xml);

        Assert.assertNotNull(xmlRequest);
        Assert.assertTrue(xmlRequest instanceof VideoXmlRequest);

        Assert.assertEquals("media_id", ((VideoXmlRequest) xmlRequest).getMediaId());
        Assert.assertEquals("thumb_media_id", ((VideoXmlRequest) xmlRequest).getThumbMediaId());
    }

    @Test
    public void testXmlToShortVideo() throws IOException {
        String xml = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("message/shortvideo.xml"));

        XmlRequest xmlRequest = XmlMessages.fromXml(xml);

        Assert.assertNotNull(xmlRequest);
        Assert.assertTrue(xmlRequest instanceof ShortVideoXmlRequest);

        Assert.assertEquals("media_id", ((ShortVideoXmlRequest) xmlRequest).getMediaId());
        Assert.assertEquals("thumb_media_id", ((ShortVideoXmlRequest) xmlRequest).getThumbMediaId());
    }

    @Test
    public void testXmlToLocation() throws IOException {
        String xml = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("message/location.xml"));

        XmlRequest xmlRequest = XmlMessages.fromXml(xml);

        Assert.assertNotNull(xmlRequest);
        Assert.assertTrue(xmlRequest instanceof LocationXmlRequest);

        Assert.assertEquals("23.134521", ((LocationXmlRequest) xmlRequest).getX());
        Assert.assertEquals("113.358803", ((LocationXmlRequest) xmlRequest).getY());
        Assert.assertEquals("20", ((LocationXmlRequest) xmlRequest).getScale());
        Assert.assertEquals("位置信息", ((LocationXmlRequest) xmlRequest).getLabel());
    }

    @Test
    public void testXmlToSubscribeEvent() throws IOException {
        String xml = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("message/event/subscribe.xml"));

        XmlRequest xmlRequest = XmlMessages.fromXml(xml);

        Assert.assertNotNull(xmlRequest);
        Assert.assertTrue(xmlRequest instanceof SubscriptionEvent);

        Assert.assertTrue(((SubscriptionEvent) xmlRequest).isSub());

    }

    @Test
    public void testXmlToUnSubscribeEvent() throws IOException {
        String xml = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("message/event/unsubscribe.xml"));

        XmlRequest xmlRequest = XmlMessages.fromXml(xml);

        Assert.assertNotNull(xmlRequest);
        Assert.assertTrue(xmlRequest instanceof SubscriptionEvent);

        Assert.assertFalse(((SubscriptionEvent) xmlRequest).isSub());

    }

    @Test
    public void testXmlToLocationReportEvent() throws IOException {
        String xml = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("message/event/location-report.xml"));

        XmlRequest xmlRequest = XmlMessages.fromXml(xml);

        Assert.assertNotNull(xmlRequest);
        Assert.assertTrue(xmlRequest instanceof LocationReportEvent);

        Assert.assertEquals("23.104105", ((LocationReportEvent) xmlRequest).getLatitude());
        Assert.assertEquals("113.320107", ((LocationReportEvent) xmlRequest).getLongitude());
        Assert.assertEquals("65.000000", ((LocationReportEvent) xmlRequest).getPrecision());
    }

    @Test
    public void testXmlToClickEvent() throws IOException {
        String xml = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("message/event/click.xml"));

        XmlRequest xmlRequest = XmlMessages.fromXml(xml);

        Assert.assertNotNull(xmlRequest);
        Assert.assertTrue(xmlRequest instanceof ClickEvent);

        Assert.assertEquals("EVENTKEY", ((ClickEvent) xmlRequest).getEventKey());
    }

    @Test
    public void testXmlToViewEvent() throws IOException {
        String xml = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("message/event/view.xml"));

        XmlRequest xmlRequest = XmlMessages.fromXml(xml);

        Assert.assertNotNull(xmlRequest);
        Assert.assertTrue(xmlRequest instanceof ViewEvent);

        Assert.assertEquals("www.qq.com", ((ViewEvent) xmlRequest).getUrl());
    }

    @Test
    public void testXmlToScanPushEvent() throws IOException {
        String xml = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("message/event/scan-push.xml"));

        XmlRequest xmlRequest = XmlMessages.fromXml(xml);

        Assert.assertNotNull(xmlRequest);
        Assert.assertTrue(xmlRequest instanceof ScanEvent);

        Assert.assertEquals("qrcode", ((ScanEvent) xmlRequest).getScanCodeInfo().getScanType());
        Assert.assertEquals("1", ((ScanEvent) xmlRequest).getScanCodeInfo().getScanResult());
    }

    @Test
    public void testXmlToScanWaitMsgEvent() throws IOException {
        String xml = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("message/event/scan-waitmsg.xml"));

        XmlRequest xmlRequest = XmlMessages.fromXml(xml);

        Assert.assertNotNull(xmlRequest);
        Assert.assertTrue(xmlRequest instanceof ScanEvent);

        Assert.assertEquals("qrcode", ((ScanEvent) xmlRequest).getScanCodeInfo().getScanType());
        Assert.assertEquals("2", ((ScanEvent) xmlRequest).getScanCodeInfo().getScanResult());
    }

    @Test
    public void testXmlToSystemPhotoEvent() throws IOException {
        String xml = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("message/event/sys-photo.xml"));

        XmlRequest xmlRequest = XmlMessages.fromXml(xml);

        Assert.assertNotNull(xmlRequest);
        Assert.assertTrue(xmlRequest instanceof PhotoEvent);

        Assert.assertEquals(1, ((PhotoEvent) xmlRequest).getSendPicsInfo().getCount());
        Assert.assertEquals("1b5f7c23b5bf75682a53e7b6d163e185", ((PhotoEvent) xmlRequest).getSendPicsInfo().getItems().get(0).getPicMd5Sum());
    }

    @Test
    public void testXmlToSystemPhotoOrAlbumEvent() throws IOException {
        String xml = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("message/event/sys-photo-or-album.xml"));

        XmlRequest xmlRequest = XmlMessages.fromXml(xml);

        Assert.assertNotNull(xmlRequest);
        Assert.assertTrue(xmlRequest instanceof PhotoEvent);

        Assert.assertEquals(1, ((PhotoEvent) xmlRequest).getSendPicsInfo().getCount());
        Assert.assertEquals("5a75aaca956d97be686719218f275c6b", ((PhotoEvent) xmlRequest).getSendPicsInfo().getItems().get(0).getPicMd5Sum());
    }

    @Test
    public void testXmlToAlbumEvent() throws IOException {
        String xml = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("message/event/album.xml"));

        XmlRequest xmlRequest = XmlMessages.fromXml(xml);

        Assert.assertNotNull(xmlRequest);
        Assert.assertTrue(xmlRequest instanceof PhotoEvent);

        Assert.assertEquals(1, ((PhotoEvent) xmlRequest).getSendPicsInfo().getCount());
        Assert.assertEquals("5a75aaca956d97be686719218f275c6b", ((PhotoEvent) xmlRequest).getSendPicsInfo().getItems().get(0).getPicMd5Sum());
    }

    @Test
    public void testXmlToLocationSelectEvent() throws IOException {
        String xml = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("message/event/location-select.xml"));

        XmlRequest xmlRequest = XmlMessages.fromXml(xml);

        Assert.assertNotNull(xmlRequest);
        Assert.assertTrue(xmlRequest instanceof LocationSelectEvent);

        Assert.assertEquals("6", ((LocationSelectEvent) xmlRequest).getEventKey());
        Assert.assertEquals("23", ((LocationSelectEvent) xmlRequest).getSendLocationInfo().getX());
        Assert.assertEquals("113", ((LocationSelectEvent) xmlRequest).getSendLocationInfo().getY());
        Assert.assertEquals("15", ((LocationSelectEvent) xmlRequest).getSendLocationInfo().getScale());
        Assert.assertEquals(" 广州市海珠区客村艺苑路 106号", ((LocationSelectEvent) xmlRequest).getSendLocationInfo().getLabel());
        Assert.assertEquals("", ((LocationSelectEvent) xmlRequest).getSendLocationInfo().getPoiName());
    }

    @Test
    public void testXmlToEnterAgentEvent() throws IOException {
        String xml = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("message/event/enter-agent.xml"));

        XmlRequest xmlRequest = XmlMessages.fromXml(xml);

        Assert.assertNotNull(xmlRequest);
        Assert.assertTrue(xmlRequest instanceof EnterAgentEvent);

        Assert.assertEquals("enter_agent", ((EnterAgentEvent) xmlRequest).getEventType().name());
    }

    @Test
    public void testXmlToBatchJobEvent() throws IOException {
        String xml = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("message/event/batch-job.xml"));

        XmlRequest xmlRequest = XmlMessages.fromXml(xml);

        Assert.assertNotNull(xmlRequest);
        Assert.assertTrue(xmlRequest instanceof BatchJobResultEvent);

        Assert.assertEquals("S0MrnndvRG5fadSlLwiBqiDDbM143UqTmKP3152FZk4", ((BatchJobResultEvent) xmlRequest).getBatchJob().getJobId());
        Assert.assertEquals("sync_user", ((BatchJobResultEvent) xmlRequest).getBatchJob().getJobType().name());
        Assert.assertEquals("0", ((BatchJobResultEvent) xmlRequest).getBatchJob().getErrorCode());
        Assert.assertEquals("ok", ((BatchJobResultEvent) xmlRequest).getBatchJob().getErrorMessage());

    }

    @Test
    public void testTextToXML() throws JsonProcessingException {
        TextXmlMessage textXmlMessage = new TextXmlMessage();
        textXmlMessage.setContent("content");
        textXmlMessage.setFromUser("from user");
        textXmlMessage.setToUser("to user");
        textXmlMessage.setCreateTime(new Date());

        String xml = XmlMessages.toXml(textXmlMessage);

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

        String xml = XmlMessages.toXml(imageXmlMessage);

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

        String xml = XmlMessages.toXml(voiceXmlMessage);

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

        String xml = XmlMessages.toXml(videoXmlMessage);

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

        String xml = XmlMessages.toXml(newsXmlMessage);

        Assert.assertTrue(xml.contains("<ToUserName><![CDATA[to user]]></ToUserName>"));
        Assert.assertTrue(xml.contains("<FromUserName><![CDATA[from user]]>"));
        Assert.assertTrue(xml.contains("<MsgType><![CDATA[news]]></MsgType>"));
        Assert.assertTrue(xml.contains("<ArticleCount>2</ArticleCount>"));
        Assert.assertTrue(xml.contains("<Articles><item><Title><![CDATA[[1]测试news]]></Title>"));
    }
}

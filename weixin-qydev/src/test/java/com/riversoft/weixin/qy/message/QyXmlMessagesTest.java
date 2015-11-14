package com.riversoft.weixin.qy.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.riversoft.weixin.common.message.*;
import com.riversoft.weixin.common.message.xml.*;
import com.riversoft.weixin.qy.event.*;
import com.riversoft.weixin.qy.request.*;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;

/**
 * Created by exizhai on 9/30/2015.
 */
public class QyXmlMessagesTest {

    @Test
    public void testXmlToText() throws IOException {
        String xml = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("message/text.xml"));

        XmlMessageHeader xmlRequest = QyXmlMessages.fromXml(xml);

        Assert.assertNotNull(xmlRequest);
        Assert.assertTrue(xmlRequest instanceof QyTextRequest);

        Assert.assertEquals("this is a test", ((QyTextRequest) xmlRequest).getContent());
    }


    @Test
    public void testXmlToImage() throws IOException {
        String xml = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("message/image.xml"));

        XmlMessageHeader xmlRequest = QyXmlMessages.fromXml(xml);

        Assert.assertNotNull(xmlRequest);
        Assert.assertTrue(xmlRequest instanceof QyImageRequest);

        Assert.assertEquals("media_id", ((QyImageRequest) xmlRequest).getMediaId());
        Assert.assertEquals(1, ((QyImageRequest) xmlRequest).getAgentId());
    }

    @Test
    public void testXmlToVoice() throws IOException {
        String xml = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("message/voice.xml"));

        XmlMessageHeader xmlRequest = QyXmlMessages.fromXml(xml);

        Assert.assertNotNull(xmlRequest);
        Assert.assertTrue(xmlRequest instanceof QyVoiceRequest);

        Assert.assertEquals("media_id", ((QyVoiceRequest) xmlRequest).getMediaId());
        Assert.assertEquals("Format", ((QyVoiceRequest) xmlRequest).getFormat());
    }

    @Test
    public void testXmlToVideo() throws IOException {
        String xml = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("message/video.xml"));

        XmlMessageHeader xmlRequest = QyXmlMessages.fromXml(xml);

        Assert.assertNotNull(xmlRequest);
        Assert.assertTrue(xmlRequest instanceof QyVideoRequest);

        Assert.assertEquals("media_id", ((QyVideoRequest) xmlRequest).getMediaId());
        Assert.assertEquals("thumb_media_id", ((QyVideoRequest) xmlRequest).getThumbMediaId());
    }

    @Test
    public void testXmlToShortVideo() throws IOException {
        String xml = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("message/shortvideo.xml"));

        XmlMessageHeader xmlRequest = QyXmlMessages.fromXml(xml);

        Assert.assertNotNull(xmlRequest);
        Assert.assertTrue(xmlRequest instanceof QyShortVideoRequest);

        Assert.assertEquals("media_id", ((QyShortVideoRequest) xmlRequest).getMediaId());
        Assert.assertEquals("thumb_media_id", ((QyShortVideoRequest) xmlRequest).getThumbMediaId());
    }

    @Test
    public void testXmlToLocation() throws IOException {
        String xml = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("message/location.xml"));

        XmlMessageHeader xmlRequest = QyXmlMessages.fromXml(xml);

        Assert.assertNotNull(xmlRequest);
        Assert.assertTrue(xmlRequest instanceof QyLocationRequest);

        Assert.assertEquals("23.134521", ((QyLocationRequest) xmlRequest).getX());
        Assert.assertEquals("113.358803", ((QyLocationRequest) xmlRequest).getY());
        Assert.assertEquals("20", ((QyLocationRequest) xmlRequest).getScale());
        Assert.assertEquals("位置信息", ((QyLocationRequest) xmlRequest).getLabel());
    }

    @Test
    public void testXmlToSubscribeEvent() throws IOException {
        String xml = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("message/event/subscribe.xml"));

        XmlMessageHeader xmlRequest = QyXmlMessages.fromXml(xml);

        Assert.assertNotNull(xmlRequest);
        Assert.assertTrue(xmlRequest instanceof QySubscriptionEvent);

        Assert.assertTrue(((QySubscriptionEvent) xmlRequest).isSub());

    }

    @Test
    public void testXmlToUnSubscribeEvent() throws IOException {
        String xml = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("message/event/unsubscribe.xml"));

        XmlMessageHeader xmlRequest = QyXmlMessages.fromXml(xml);

        Assert.assertNotNull(xmlRequest);
        Assert.assertTrue(xmlRequest instanceof QySubscriptionEvent);

        Assert.assertFalse(((QySubscriptionEvent) xmlRequest).isSub());

    }

    @Test
    public void testXmlToLocationReportEvent() throws IOException {
        String xml = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("message/event/location-report.xml"));

        XmlMessageHeader xmlRequest = QyXmlMessages.fromXml(xml);

        Assert.assertNotNull(xmlRequest);
        Assert.assertTrue(xmlRequest instanceof QyLocationReportEvent);

        Assert.assertEquals("23.104105", ((QyLocationReportEvent) xmlRequest).getLatitude());
        Assert.assertEquals("113.320107", ((QyLocationReportEvent) xmlRequest).getLongitude());
        Assert.assertEquals("65.000000", ((QyLocationReportEvent) xmlRequest).getPrecision());
    }

    @Test
    public void testXmlToClickEvent() throws IOException {
        String xml = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("message/event/click.xml"));

        XmlMessageHeader xmlRequest = QyXmlMessages.fromXml(xml);

        Assert.assertNotNull(xmlRequest);
        Assert.assertTrue(xmlRequest instanceof QyClickEvent);

        Assert.assertEquals("EVENTKEY", ((QyClickEvent) xmlRequest).getEventKey());
    }

    @Test
    public void testXmlToViewEvent() throws IOException {
        String xml = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("message/event/view.xml"));

        XmlMessageHeader xmlRequest = QyXmlMessages.fromXml(xml);

        Assert.assertNotNull(xmlRequest);
        Assert.assertTrue(xmlRequest instanceof QyViewEvent);

        Assert.assertEquals("www.qq.com", ((QyViewEvent) xmlRequest).getUrl());
    }

    @Test
    public void testXmlToScanPushEvent() throws IOException {
        String xml = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("message/event/scan-push.xml"));

        XmlMessageHeader xmlRequest = QyXmlMessages.fromXml(xml);

        Assert.assertNotNull(xmlRequest);
        Assert.assertTrue(xmlRequest instanceof QyScanEvent);

        Assert.assertEquals("qrcode", ((QyScanEvent) xmlRequest).getScanCodeInfo().getScanType());
        Assert.assertEquals("1", ((QyScanEvent) xmlRequest).getScanCodeInfo().getScanResult());
    }

    @Test
    public void testXmlToScanWaitMsgEvent() throws IOException {
        String xml = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("message/event/scan-waitmsg.xml"));

        XmlMessageHeader xmlRequest = QyXmlMessages.fromXml(xml);

        Assert.assertNotNull(xmlRequest);
        Assert.assertTrue(xmlRequest instanceof QyScanEvent);

        Assert.assertEquals("qrcode", ((QyScanEvent) xmlRequest).getScanCodeInfo().getScanType());
        Assert.assertEquals("2", ((QyScanEvent) xmlRequest).getScanCodeInfo().getScanResult());
    }

    @Test
    public void testXmlToSystemPhotoEvent() throws IOException {
        String xml = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("message/event/sys-photo.xml"));

        XmlMessageHeader xmlRequest = QyXmlMessages.fromXml(xml);

        Assert.assertNotNull(xmlRequest);
        Assert.assertTrue(xmlRequest instanceof QyPhotoEvent);

        Assert.assertEquals(1, ((QyPhotoEvent) xmlRequest).getSendPicsInfo().getCount());
        Assert.assertEquals("1b5f7c23b5bf75682a53e7b6d163e185", ((QyPhotoEvent) xmlRequest).getSendPicsInfo().getItems().get(0).getPicMd5Sum());
    }

    @Test
    public void testXmlToSystemPhotoOrAlbumEvent() throws IOException {
        String xml = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("message/event/sys-photo-or-album.xml"));

        XmlMessageHeader xmlRequest = QyXmlMessages.fromXml(xml);

        Assert.assertNotNull(xmlRequest);
        Assert.assertTrue(xmlRequest instanceof QyPhotoEvent);

        Assert.assertEquals(1, ((QyPhotoEvent) xmlRequest).getSendPicsInfo().getCount());
        Assert.assertEquals("5a75aaca956d97be686719218f275c6b", ((QyPhotoEvent) xmlRequest).getSendPicsInfo().getItems().get(0).getPicMd5Sum());
    }

    @Test
    public void testXmlToAlbumEvent() throws IOException {
        String xml = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("message/event/album.xml"));

        XmlMessageHeader xmlRequest = QyXmlMessages.fromXml(xml);

        Assert.assertNotNull(xmlRequest);
        Assert.assertTrue(xmlRequest instanceof QyPhotoEvent);

        Assert.assertEquals(1, ((QyPhotoEvent) xmlRequest).getSendPicsInfo().getCount());
        Assert.assertEquals("5a75aaca956d97be686719218f275c6b", ((QyPhotoEvent) xmlRequest).getSendPicsInfo().getItems().get(0).getPicMd5Sum());
    }

    @Test
    public void testXmlToLocationSelectEvent() throws IOException {
        String xml = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("message/event/location-select.xml"));

        XmlMessageHeader xmlRequest = QyXmlMessages.fromXml(xml);

        Assert.assertNotNull(xmlRequest);
        Assert.assertTrue(xmlRequest instanceof QyLocationSelectEvent);

        Assert.assertEquals("6", ((QyLocationSelectEvent) xmlRequest).getEventKey());
        Assert.assertEquals("23", ((QyLocationSelectEvent) xmlRequest).getSendLocationInfo().getX());
        Assert.assertEquals("113", ((QyLocationSelectEvent) xmlRequest).getSendLocationInfo().getY());
        Assert.assertEquals("15", ((QyLocationSelectEvent) xmlRequest).getSendLocationInfo().getScale());
        Assert.assertEquals(" 广州市海珠区客村艺苑路 106号", ((QyLocationSelectEvent) xmlRequest).getSendLocationInfo().getLabel());
        Assert.assertEquals("", ((QyLocationSelectEvent) xmlRequest).getSendLocationInfo().getPoiName());
    }

    @Test
    public void testXmlToEnterAgentEvent() throws IOException {
        String xml = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("message/event/enter-agent.xml"));

        XmlMessageHeader xmlRequest = QyXmlMessages.fromXml(xml);

        Assert.assertNotNull(xmlRequest);
        Assert.assertTrue(xmlRequest instanceof EnterAgentEvent);

        Assert.assertEquals("enter_agent", ((EnterAgentEvent) xmlRequest).getEventType().name());
    }

    @Test
    public void testXmlToBatchJobEvent() throws IOException {
        String xml = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("message/event/batch-job.xml"));

        XmlMessageHeader xmlRequest = QyXmlMessages.fromXml(xml);

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

        String xml = QyXmlMessages.toXml(textXmlMessage);

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

        String xml = QyXmlMessages.toXml(imageXmlMessage);

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

        String xml = QyXmlMessages.toXml(voiceXmlMessage);

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

        String xml = QyXmlMessages.toXml(videoXmlMessage);

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

        String xml = QyXmlMessages.toXml(newsXmlMessage);

        Assert.assertTrue(xml.contains("<ToUserName><![CDATA[to user]]></ToUserName>"));
        Assert.assertTrue(xml.contains("<FromUserName><![CDATA[from user]]>"));
        Assert.assertTrue(xml.contains("<MsgType><![CDATA[news]]></MsgType>"));
        Assert.assertTrue(xml.contains("<ArticleCount>2</ArticleCount>"));
        Assert.assertTrue(xml.contains("<Articles><item><Title><![CDATA[[1]测试news]]></Title>"));
    }
}

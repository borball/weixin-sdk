package com.riversoft.weixin.mp.request;

import com.riversoft.weixin.common.event.*;
import com.riversoft.weixin.common.message.XmlMessageHeader;
import com.riversoft.weixin.common.request.*;
import com.riversoft.weixin.mp.message.MpXmlMessages;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by exizhai on 11/14/2015.
 */
public class MpXmlMessagesTest {

    @Test
    public void testXmlToText() throws IOException {
        String xml = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("message/text.xml"));

        XmlMessageHeader xmlMessageHeader = MpXmlMessages.fromXml(xml);

        Assert.assertNotNull(xmlMessageHeader);
        Assert.assertTrue(xmlMessageHeader instanceof TextRequest);

        Assert.assertEquals("this is a test", ((TextRequest) xmlMessageHeader).getContent());
    }

    @Test
    public void testXmlToImage() throws IOException {
        String xml = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("message/image.xml"));

        XmlMessageHeader xmlRequest = MpXmlMessages.fromXml(xml);

        Assert.assertNotNull(xmlRequest);
        Assert.assertTrue(xmlRequest instanceof ImageRequest);

        Assert.assertEquals("media_id", ((ImageRequest) xmlRequest).getMediaId());
    }

    @Test
    public void testXmlToVoice() throws IOException {
        String xml = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("message/voice.xml"));

        XmlMessageHeader xmlRequest = MpXmlMessages.fromXml(xml);

        Assert.assertNotNull(xmlRequest);
        Assert.assertTrue(xmlRequest instanceof VoiceRequest);

        Assert.assertEquals("media_id", ((VoiceRequest) xmlRequest).getMediaId());
        Assert.assertEquals("Format", ((VoiceRequest) xmlRequest).getFormat());
    }

    @Test
    public void testXmlToVideo() throws IOException {
        String xml = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("message/video.xml"));

        XmlMessageHeader xmlRequest = MpXmlMessages.fromXml(xml);

        Assert.assertNotNull(xmlRequest);
        Assert.assertTrue(xmlRequest instanceof VideoRequest);

        Assert.assertEquals("media_id", ((VideoRequest) xmlRequest).getMediaId());
        Assert.assertEquals("thumb_media_id", ((VideoRequest) xmlRequest).getThumbMediaId());
    }

    @Test
    public void testXmlToLink() throws IOException {
        String xml = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("message/link.xml"));

        XmlMessageHeader xmlRequest = MpXmlMessages.fromXml(xml);

        Assert.assertNotNull(xmlRequest);
        Assert.assertTrue(xmlRequest instanceof LinkRequest);

        Assert.assertEquals("公众平台官网链接", ((LinkRequest) xmlRequest).getTitle());
        Assert.assertEquals("公众平台官网链接", ((LinkRequest) xmlRequest).getDesc());
        Assert.assertEquals("url", ((LinkRequest) xmlRequest).getUrl());
    }

    @Test
    public void testXmlToShortVideo() throws IOException {
        String xml = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("message/shortvideo.xml"));

        XmlMessageHeader xmlRequest = MpXmlMessages.fromXml(xml);

        Assert.assertNotNull(xmlRequest);
        Assert.assertTrue(xmlRequest instanceof ShortVideoRequest);

        Assert.assertEquals("media_id", ((ShortVideoRequest) xmlRequest).getMediaId());
        Assert.assertEquals("thumb_media_id", ((ShortVideoRequest) xmlRequest).getThumbMediaId());
    }

    @Test
    public void testXmlToLocation() throws IOException {
        String xml = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("message/location.xml"));

        XmlMessageHeader xmlRequest = MpXmlMessages.fromXml(xml);

        Assert.assertNotNull(xmlRequest);
        Assert.assertTrue(xmlRequest instanceof LocationRequest);

        Assert.assertEquals("23.134521", ((LocationRequest) xmlRequest).getX());
        Assert.assertEquals("113.358803", ((LocationRequest) xmlRequest).getY());
        Assert.assertEquals("20", ((LocationRequest) xmlRequest).getScale());
        Assert.assertEquals("位置信息", ((LocationRequest) xmlRequest).getLabel());
    }

    @Test
    public void testXmlToSubscribeEvent() throws IOException {
        String xml = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("event/subscribe.xml"));

        XmlMessageHeader xmlRequest = MpXmlMessages.fromXml(xml);

        Assert.assertNotNull(xmlRequest);
        Assert.assertTrue(xmlRequest instanceof SubscriptionEvent);

        Assert.assertTrue(((SubscriptionEvent) xmlRequest).isSub());

    }

    @Test
    public void testXmlToUnSubscribeEvent() throws IOException {
        String xml = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("event/unsubscribe.xml"));

        XmlMessageHeader xmlRequest = MpXmlMessages.fromXml(xml);

        Assert.assertNotNull(xmlRequest);
        Assert.assertTrue(xmlRequest instanceof SubscriptionEvent);

        Assert.assertFalse(((SubscriptionEvent) xmlRequest).isSub());

    }

    @Test
    public void testXmlToLocationReportEvent() throws IOException {
        String xml = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("event/location-report.xml"));

        XmlMessageHeader xmlRequest = MpXmlMessages.fromXml(xml);

        Assert.assertNotNull(xmlRequest);
        Assert.assertTrue(xmlRequest instanceof LocationReportEvent);

        Assert.assertEquals("23.104105", ((LocationReportEvent) xmlRequest).getLatitude());
        Assert.assertEquals("113.320107", ((LocationReportEvent) xmlRequest).getLongitude());
        Assert.assertEquals("65.000000", ((LocationReportEvent) xmlRequest).getPrecision());
    }

    @Test
    public void testXmlToClickEvent() throws IOException {
        String xml = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("event/click.xml"));

        XmlMessageHeader xmlRequest = MpXmlMessages.fromXml(xml);

        Assert.assertNotNull(xmlRequest);
        Assert.assertTrue(xmlRequest instanceof ClickEvent);

        Assert.assertEquals("EVENTKEY", ((ClickEvent) xmlRequest).getEventKey());
    }

    @Test
    public void testXmlToViewEvent() throws IOException {
        String xml = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("event/view.xml"));

        XmlMessageHeader xmlRequest = MpXmlMessages.fromXml(xml);

        Assert.assertNotNull(xmlRequest);
        Assert.assertTrue(xmlRequest instanceof ViewEvent);

        Assert.assertEquals("www.qq.com", ((ViewEvent) xmlRequest).getUrl());
    }

    @Test
    public void testXmlToScanPushEvent() throws IOException {
        String xml = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("event/scan-push.xml"));

        XmlMessageHeader xmlRequest = MpXmlMessages.fromXml(xml);

        Assert.assertNotNull(xmlRequest);
        Assert.assertTrue(xmlRequest instanceof ScanEvent);

        Assert.assertEquals("qrcode", ((ScanEvent) xmlRequest).getScanCodeInfo().getScanType());
        Assert.assertEquals("1", ((ScanEvent) xmlRequest).getScanCodeInfo().getScanResult());
    }

    @Test
    public void testXmlToScanWaitMsgEvent() throws IOException {
        String xml = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("event/scan-waitmsg.xml"));

        XmlMessageHeader xmlRequest = MpXmlMessages.fromXml(xml);

        Assert.assertNotNull(xmlRequest);
        Assert.assertTrue(xmlRequest instanceof ScanEvent);

        Assert.assertEquals("qrcode", ((ScanEvent) xmlRequest).getScanCodeInfo().getScanType());
        Assert.assertEquals("2", ((ScanEvent) xmlRequest).getScanCodeInfo().getScanResult());
    }

    @Test
    public void testXmlToSystemPhotoEvent() throws IOException {
        String xml = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("event/sys-photo.xml"));

        XmlMessageHeader xmlRequest = MpXmlMessages.fromXml(xml);

        Assert.assertNotNull(xmlRequest);
        Assert.assertTrue(xmlRequest instanceof PhotoEvent);

        Assert.assertEquals(1, ((PhotoEvent) xmlRequest).getSendPicsInfo().getCount());
        Assert.assertEquals("1b5f7c23b5bf75682a53e7b6d163e185", ((PhotoEvent) xmlRequest).getSendPicsInfo().getItems().get(0).getPicMd5Sum());
    }

    @Test
    public void testXmlToSystemPhotoOrAlbumEvent() throws IOException {
        String xml = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("event/sys-photo-or-album.xml"));

        XmlMessageHeader xmlRequest = MpXmlMessages.fromXml(xml);

        Assert.assertNotNull(xmlRequest);
        Assert.assertTrue(xmlRequest instanceof PhotoEvent);

        Assert.assertEquals(1, ((PhotoEvent) xmlRequest).getSendPicsInfo().getCount());
        Assert.assertEquals("5a75aaca956d97be686719218f275c6b", ((PhotoEvent) xmlRequest).getSendPicsInfo().getItems().get(0).getPicMd5Sum());
    }

    @Test
    public void testXmlToAlbumEvent() throws IOException {
        String xml = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("event/album.xml"));

        XmlMessageHeader xmlRequest = MpXmlMessages.fromXml(xml);

        Assert.assertNotNull(xmlRequest);
        Assert.assertTrue(xmlRequest instanceof PhotoEvent);

        Assert.assertEquals(1, ((PhotoEvent) xmlRequest).getSendPicsInfo().getCount());
        Assert.assertEquals("5a75aaca956d97be686719218f275c6b", ((PhotoEvent) xmlRequest).getSendPicsInfo().getItems().get(0).getPicMd5Sum());
    }

    @Test
    public void testXmlToLocationSelectEvent() throws IOException {
        String xml = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("event/location-select.xml"));

        XmlMessageHeader xmlRequest = MpXmlMessages.fromXml(xml);

        Assert.assertNotNull(xmlRequest);
        Assert.assertTrue(xmlRequest instanceof LocationSelectEvent);

        Assert.assertEquals("6", ((LocationSelectEvent) xmlRequest).getEventKey());
        Assert.assertEquals("23", ((LocationSelectEvent) xmlRequest).getSendLocationInfo().getX());
        Assert.assertEquals("113", ((LocationSelectEvent) xmlRequest).getSendLocationInfo().getY());
        Assert.assertEquals("15", ((LocationSelectEvent) xmlRequest).getSendLocationInfo().getScale());
        Assert.assertEquals(" 广州市海珠区客村艺苑路 106号", ((LocationSelectEvent) xmlRequest).getSendLocationInfo().getLabel());
        Assert.assertEquals("", ((LocationSelectEvent) xmlRequest).getSendLocationInfo().getPoiName());
    }

}

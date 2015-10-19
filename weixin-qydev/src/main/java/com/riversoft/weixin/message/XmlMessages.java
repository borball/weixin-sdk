package com.riversoft.weixin.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.riversoft.weixin.exception.WxRuntimeException;
import com.riversoft.weixin.message.request.*;
import com.riversoft.weixin.message.request.event.*;
import com.riversoft.weixin.message.xml.XmlMessageHeader;
import com.riversoft.weixin.util.XmlObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by exizhai on 9/29/2015.
 */
public class XmlMessages {

    private static Logger logger = LoggerFactory.getLogger(XmlMessages.class);

    public static XmlRequest fromXml(String xml) {
        try {
            XmlRequest xmlRequest = (XmlRequest) XmlObjectMapper.defaultMapper().fromXml(xml, XmlRequest.class);
            switch (xmlRequest.getMsgType()) {
                case text:
                    return (TextXmlRequest) XmlObjectMapper.defaultMapper().fromXml(xml, TextXmlRequest.class);
                case image:
                    return (ImageXmlRequest) XmlObjectMapper.defaultMapper().fromXml(xml, ImageXmlRequest.class);
                case voice:
                    return (VoiceXmlRequest) XmlObjectMapper.defaultMapper().fromXml(xml, VoiceXmlRequest.class);
                case video:
                    return (VideoXmlRequest) XmlObjectMapper.defaultMapper().fromXml(xml, VideoXmlRequest.class);
                case shortvideo:
                    return (ShortVideoXmlRequest) XmlObjectMapper.defaultMapper().fromXml(xml, ShortVideoXmlRequest.class);
                case location:
                    return (LocationXmlRequest) XmlObjectMapper.defaultMapper().fromXml(xml, LocationXmlRequest.class);
                case event:
                    return toEvent(xml);
                default:
                    logger.warn("xml to bean failed, unknown message type {}.", xmlRequest.getMsgType());
                    throw new WxRuntimeException(999, "xml to bean failed, unknown message type " + xmlRequest.getMsgType());
            }
        } catch (IOException e) {
            logger.error("xml to message request failed", e);
            throw new WxRuntimeException(999, "xml to message request failed," + e.getMessage());
        }
    }

    private static XmlRequest toEvent(String xml) {
        try {
            EventXmlRequest eventXmlRequest = (EventXmlRequest) XmlObjectMapper.defaultMapper().fromXml(xml, EventXmlRequest.class);
            switch (eventXmlRequest.getEventType()) {
                case subscribe:
                case unsubscribe:
                    return (SubscriptionEvent) XmlObjectMapper.defaultMapper().fromXml(xml, SubscriptionEvent.class);
                case LOCATION:
                    return (LocationReportEvent) XmlObjectMapper.defaultMapper().fromXml(xml, LocationReportEvent.class);
                case click:
                    return (ClickEvent) XmlObjectMapper.defaultMapper().fromXml(xml, ClickEvent.class);
                case view:
                    return (ViewEvent) XmlObjectMapper.defaultMapper().fromXml(xml, ViewEvent.class);
                case scancode_push:
                case scancode_waitmsg:
                    return (ScanEvent) XmlObjectMapper.defaultMapper().fromXml(xml, ScanEvent.class);
                case pic_photo_or_album:
                case pic_sysphoto:
                case pic_weixin:
                    return (PhotoEvent) XmlObjectMapper.defaultMapper().fromXml(xml, PhotoEvent.class);
                case location_select:
                    return (LocationSelectEvent) XmlObjectMapper.defaultMapper().fromXml(xml, LocationSelectEvent.class);
                case enter_agent:
                    return (EnterAgentEvent) XmlObjectMapper.defaultMapper().fromXml(xml, EnterAgentEvent.class);
                case batch_job_result:
                    return (BatchJobResultEvent) XmlObjectMapper.defaultMapper().fromXml(xml, BatchJobResultEvent.class);
                default:
                    logger.warn("xml to event, unknown event type {}.", eventXmlRequest.getEventType());
                    throw new WxRuntimeException(999, "xml to bean event, unknown event type " + eventXmlRequest.getEventType());
            }
        } catch (IOException e) {
            logger.error("xml to event failed", e);
            throw new WxRuntimeException(999, "xml to event failed," + e.getMessage());
        }
    }

    public static String toXml(XmlMessageHeader xmlMessage) {
        try {
            return XmlObjectMapper.defaultMapper().toXml(xmlMessage);
        } catch (JsonProcessingException e) {
            logger.error("message to xml failed", e);
            throw new WxRuntimeException(999, "message to xml failed," + e.getMessage());
        }
    }
}

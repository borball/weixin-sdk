package com.riversoft.weixin.mp.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.riversoft.weixin.common.event.*;
import com.riversoft.weixin.common.exception.WxRuntimeException;
import com.riversoft.weixin.common.message.XmlMessageHeader;
import com.riversoft.weixin.common.request.*;
import com.riversoft.weixin.common.util.XmlObjectMapper;
import com.riversoft.weixin.mp.event.MessageSentEvent;
import com.riversoft.weixin.mp.event.care.SessionCloseEvent;
import com.riversoft.weixin.mp.event.care.SessionCreateEvent;
import com.riversoft.weixin.mp.event.care.SessionForwardEvent;
import com.riversoft.weixin.mp.event.shop.OrderEvent;
import com.riversoft.weixin.mp.event.template.JobFinishedEvent;
import com.riversoft.weixin.mp.event.ticket.SceneScanEvent;
import com.riversoft.weixin.mp.event.ticket.SceneSubEvent;
import com.riversoft.weixin.mp.request.LinkRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by exizhai on 9/29/2015.
 */
public class MpXmlMessages {

    private static Logger logger = LoggerFactory.getLogger(MpXmlMessages.class);

    public static XmlMessageHeader fromXml(String xml) {
        try {
            XmlMessageHeader xmlRequest = XmlObjectMapper.defaultMapper().fromXml(xml, XmlMessageHeader.class);
            switch (xmlRequest.getMsgType()) {
                case text:
                    return XmlObjectMapper.defaultMapper().fromXml(xml, TextRequest.class);
                case image:
                    return XmlObjectMapper.defaultMapper().fromXml(xml, ImageRequest.class);
                case voice:
                    return XmlObjectMapper.defaultMapper().fromXml(xml, VoiceRequest.class);
                case video:
                    return XmlObjectMapper.defaultMapper().fromXml(xml, VideoRequest.class);
                case shortvideo:
                    return XmlObjectMapper.defaultMapper().fromXml(xml, ShortVideoRequest.class);
                case location:
                    return XmlObjectMapper.defaultMapper().fromXml(xml, LocationRequest.class);
                case link:
                    return XmlObjectMapper.defaultMapper().fromXml(xml, LinkRequest.class);
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

    private static EventRequest toEvent(String xml) {
        try {
            EventRequest eventRequest = XmlObjectMapper.defaultMapper().fromXml(xml, EventRequest.class);
            switch (eventRequest.getEventType()) {
                case subscribe:
                case unsubscribe:
                    return XmlObjectMapper.defaultMapper().fromXml(xml, SceneSubEvent.class);
                case LOCATION:
                    return XmlObjectMapper.defaultMapper().fromXml(xml, LocationReportEvent.class);
                case CLICK:
                    return XmlObjectMapper.defaultMapper().fromXml(xml, ClickEvent.class);
                case VIEW:
                    return XmlObjectMapper.defaultMapper().fromXml(xml, ViewEvent.class);
                case scancode_push:
                case scancode_waitmsg:
                    return XmlObjectMapper.defaultMapper().fromXml(xml, ScanEvent.class);
                case pic_photo_or_album:
                case pic_sysphoto:
                case pic_weixin:
                    return XmlObjectMapper.defaultMapper().fromXml(xml, PhotoEvent.class);
                case location_select:
                    return XmlObjectMapper.defaultMapper().fromXml(xml, LocationSelectEvent.class);
                case kf_create_session:
                    return XmlObjectMapper.defaultMapper().fromXml(xml, SessionCreateEvent.class);
                case kf_close_session:
                    return XmlObjectMapper.defaultMapper().fromXml(xml, SessionCloseEvent.class);
                case kf_switch_session:
                    return XmlObjectMapper.defaultMapper().fromXml(xml, SessionForwardEvent.class);
                case MASSSENDJOBFINISH:
                    return XmlObjectMapper.defaultMapper().fromXml(xml, MessageSentEvent.class);
                case SCAN:
                    return XmlObjectMapper.defaultMapper().fromXml(xml, SceneScanEvent.class);
                case TEMPLATESENDJOBFINISH:
                    return XmlObjectMapper.defaultMapper().fromXml(xml, JobFinishedEvent.class);
                case ORDER:
                    return XmlObjectMapper.defaultMapper().fromXml(xml, OrderEvent.class);
                default:
                    logger.warn("xml to event, unknown event type {}.", eventRequest.getEventType());
                    throw new WxRuntimeException(999, "xml to bean event, unknown event type " + eventRequest.getEventType());
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

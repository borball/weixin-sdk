package com.riversoft.weixin.qy.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.riversoft.weixin.common.event.EventRequest;
import com.riversoft.weixin.common.exception.WxRuntimeException;
import com.riversoft.weixin.common.message.XmlMessageHeader;
import com.riversoft.weixin.common.util.XmlObjectMapper;
import com.riversoft.weixin.qy.event.*;
import com.riversoft.weixin.qy.request.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by exizhai on 9/29/2015.
 */
public class QyXmlMessages {

    private static Logger logger = LoggerFactory.getLogger(QyXmlMessages.class);

    public static XmlMessageHeader fromXml(String xml) {
        try {
            XmlMessageHeader xmlRequest = XmlObjectMapper.defaultMapper().fromXml(xml, XmlMessageHeader.class);
            switch (xmlRequest.getMsgType()) {
                case text:
                    return XmlObjectMapper.defaultMapper().fromXml(xml, QyTextRequest.class);
                case image:
                    return XmlObjectMapper.defaultMapper().fromXml(xml, QyImageRequest.class);
                case voice:
                    return XmlObjectMapper.defaultMapper().fromXml(xml, QyVoiceRequest.class);
                case video:
                    return XmlObjectMapper.defaultMapper().fromXml(xml, QyVideoRequest.class);
                case shortvideo:
                    return XmlObjectMapper.defaultMapper().fromXml(xml, QyShortVideoRequest.class);
                case location:
                    return XmlObjectMapper.defaultMapper().fromXml(xml, QyLocationRequest.class);
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
            EventRequest qyEventRequest = XmlObjectMapper.defaultMapper().fromXml(xml, EventRequest.class);
            switch (qyEventRequest.getEventType()) {
                case subscribe:
                case unsubscribe:
                    return XmlObjectMapper.defaultMapper().fromXml(xml, QySubscriptionEvent.class);
                case LOCATION:
                    return XmlObjectMapper.defaultMapper().fromXml(xml, QyLocationReportEvent.class);
                case click:
                    return XmlObjectMapper.defaultMapper().fromXml(xml, QyClickEvent.class);
                case view:
                    return XmlObjectMapper.defaultMapper().fromXml(xml, QyViewEvent.class);
                case scancode_push:
                case scancode_waitmsg:
                    return XmlObjectMapper.defaultMapper().fromXml(xml, QyScanEvent.class);
                case pic_photo_or_album:
                case pic_sysphoto:
                case pic_weixin:
                    return XmlObjectMapper.defaultMapper().fromXml(xml, QyPhotoEvent.class);
                case location_select:
                    return XmlObjectMapper.defaultMapper().fromXml(xml, QyLocationSelectEvent.class);
                case enter_agent:
                    return XmlObjectMapper.defaultMapper().fromXml(xml, EnterAgentEvent.class);
                case batch_job_result:
                    return XmlObjectMapper.defaultMapper().fromXml(xml, BatchJobResultEvent.class);
                default:
                    logger.warn("xml to event, unknown event type {}.", qyEventRequest.getEventType());
                    throw new WxRuntimeException(999, "xml to bean event, unknown event type " + qyEventRequest.getEventType());
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

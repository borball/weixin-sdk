package com.riversoft.weixin.mp.message.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.riversoft.weixin.common.message.MsgType;
import com.riversoft.weixin.common.message.XmlMessageHeader;

/**
 * Created by exizhai on 11/25/2015.
 */
@JacksonXmlRootElement(localName = "xml")
public class Forward2CareXmlMessage extends XmlMessageHeader{

    public Forward2CareXmlMessage(){
        setMsgType(MsgType.transfer_customer_service);
    }
}

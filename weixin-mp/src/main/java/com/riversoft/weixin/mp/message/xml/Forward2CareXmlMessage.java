package com.riversoft.weixin.mp.message.xml;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.riversoft.weixin.common.message.MsgType;
import com.riversoft.weixin.common.message.XmlMessageHeader;

/**
 * Created by exizhai on 11/25/2015.
 */
@JacksonXmlRootElement(localName = "xml")
public class Forward2CareXmlMessage extends XmlMessageHeader {

    @JacksonXmlProperty(localName = "TransInfo")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private TransInfo transInfo;

    public Forward2CareXmlMessage() {
        setMsgType(MsgType.transfer_customer_service);
    }

    public void setAccount(String account){
        this.transInfo = new TransInfo(account);
    }

    public static class TransInfo {

        @JacksonXmlProperty(localName = "KfAccount")
        @JacksonXmlCData
        private String account;

        public TransInfo(){
        }

        public TransInfo(String account) {
            this.account = account;
        }
    }
}

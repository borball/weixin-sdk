package com.riversoft.weixin.pay.redpack;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.riversoft.weixin.common.util.JsonMapper;
import com.riversoft.weixin.common.util.XmlObjectMapper;
import com.riversoft.weixin.pay.redpack.bean.RedPackRequest;
import org.junit.Assert;
import org.junit.Test;

import java.util.SortedMap;

/**
 * Created by exizhai on 12/1/2015.
 */
public class Bean2MapTest {

    @Test
    public void testBean2Map(){
        RedPackRequest redPackRequest = new RedPackRequest();
        redPackRequest.setActivityName("test sendSingle red pack");
        redPackRequest.setAmount(1);
        redPackRequest.setBillNumber("BB-111110000");
        redPackRequest.setNumber(1);
        redPackRequest.setOpenId("wx-openid");
        redPackRequest.setRemark("备注");
        redPackRequest.setWishing("祝福语");
        redPackRequest.setClientIp("127.0.0.1");
        redPackRequest.setSendName("商戶名");

        SortedMap<String, Object> map = JsonMapper.defaultMapper().getMapper().convertValue(redPackRequest, SortedMap.class);

        Assert.assertNotNull(map);

        Assert.assertTrue("act_name".equals(map.firstKey()));
        Assert.assertTrue("wishing".equals(map.lastKey()));

        try {
            RedPackRequestWrapper wrapper = new RedPackRequestWrapper();
            wrapper.setRedPackRequest(redPackRequest);

            RedPacks.AppSettingMixin appSettingMixin = new RedPacks.AppSettingMixin();
            appSettingMixin.setMchId("mchId");
            appSettingMixin.setNonce("nonce");
            appSettingMixin.setSign("signsignsignsign");
            wrapper.setAppSettingMixin(appSettingMixin);
            String xml = XmlObjectMapper.prettyFormatMapper().toXml(wrapper);
            System.out.println(xml);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @JacksonXmlRootElement(localName = "xml")
    class RedPackRequestWrapper {

        @JsonUnwrapped
        private RedPackRequest redPackRequest;

        @JsonUnwrapped
        private RedPacks.AppSettingMixin appSettingMixin;

        public RedPackRequest getRedPackRequest() {
            return redPackRequest;
        }

        public void setRedPackRequest(RedPackRequest redPackRequest) {
            this.redPackRequest = redPackRequest;
        }

        public RedPacks.AppSettingMixin getAppSettingMixin() {
            return appSettingMixin;
        }

        public void setAppSettingMixin(RedPacks.AppSettingMixin appSettingMixin) {
            this.appSettingMixin = appSettingMixin;
        }
    }
}

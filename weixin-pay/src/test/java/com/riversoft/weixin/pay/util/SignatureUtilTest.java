package com.riversoft.weixin.pay.util;

import com.riversoft.weixin.common.util.JsonMapper;
import com.riversoft.weixin.common.util.XmlObjectMapper;
import com.riversoft.weixin.pay.payment.Payments;
import com.riversoft.weixin.pay.payment.bean.PaymentNotification;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.SortedMap;

/**
 * @borball on 6/3/2016.
 */
public class SignatureUtilTest {

    @Test
    public void testSign() throws IOException {
        PaymentNotification paymentNotification = XmlObjectMapper.nonEmptyMapper().fromXml("<?xml version=\"1.0\"?>\n" +
                "<xml>\n" +
                "  <appid><![CDATA[wxd1a32e23ee80bf7a]]></appid>\n" +
                "  <bank_type><![CDATA[BOC_CREDIT]]></bank_type>\n" +
                "  <cash_fee><![CDATA[55]]></cash_fee>\n" +
                "  <fee_type><![CDATA[CNY]]></fee_type>\n" +
                "  <is_subscribe><![CDATA[Y]]></is_subscribe>\n" +
                "  <mch_id><![CDATA[1292063901]]></mch_id>\n" +
                "  <nonce_str><![CDATA[aufr8u2z6cbcy3r0dqz17xtdckfe98ff]]></nonce_str>\n" +
                "  <openid><![CDATA[oELhlt7JHGmSC47bG0hmm4L1LYzg]]></openid>\n" +
                "  <out_trade_no><![CDATA[1292063901201606031042082995]]></out_trade_no>\n" +
                "  <result_code><![CDATA[SUCCESS]]></result_code>\n" +
                "  <return_code><![CDATA[SUCCESS]]></return_code>\n" +
                "  <sign><![CDATA[278617625B434068D2FE3AA9998F180C]]></sign>\n" +
                "  <time_end><![CDATA[20160603104226]]></time_end>\n" +
                "  <total_fee>55</total_fee>\n" +
                "  <trade_type><![CDATA[JSAPI]]></trade_type>\n" +
                "  <coupon_fee>300</coupon_fee>\n" +
                "  <coupon_count>3</coupon_count>\n" +
                "  <coupon_type_0>CASH</coupon_type_0>\n" +
                "  <coupon_fee_0>100</coupon_fee_0>\n" +
                "  <coupon_id_0>coupon1</coupon_id_0>\n" +
                "  <coupon_type_1>CASH</coupon_type_1>\n" +
                "  <coupon_fee_1>100</coupon_fee_1>\n" +
                "  <coupon_id_1>coupon2</coupon_id_1>\n" +
                "  <coupon_type_2>CASH</coupon_type_2>\n" +
                "  <coupon_fee_2>100</coupon_fee_2>\n" +
                "  <coupon_id_2>coupon2</coupon_id_2>\n" +
                "  <transaction_id><![CDATA[4009722001201606036693604419]]></transaction_id>\n" +
                "</xml>\n", PaymentNotification.class);

        Assert.assertTrue(9 == paymentNotification.getOthers().size());
        SortedMap<String, Object> notificationMap = JsonMapper.nonEmptyMapper().getMapper().convertValue(paymentNotification, SortedMap.class);
        notificationMap.putAll(paymentNotification.getOthers());
        notificationMap.remove("sign");
        Assert.assertEquals(paymentNotification.getSign(), SignatureUtil.sign(notificationMap, "key123"));

    }

}

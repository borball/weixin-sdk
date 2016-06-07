package com.riversoft.weixin.pay.mp;

import com.riversoft.weixin.common.util.XmlObjectMapper;
import com.riversoft.weixin.pay.mp.bean.PaymentNotification;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * @borball on 6/3/2016.
 */
public class SignatureTest {

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
                "  <sign><![CDATA[C6A34BFED2958B60543876DB94482ABC]]></sign>\n" +
                "  <time_end><![CDATA[20160603104226]]></time_end>\n" +
                "  <total_fee>55</total_fee>\n" +
                "  <trade_type><![CDATA[JSAPI]]></trade_type>\n" +
                "  <transaction_id><![CDATA[4009722001201606036693604419]]></transaction_id>\n" +
                "</xml>\n", PaymentNotification.class);
        Assert.assertTrue(Orders.defaultOrders().checkSignature(paymentNotification));
    }
}

package com.riversoft.weixin.pay.payment;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.riversoft.weixin.pay.payment.wrapper.RefundQueryWrapper;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
/**
 * @borball on 12/29/2016.
 */
public class JacksonTest {

    @Test
    public void testXml2Bean() throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        RefundQueryWrapper refundQueryWrapper =  xmlMapper.readValue(getXml(), RefundQueryWrapper.class);
        Assert.assertNotNull(refundQueryWrapper);
        Assert.assertNotNull(refundQueryWrapper.getRefundQuery());

    }

    private String getXml(){
        return "<xml>\n" +
                "   <appid><![CDATA[wx2421b1c4370ec43b]]></appid>\n" +
                "   <mch_id><![CDATA[10000100]]></mch_id>\n" +
                "   <nonce_str><![CDATA[TeqClE3i0mvn3DrK]]></nonce_str>\n" +
                "   <out_refund_no_0><![CDATA[1415701182]]></out_refund_no_0>\n" +
                "   <out_trade_no><![CDATA[1415757673]]></out_trade_no>\n" +
                "   <cash_fee><![CDATA[10]]></cash_fee>\n" +
                "   <refund_count>2</refund_count>\n" +
                "   <refund_fee_0>10</refund_fee_0>\n" +
                "   <refund_id_0><![CDATA[2008450740201411110000174436]]></refund_id_0>\n" +
                "   <refund_status_0><![CDATA[PROCESSING]]></refund_status_0>\n" +
                "   <refund_channel_0><![CDATA[ORIGINAL]]></refund_channel_0>\n" +
                "   <settlement_refund_fee_0>10</settlement_refund_fee_0>\n" +
                "   <coupon_type_0><![CDATA[CASH]]></coupon_type_0>\n" +
                "   <coupon_refund_fee_0>5</coupon_refund_fee_0>\n" +
                "   <coupon_refund_count_0>2</coupon_refund_count_0>\n" +
                "   <coupon_refund_id_0_0><![CDATA[coupon_0_0]]></coupon_refund_id_0_0>\n" +
                "   <coupon_refund_id_0_1><![CDATA[coupon_0_1]]></coupon_refund_id_0_1>\n" +
                "   <coupon_refund_id_1_0><![CDATA[coupon_1_0]]></coupon_refund_id_1_0>\n" +
                "   <coupon_refund_id_1_1><![CDATA[coupon_1_1]]></coupon_refund_id_1_1>\n" +
                "   <coupon_refund_fee_0_0>100</coupon_refund_fee_0_0>\n" +
                "   <coupon_refund_fee_0_1>101</coupon_refund_fee_0_1>\n" +
                "   <coupon_refund_fee_1_0>110</coupon_refund_fee_1_0>\n" +
                "   <coupon_refund_fee_1_1>111</coupon_refund_fee_1_1>    \n" +
                "   <refund_recv_accout_0><![CDATA[receive account 0]]></refund_recv_accout_0>\n" +
                "   <refund_fee_1>10</refund_fee_1>\n" +
                "   <refund_id_1><![CDATA[2008450740201411110000174436]]></refund_id_1>\n" +
                "   <refund_status_1><![CDATA[PROCESSING]]></refund_status_1>\n" +
                "   <refund_channel_1><![CDATA[ORIGINAL]]></refund_channel_1>\n" +
                "   <settlement_refund_fee_1>10</settlement_refund_fee_1>\n" +
                "   <coupon_type_1><![CDATA[CASH]]></coupon_type_1>\n" +
                "   <coupon_refund_fee_1>5</coupon_refund_fee_1>\n" +
                "   <coupon_refund_count_1>2</coupon_refund_count_1>\n" +
                "   <refund_recv_accout_1><![CDATA[receive account 0]]></refund_recv_accout_1>   \n" +
                "   <result_code><![CDATA[SUCCESS]]></result_code>\n" +
                "   <return_code><![CDATA[SUCCESS]]></return_code>\n" +
                "   <return_msg><![CDATA[OK]]></return_msg>\n" +
                "   <sign><![CDATA[1F2841558E233C33ABA71A961D27561C]]></sign>\n" +
                "   <transaction_id><![CDATA[1008450740201411110005820873]]></transaction_id>\n" +
                "</xml>\n";
    }

}

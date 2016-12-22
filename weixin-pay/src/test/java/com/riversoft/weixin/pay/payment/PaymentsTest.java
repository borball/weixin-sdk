package com.riversoft.weixin.pay.payment;

import com.riversoft.weixin.pay.base.BaseResponse;
import com.riversoft.weixin.pay.payment.bean.OrderQueryRequest;
import com.riversoft.weixin.pay.payment.bean.OrderQueryResponse;
import com.riversoft.weixin.pay.payment.bean.UnifiedOrderRequest;
import com.riversoft.weixin.pay.payment.bean.UnifiedOrderResponse;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

/**
 * @borball on 5/15/2016.
 */
public class PaymentsTest {

    @Test
    public void testUnifiedOrder(){
        UnifiedOrderRequest unifiedOrderRequest = new UnifiedOrderRequest();
        unifiedOrderRequest.setBody("牛奶 小号");
        unifiedOrderRequest.setDetail("小号牛奶 鲜奶");
        unifiedOrderRequest.setTradeNumber("1292063901201605160012300016");
        unifiedOrderRequest.setTotalFee(100);
        unifiedOrderRequest.setBillCreatedIp("192.168.1.103");
        unifiedOrderRequest.setNotifyUrl("http://gzriver.com/order/pay/notify");
        unifiedOrderRequest.setTradeType("JSAPI");
        unifiedOrderRequest.setOpenId("oELhlt7Q-lRmLbRsPsaKeVX6pqjg");

        UnifiedOrderResponse response = Payments.defaultOrders().unifiedOrder(unifiedOrderRequest);

        Assert.assertNotNull(response);

    }

    @Test
    public void testQueryOrder(){
        OrderQueryRequest orderQueryRequest = new OrderQueryRequest();
        orderQueryRequest.setTradeNumber("1292063901201605160012300015");

        OrderQueryResponse response = Payments.defaultOrders().query(orderQueryRequest);
        Assert.assertNotNull(response);
    }

    @Test
    public void testCloseOrder(){
        BaseResponse response = Payments.defaultOrders().close("1292063901201605160012300015");
        Assert.assertNotNull(response);
    }

    @Test
    public void testDownloadBill(){
        String response = Payments.defaultOrders().downloadAllBill(new Date());
        Assert.assertNotNull(response);
    }
}

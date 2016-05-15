package com.riversoft.weixin.pay.mp;

import com.riversoft.weixin.pay.base.BaseResponse;
import com.riversoft.weixin.pay.mp.bean.OrderQueryRequest;
import com.riversoft.weixin.pay.mp.bean.OrderQueryResponse;
import com.riversoft.weixin.pay.mp.bean.UnifiedOrderRequest;
import com.riversoft.weixin.pay.mp.bean.UnifiedOrderResponse;
import org.junit.Assert;
import org.junit.Test;

/**
 * @borball on 5/15/2016.
 */
public class OrdersTest {

    @Test
    public void testUnifiedOrder(){
        UnifiedOrderRequest unifiedOrderRequest = new UnifiedOrderRequest();
        unifiedOrderRequest.setBody("牛奶 小号");
        unifiedOrderRequest.setDetail("小号牛奶 鲜奶");
        unifiedOrderRequest.setTradeNumber("1292063901201605160012300015");
        unifiedOrderRequest.setTotalFee(100);
        unifiedOrderRequest.setBillCreatedIp("192.168.1.103");
        unifiedOrderRequest.setNotifyUrl("http://gzriver.com/order/pay/notify");
        unifiedOrderRequest.setTradeType("JSAPI");
        unifiedOrderRequest.setOpenId("oELhlt7Q-lRmLbRsPsaKeVX6pqjg");

        UnifiedOrderResponse response = Orders.defaultOrders().unifiedOrder(unifiedOrderRequest);

        Assert.assertNotNull(response);

    }

    @Test
    public void testQueryOrder(){
        OrderQueryRequest orderQueryRequest = new OrderQueryRequest();
        orderQueryRequest.setTradeNumber("1292063901201605160012300015");

        OrderQueryResponse response = Orders.defaultOrders().query(orderQueryRequest);
        Assert.assertNotNull(response);
    }

    @Test
    public void testCloseOrder(){
        BaseResponse response = Orders.defaultOrders().close("1292063901201605160012300015");
        Assert.assertNotNull(response);
    }
}

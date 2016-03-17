package com.riversoft.weixin.mp.shop;

import org.junit.Test;

import java.util.Calendar;
import java.util.List;

/**
 * @borball on 3/17/2016.
 */
public class OrdersTest {

    @Test
    public void testQuery(){
        Calendar end = Calendar.getInstance();

        Calendar start = Calendar.getInstance();
        start.add(Calendar.MONTH, -1);
        List<Order> orders = Orders.defaultOrders().query(null, start.getTime(), null);
    }


    @Test
    public void testGet(){
        Order order = Orders.defaultOrders().get("13215171060853012180");
    }

}

package com.riversoft.weixin.mp.shop;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.riversoft.weixin.common.WxClient;
import com.riversoft.weixin.common.util.JsonMapper;
import com.riversoft.weixin.mp.MpWxClientFactory;
import com.riversoft.weixin.mp.base.AppSetting;
import com.riversoft.weixin.mp.base.WxEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 订单管理相关接口
 *
 * @borball on 3/17/2016.
 */
public class Orders {

    private static Logger logger = LoggerFactory.getLogger(Orders.class);
    private WxClient wxClient;

    public static Orders defaultOrders() {
        return with(AppSetting.defaultSettings());
    }

    public static Orders with(AppSetting appSetting) {
        Orders orders = new Orders();
        orders.setWxClient(MpWxClientFactory.getInstance().with(appSetting));
        return orders;
    }

    public void setWxClient(WxClient wxClient) {
        this.wxClient = wxClient;
    }

    public Order get(String id) {
        String url = WxEndpoint.get("shop.order.get");
        String body = String.format("{\"order_id\":\"%s\"}", id);
        logger.debug("shop: get order by id: {}", id);
        String response = wxClient.post(url, body);
        OrderWrapper orderWrapper = JsonMapper.defaultMapper().fromJson(response, OrderWrapper.class);
        return orderWrapper.getOrder();
    }

    public List<Order> query(Integer status, Date start, Date end) {
        String url = WxEndpoint.get("shop.order.query");

        Map<String, Object> map = new HashMap<>();
        if (status != null) map.put("status", status);
        if (start != null) map.put("begintime", start.getTime() / 1000);
        if (end != null) map.put("endtime", start.getTime() / 1000);

        String body = JsonMapper.defaultMapper().toJson(map);

        logger.debug("shop: query orders: {}", body);
        String response = wxClient.post(url, body);
        OrderList orderList = JsonMapper.defaultMapper().fromJson(response, OrderList.class);
        return orderList.getOrders();
    }

    public static class OrderWrapper {

        private Order order;

        public Order getOrder() {
            return order;
        }

        public void setOrder(Order order) {
            this.order = order;
        }
    }
    public static class OrderList {

        private int errcode;
        private String errmsg;

        @JsonProperty("order_list")
        private List<Order> orders;

        public int getErrcode() {
            return errcode;
        }

        public void setErrcode(int errcode) {
            this.errcode = errcode;
        }

        public String getErrmsg() {
            return errmsg;
        }

        public void setErrmsg(String errmsg) {
            this.errmsg = errmsg;
        }

        public List<Order> getOrders() {
            return orders;
        }

        public void setOrders(List<Order> orders) {
            this.orders = orders;
        }
    }
}

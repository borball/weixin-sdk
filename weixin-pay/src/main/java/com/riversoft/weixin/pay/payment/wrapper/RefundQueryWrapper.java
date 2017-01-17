package com.riversoft.weixin.pay.payment.wrapper;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.riversoft.weixin.pay.payment.bean.RefundQuery;

import java.util.HashMap;
import java.util.Map;

/**
 * @borball on 1/13/2017.
 */
public class RefundQueryWrapper extends BaseSettings{

    @JsonUnwrapped
    private RefundQuery refundQuery;

    @JsonAnySetter
    public void set(String name, Object value) {
        others.put(name, value);
    }

    public RefundQuery getRefundQuery() {
        return refundQuery;
    }

    public void setRefundQuery(RefundQuery refundQuery) {
        this.refundQuery = refundQuery;
    }

    protected Map<String,Object> others = new HashMap<>();
    public Map<String, Object> getOthers() {
        return others;
    }
    public void setOthers(Map<String, Object> others) {
        this.others = others;
    }

    public void ready(){
        this.getRefundQuery().setOthers(this.getOthers());
    }

}
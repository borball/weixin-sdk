package com.riversoft.weixin.pay.payment.wrapper;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.riversoft.weixin.pay.base.BaseResponse;

/**
 * @borball on 1/13/2017.
 */
public class OrderCloseResponseWrapper extends BaseSettings {

    @JsonUnwrapped
    private BaseResponse response;

    public BaseResponse getResponse() {
        return response;
    }

    public void setResponse(BaseResponse response) {
        this.response = response;
    }
}

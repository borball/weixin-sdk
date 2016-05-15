package com.riversoft.weixin.pay.base;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @borball on 5/15/2016.
 */
public class BaseResponse {

    @JsonProperty("return_code")
    private String returnCode;

    @JsonProperty("return_msg")
    private String returnMessage;

    @JsonProperty("result_code")
    private String resultCode;

    @JsonProperty("err_code")
    private String errorCode;

    @JsonProperty("err_code_des")
    private String errorCodeDesc;

    public boolean success() {
        return "SUCCESS".equals(returnCode) && "SUCCESS".equals(resultCode);
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorCodeDesc() {
        return errorCodeDesc;
    }

    public void setErrorCodeDesc(String errorCodeDesc) {
        this.errorCodeDesc = errorCodeDesc;
    }

}

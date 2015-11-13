package com.riversoft.weixin.common.exception;

/**
 * Created by exizhai on 9/25/2015.
 */
public class WxRuntimeException extends RuntimeException {

    private int code;
    private String message;
    private WxError wxError;

    public WxRuntimeException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public WxRuntimeException(WxError wxError) {
        super(wxError.getJson());
        this.wxError = wxError;
    }

    public int getCode() {
        if (this.wxError != null) {
            return wxError.getErrorCode();
        } else {
            return code;
        }
    }

    public String message() {
        if (this.wxError != null) {
            return wxError.getErrorMsg();
        } else {
            return this.message;
        }
    }

    public WxError getWxError() {
        return this.wxError;
    }

}

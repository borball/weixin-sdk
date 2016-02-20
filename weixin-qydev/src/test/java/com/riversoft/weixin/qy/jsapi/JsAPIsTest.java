package com.riversoft.weixin.qy.jsapi;

import com.riversoft.weixin.common.jsapi.JsAPISignature;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by exizhai on 1/28/2016.
 */
public class JsAPIsTest {

    @Test
    public void testCreateJsAPISignature(){
        JsAPISignature signature = JsAPIs.defaultJsAPIs().createJsAPISignature("http://test.com/test.jsp?abc=123");
        Assert.assertNotNull(signature);
    }
}

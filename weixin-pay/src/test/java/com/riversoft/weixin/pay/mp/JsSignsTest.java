package com.riversoft.weixin.pay.mp;

import com.riversoft.weixin.pay.mp.bean.JSSignature;
import org.junit.Assert;
import org.junit.Test;

/**
 * @borball on 6/2/2016.
 */
public class JsSignsTest {

    @Test
    public void testCreate(){
        JSSignature jsSignature = JsSigns.defaultJsSigns().createJsSignature("1234567890");
        Assert.assertNotNull(jsSignature);
    }
}

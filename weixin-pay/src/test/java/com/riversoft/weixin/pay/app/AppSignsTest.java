package com.riversoft.weixin.pay.app;

import com.riversoft.weixin.pay.mp.JsSigns;
import com.riversoft.weixin.pay.payment.bean.Signature;
import org.junit.Assert;
import org.junit.Test;

/**
 * @borball on 6/2/2016.
 */
public class AppSignsTest {

    @Test
    public void testCreate(){
        Signature signature = JsSigns.defaultJsSigns().createSignature("1234567890");
        Assert.assertNotNull(signature);
    }
}

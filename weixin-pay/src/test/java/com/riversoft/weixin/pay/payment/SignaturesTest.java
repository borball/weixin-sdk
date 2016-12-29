package com.riversoft.weixin.pay.payment;

import com.riversoft.weixin.pay.payment.bean.Signature;
import org.junit.Assert;
import org.junit.Test;

/**
 * @borball on 6/2/2016.
 */
public class SignaturesTest {

    @Test
    public void testCreateJsSignature(){
        Signature signature = Signatures.defaultSignatures().createJsSignature("1234567890");
        Assert.assertNotNull(signature);
    }


    @Test
    public void testCreateAppSignature(){
        Signature signature = Signatures.defaultSignatures().createAppSignature("1234567890");
        Assert.assertNotNull(signature);
    }
}

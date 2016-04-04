package com.riversoft.weixin.open.oauth2;

import org.junit.Assert;
import org.junit.Test;

/**
 * @borball on 3/25/2016.
 */
public class OpenOAuth2sTest {

    @Test
    public void testAuthenticationUrl(){
        String url = OpenOAuth2s.defaultOpenOAuth2s().authenticationUrl("http://hostname.com");
        Assert.assertNotNull(url);
    }
}

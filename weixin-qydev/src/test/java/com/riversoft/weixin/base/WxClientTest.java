package com.riversoft.weixin.base;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by exizhai on 9/26/2015.
 */
public class WxClientTest {

    private static WxClient wxClient;

    @BeforeClass
    public static void beforeClass() {
        wxClient = new WxClient(Settings.defaultSettings().getDefaultCorpSetting());
    }

    @Test
    public void testGetToken() {
        String token = wxClient.getAccessToken();
        Assert.assertNotNull(token);
        token = wxClient.getAccessToken(true);
        Assert.assertNotNull(token);
    }

}

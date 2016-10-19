package com.riversoft.weixin.mp;

import com.riversoft.weixin.common.AccessToken;
import com.riversoft.weixin.common.WxClient;
import com.riversoft.weixin.mp.base.AppSetting;
import com.riversoft.weixin.mp.base.WxEndpoint;
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
        AppSetting appSetting = AppSetting.defaultSettings();
        String url = WxEndpoint.get("url.token.get");
        wxClient = new WxClient(url, appSetting.getAppId(), appSetting.getSecret());
    }

    @Test
    public void testGetToken() {
        AccessToken token = wxClient.getAccessToken();
        System.out.println(token.getAccessToken() + "," + token.getExpiresTill());
        Assert.assertNotNull(token);
        wxClient.refreshToken();
        token = wxClient.getAccessToken();
        System.out.println(token.getAccessToken() + "," + token.getExpiresTill());
        Assert.assertNotNull(token);
    }

}

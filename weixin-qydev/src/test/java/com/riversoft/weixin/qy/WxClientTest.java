package com.riversoft.weixin.qy;

import com.riversoft.weixin.common.AccessToken;
import com.riversoft.weixin.common.WxClient;
import com.riversoft.weixin.qy.base.CorpSetting;
import com.riversoft.weixin.qy.base.WxEndpoint;
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
        String url = WxEndpoint.get("url.token.get");
        CorpSetting corpSetting = CorpSetting.defaultSettings();
        wxClient = new WxClient(url, corpSetting.getCorpId(), corpSetting.getCorpSecret());
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

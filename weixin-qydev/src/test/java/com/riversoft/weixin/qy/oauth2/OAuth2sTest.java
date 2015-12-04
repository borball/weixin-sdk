package com.riversoft.weixin.qy.oauth2;

import org.junit.Test;

import java.util.Map;

/**
 * Created by exizhai on 12/2/2015.
 */
public class OAuth2sTest {

    @Test
    public void testToOpenId(){
        Map<String, String> response = OAuth2s.defaultOAuth2s().toOpenId(45, "johnny");
        System.out.print("johnny|");
        System.out.print(response.get("appid"));
        System.out.print("|");
        System.out.println(response.get("openid"));
    }
}

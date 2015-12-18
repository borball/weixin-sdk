package com.riversoft.weixin.qy.oauth2;

import org.junit.Test;

import java.util.Map;

/**
 * Created by exizhai on 12/2/2015.
 */
public class QyOAuth2STest {

    @Test
    public void testToOpenId(){
        Map<String, String> response = QyOAuth2s.defaultOAuth2s().toOpenId(45, "borball");
        System.out.print("woden|");
        System.out.print(response.get("appid"));
        System.out.print("|");
        System.out.println(response.get("openid"));
    }
}

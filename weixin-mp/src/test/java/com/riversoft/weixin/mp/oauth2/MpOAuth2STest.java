package com.riversoft.weixin.mp.oauth2;

import com.riversoft.weixin.common.util.JsonMapper;
import com.riversoft.weixin.common.oauth2.OpenUser;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by exizhai on 12/17/2015.
 */
public class MpOAuth2STest {

    @Test
    public void testFromJson(){
        String user = "{\"openid\" : \"OPENID\",\" nickname\" : \"NICKNAME\",\"sex\" : \"1\",\"province\" : \"PROVINCE\",\"city\" : \"CITY\",\"country\" : \"COUNTRY\",\"headimgurl\" : \"http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/46\",\"privilege\" : [\"PRIVILEGE1\", \"PRIVILEGE2\"],\"unionid\" : \"o6_bmasdasdsad6_2sgVt7hMZOPfL\"}";
        OpenUser openUser = JsonMapper.defaultMapper().fromJson(user, OpenUser.class);
        Assert.assertNotNull(openUser);
    }

    @Test
    public void testAuthenticationUrl(){
        String url = MpOAuth2s.defaultOAuth2s().authenticationUrl("http://wxtest.gzriver.com/oS-I8EwVL9X.view", "snsapi_userinfo");
        System.out.println(url);
        Assert.assertNotNull(url);
    }
}

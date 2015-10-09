package com.riversoft.weixin.oauth2;

import com.riversoft.weixin.base.WxClient;
import com.riversoft.weixin.oauth2.bean.UserInfo;
import com.riversoft.weixin.util.JsonMapper;
import com.riversoft.weixin.util.PropertiesLoader;
import com.riversoft.weixin.util.URLEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by exizhai on 10/4/2015.
 */
public class OAuth2s {

    private static Logger logger = LoggerFactory.getLogger(OAuth2s.class);

    private static OAuth2s oAuth2s = null;

    public static OAuth2s defaultOAuth(){
        if(oAuth2s == null) {
            oAuth2s = new OAuth2s();
            oAuth2s.setWxClient(WxClient.defaultWxClient());
        }

        return oAuth2s;
    }

    private WxClient wxClient;

    public void setWxClient(WxClient wxClient) {
        this.wxClient = wxClient;
    }

    public String authenticationUrl(String redirect, String state){
        if(state == null || "".equals(state)) {
            String url = PropertiesLoader.getInstance().getProperty("url.oauth.authentication");
            return String.format(url, wxClient.getCorpSetting().getCorpId(), URLEncoder.encode(redirect), state);
        } else {
            String url = PropertiesLoader.getInstance().getProperty("url.oauth.authentication.state");
            return String.format(url, wxClient.getCorpSetting().getCorpId(), URLEncoder.encode(redirect));
        }
    }

    public UserInfo userInfo(String code) {
        String url = PropertiesLoader.getInstance().getProperty("url.oauth.get");

        //尼玛又不带error code
        String userInfo = wxClient.get(String.format(url, code));
        logger.debug("oauth get user info: {}", userInfo);
        return JsonMapper.nonEmptyMapper().fromJson(userInfo, UserInfo.class);
    }
}

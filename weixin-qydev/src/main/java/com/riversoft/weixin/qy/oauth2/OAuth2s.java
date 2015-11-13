package com.riversoft.weixin.qy.oauth2;

import com.riversoft.weixin.common.WxClient;
import com.riversoft.weixin.qy.QyWxClientFactory;
import com.riversoft.weixin.qy.base.CorpSetting;
import com.riversoft.weixin.qy.base.DefaultSettings;
import com.riversoft.weixin.qy.oauth2.bean.UserInfo;
import com.riversoft.weixin.qy.util.JsonMapper;
import com.riversoft.weixin.qy.base.WxEndpoint;
import com.riversoft.weixin.qy.util.URLEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by exizhai on 10/4/2015.
 */
public class OAuth2s {

    private static Logger logger = LoggerFactory.getLogger(OAuth2s.class);

    private WxClient wxClient;

    public void setWxClient(WxClient wxClient) {
        this.wxClient = wxClient;
    }

    public static OAuth2s defaultOAuth2s() {
        return with(DefaultSettings.defaultSettings().getCorpSetting());
    }

    public static OAuth2s with(CorpSetting corpSetting) {
        OAuth2s oAuth2s = new OAuth2s();
        oAuth2s.setWxClient(QyWxClientFactory.getInstance().with(corpSetting));
        return oAuth2s;
    }

    public String authenticationUrl(String redirect, String state){
        if(state == null || "".equals(state)) {
            String url = WxEndpoint.get("url.oauth.authentication");
            return String.format(url, wxClient.getClientId(), URLEncoder.encode(redirect), state);
        } else {
            String url = WxEndpoint.get("url.oauth.authentication.state");
            return String.format(url, wxClient.getClientId(), URLEncoder.encode(redirect));
        }
    }

    public UserInfo userInfo(String code) {
        String url = WxEndpoint.get("url.oauth.get");

        //尼玛又不带error code
        String userInfo = wxClient.get(String.format(url, code));
        logger.debug("oauth get user info: {}", userInfo);
        return JsonMapper.nonEmptyMapper().fromJson(userInfo, UserInfo.class);
    }
}

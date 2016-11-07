package com.riversoft.weixin.app.user;

import com.riversoft.weixin.app.AppWxClientFactory;
import com.riversoft.weixin.app.base.AppSetting;
import com.riversoft.weixin.app.base.WxEndpoint;
import com.riversoft.weixin.common.WxClient;
import com.riversoft.weixin.common.util.JsonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 用户操作相关
 * @borball on 11/7/2016.
 */
public class Users {

    private static Logger logger = LoggerFactory.getLogger(Users.class);

    private WxClient wxClient;

    public static Users defaultUsers() {
        return with(AppSetting.defaultSettings());
    }

    public static Users with(AppSetting appSetting) {
        Users cards = new Users();
        cards.setWxClient(AppWxClientFactory.getInstance().with(appSetting));
        return cards;
    }

    public void setWxClient(WxClient wxClient) {
        this.wxClient = wxClient;
    }

    public SessionKey code2Session(String code) {
        String url = WxEndpoint.get("url.user.code2session");
        String sessionKey = wxClient.get(String.format(url, wxClient.getClientId(), wxClient.getClientSecret(), code));
        logger.debug("code to session key: {}", sessionKey);
        return JsonMapper.nonEmptyMapper().fromJson(sessionKey, SessionKey.class);
    }

}

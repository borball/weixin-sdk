package com.riversoft.weixin.open.oauth2;

import com.riversoft.weixin.common.WxClient;
import com.riversoft.weixin.common.oauth2.AccessToken;
import com.riversoft.weixin.common.oauth2.OpenUser;
import com.riversoft.weixin.common.util.JsonMapper;
import com.riversoft.weixin.common.util.URLEncoder;
import com.riversoft.weixin.open.OpenWxClientFactory;
import com.riversoft.weixin.open.base.AppSetting;
import com.riversoft.weixin.open.base.WxEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 公众号OAuth API
 * Created by exizhai on 12/17/2015.
 */
public class OpenOAuth2s {

    private static Logger logger = LoggerFactory.getLogger(OpenOAuth2s.class);

    private WxClient wxClient;

    public static OpenOAuth2s defaultOpenOAuth2s() {
        return with(AppSetting.defaultSettings());
    }

    public static OpenOAuth2s with(AppSetting appSetting) {
        OpenOAuth2s mpOAuth2S = new OpenOAuth2s();
        mpOAuth2S.setWxClient(OpenWxClientFactory.getInstance().with(appSetting));
        return mpOAuth2S;
    }

    public void setWxClient(WxClient wxClient) {
        this.wxClient = wxClient;
    }

    /**
     * 生成授权链接, 默认scope: snsapi_base
     * @param redirect 授权后重定向的回调链接地址，请使用urlencode对链接进行处理
     * @return
     */
    public String authenticationUrl(String redirect) {
        return authenticationUrl(redirect, "snsapi_base");
    }

    /**
     * 生成授权链接
     * @param redirect 授权后重定向的回调链接地址，请使用urlencode对链接进行处理
     * @param scope 应用授权作用域，snsapi_base or snsapi_userinfo
     * @return
     */
    public String authenticationUrl(String redirect, String scope) {
        return authenticationUrl(redirect, scope, null);
    }

    /**
     * 生成授权链接
     * @param redirect 授权后重定向的回调链接地址，请使用urlencode对链接进行处理
     * @param scope 应用授权作用域，snsapi_base or snsapi_userinfo
     * @param state 重定向后会带上state参数，开发者可以填写a-zA-Z0-9的参数值，最多128字节
     * @return
     */
    public String authenticationUrl(String redirect, String scope, String state) {
        if (state == null || "".equals(state)) {
            String url = WxEndpoint.get("url.oauth.authentication");
            return String.format(url, wxClient.getClientId(), URLEncoder.encode(redirect), scope);
        } else {
            String url = WxEndpoint.get("url.oauth.authentication.state");
            return String.format(url, wxClient.getClientId(), URLEncoder.encode(redirect), scope, state);
        }
    }

    /**
     * 获取access token
     * @param code
     * @return
     */
    public AccessToken getAccessToken(String code) {
        String url = WxEndpoint.get("url.oauth.accesstoken.get");
        String formatUrl = String.format(url, wxClient.getClientId(), wxClient.getClientSecret(), code);
        logger.debug("get access token: {}", formatUrl);
        String response = wxClient.get(formatUrl, false);
        return JsonMapper.defaultMapper().fromJson(response, AccessToken.class);
    }

    /**
     * 刷新access token
     * @param refreshToken
     * @return
     */
    public AccessToken refreshToken(String refreshToken) {
        String url = WxEndpoint.get("url.oauth.accesstoken.refresh");
        String formatUrl = String.format(url, wxClient.getClientId(), refreshToken);
        logger.debug("refresh access token: {}", formatUrl);
        String response = wxClient.get(formatUrl, false);
        return JsonMapper.defaultMapper().fromJson(response, AccessToken.class);
    }

    /**
     * 获取用户信息
     * @param accessToken
     * @param openId
     * @return
     */
    public OpenUser userInfo(String accessToken, String openId) {
        return userInfo(accessToken, openId, "zh_CN");
    }

    /**
     * 获取用户信息
     * @param accessToken
     * @param openId
     * @param lang
     * @return
     */
    public OpenUser userInfo(String accessToken, String openId, String lang) {
        String url = WxEndpoint.get("url.oauth.userinfo.get");
        String formatUrl = String.format(url, accessToken, openId, lang);

        logger.debug("get user info {}", formatUrl);
        String response = wxClient.get(formatUrl, false);
        return JsonMapper.defaultMapper().fromJson(response, OpenUser.class);
    }
}

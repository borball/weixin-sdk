package com.riversoft.weixin.mp.oauth2;

import com.riversoft.weixin.common.WxClient;
import com.riversoft.weixin.common.util.JsonMapper;
import com.riversoft.weixin.common.util.URLEncoder;
import com.riversoft.weixin.mp.MpWxClientFactory;
import com.riversoft.weixin.mp.base.AppSetting;
import com.riversoft.weixin.mp.base.WxEndpoint;
import com.riversoft.weixin.common.oauth2.AccessToken;
import com.riversoft.weixin.common.oauth2.OpenUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 公众号OAuth API
 * Created by exizhai on 12/17/2015.
 */
public class MpOAuth2s {

    private static Logger logger = LoggerFactory.getLogger(MpOAuth2s.class);

    private WxClient wxClient;

    public static MpOAuth2s defaultOAuth2s() {
        return with(AppSetting.defaultSettings());
    }

    public static MpOAuth2s with(AppSetting appSetting) {
        MpOAuth2s mpOAuth2S = new MpOAuth2s();
        mpOAuth2S.setWxClient(MpWxClientFactory.getInstance().with(appSetting));
        return mpOAuth2S;
    }

    public void setWxClient(WxClient wxClient) {
        this.wxClient = wxClient;
    }

    /**
     * 生成授权链接
     * @param redirect 授权后重定向的回调链接地址，请使用urlencode对链接进行处理
     * @param scope  应用授权作用域，
     *               snsapi_base （不弹出授权页面，直接跳转，只能获取用户openid），
     *               snsapi_userinfo （弹出授权页面，可通过openid拿到昵称、性别、所在地。并且，即使在未关注的情况下，只要用户授权，也能获取其信息）
     * @return
     */
    public String authenticationUrl(String redirect, String scope) {
        return authenticationUrl(redirect, scope, null);
    }

    /**
     * 生成授权链接
     * @param redirect 授权后重定向的回调链接地址，请使用urlencode对链接进行处理
     * @param scope  应用授权作用域，
     *               snsapi_base （不弹出授权页面，直接跳转，只能获取用户openid），
     *               snsapi_userinfo （弹出授权页面，可通过openid拿到昵称、性别、所在地。并且，即使在未关注的情况下，只要用户授权，也能获取其信息）
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

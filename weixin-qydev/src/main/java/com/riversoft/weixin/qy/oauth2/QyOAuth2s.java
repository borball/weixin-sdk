package com.riversoft.weixin.qy.oauth2;

import com.riversoft.weixin.common.WxClient;
import com.riversoft.weixin.common.exception.WxRuntimeException;
import com.riversoft.weixin.common.util.JsonMapper;
import com.riversoft.weixin.common.util.URLEncoder;
import com.riversoft.weixin.qy.QyWxClientFactory;
import com.riversoft.weixin.qy.base.CorpSetting;
import com.riversoft.weixin.qy.base.WxEndpoint;
import com.riversoft.weixin.qy.oauth2.bean.QyUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by exizhai on 10/4/2015.
 */
public class QyOAuth2s {

    private static Logger logger = LoggerFactory.getLogger(QyOAuth2s.class);

    private WxClient wxClient;

    public static QyOAuth2s defaultOAuth2s() {
        return with(CorpSetting.defaultSettings());
    }

    public static QyOAuth2s with(CorpSetting corpSetting) {
        QyOAuth2s qyOAuth2S = new QyOAuth2s();
        qyOAuth2S.setWxClient(QyWxClientFactory.getInstance().with(corpSetting));
        return qyOAuth2S;
    }

    public void setWxClient(WxClient wxClient) {
        this.wxClient = wxClient;
    }

    public String authenticationUrl(String redirect, String state) {
        if (state == null || "".equals(state)) {
            String url = WxEndpoint.get("url.oauth.authentication");
            return String.format(url, wxClient.getClientId(), URLEncoder.encode(redirect));
        } else {
            String url = WxEndpoint.get("url.oauth.authentication.state");
            return String.format(url, wxClient.getClientId(), URLEncoder.encode(redirect), state);
        }
    }

    public QyUser userInfo(String code) {
        String url = WxEndpoint.get("url.oauth.userinfo.get");

        //尼玛又不带error code
        String userInfo = wxClient.get(String.format(url, code));
        logger.debug("oauth get user info: {}", userInfo);
        return JsonMapper.nonEmptyMapper().fromJson(userInfo, QyUser.class);
    }

    public Map<String, String> toOpenId(int agentId, String userId) {
        String url = WxEndpoint.get("url.oauth.uid2openid");

        Map<String, Object> request = new HashMap<>();
        request.put("userid", userId);
        request.put("agentid", agentId);

        logger.debug("agent[{}], userId[{}] to openId", agentId, userId);
        String response = wxClient.post(url, JsonMapper.defaultMapper().toJson(request));

        Map<String, Object> result = JsonMapper.defaultMapper().json2Map(response);
        if (result.containsKey("errcode") && (Integer) result.get("errcode") == 0) {
            Map<String, String> map = new HashMap<>();
            map.put("openid", result.get("openid").toString());
            map.put("appid", result.get("appid").toString());
            return map;
        } else {
            throw new WxRuntimeException(999, "user id to open id failed.");
        }
    }

    public String toOpenId(String userId) {
        String url = WxEndpoint.get("url.oauth.uid2openid");

        Map<String, Object> request = new HashMap<>();
        request.put("userid", userId);

        logger.debug("userId[{}] to openId", userId);
        String response = wxClient.post(url, JsonMapper.defaultMapper().toJson(request));

        Map<String, Object> result = JsonMapper.defaultMapper().json2Map(response);
        if (result.containsKey("errcode") && (Integer) result.get("errcode") == 0) {
            return result.get("openid").toString();
        } else {
            throw new WxRuntimeException(999, "user id to open id failed.");
        }
    }

    public String toUserId(String openId) {
        String url = WxEndpoint.get("url.oauth.openid2uid");

        Map<String, Object> request = new HashMap<>();
        request.put("openid", openId);

        logger.debug("openId[{}] to userid ", openId);
        String response = wxClient.post(url, JsonMapper.defaultMapper().toJson(request));

        Map<String, Object> result = JsonMapper.defaultMapper().json2Map(response);
        if (result.containsKey("errcode") && (Integer) result.get("errcode") == 0) {
            return result.get("userid").toString();
        } else {
            throw new WxRuntimeException(999, "open id to user id failed.");
        }
    }
}

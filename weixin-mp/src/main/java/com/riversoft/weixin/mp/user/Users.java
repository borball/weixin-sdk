package com.riversoft.weixin.mp.user;

import com.riversoft.weixin.common.WxClient;
import com.riversoft.weixin.common.util.JsonMapper;
import com.riversoft.weixin.mp.MpWxClientFactory;
import com.riversoft.weixin.mp.base.AppSetting;
import com.riversoft.weixin.mp.base.WxEndpoint;
import com.riversoft.weixin.mp.user.bean.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by exizhai on 11/4/2015.
 */
public class Users {

    private static Logger logger = LoggerFactory.getLogger(Users.class);

    private WxClient wxClient;

    public static Users defaultUsers() {
        return with(AppSetting.defaultSettings());
    }

    public static Users with(AppSetting appSetting) {
        Users users = new Users();
        users.setWxClient(MpWxClientFactory.getInstance().with(appSetting));
        return users;
    }

    public void setWxClient(WxClient wxClient) {
        this.wxClient = wxClient;
    }

    public User get(String openId) {
        return get(openId, "zh_CN");
    }

    public User get(String openId, String lang) {
        String url = WxEndpoint.get("url.user.get");
        String user = wxClient.get(String.format(url, openId, lang));
        logger.debug("get user: {}", user);
        return JsonMapper.nonEmptyMapper().fromJson(user, User.class);
    }

    public String list() {
        String url = WxEndpoint.get("url.user.list");
        logger.debug("list users: {}");
        return wxClient.get(url);
    }

    public void remark(String openId, String remark) {
        String url = WxEndpoint.get("url.user.remark");
        String json = String.format("{\"openid\":\"%s\",\"remark\":\"%s\"}", openId, remark);
        logger.debug("remark user: {}", json);
        wxClient.post(url, json);
    }

}

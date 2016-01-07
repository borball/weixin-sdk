package com.riversoft.weixin.mp.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.riversoft.weixin.common.WxClient;
import com.riversoft.weixin.common.exception.WxRuntimeException;
import com.riversoft.weixin.common.util.JsonMapper;
import com.riversoft.weixin.mp.MpWxClientFactory;
import com.riversoft.weixin.mp.base.AppSetting;
import com.riversoft.weixin.mp.base.WxEndpoint;
import com.riversoft.weixin.mp.user.bean.User;
import com.riversoft.weixin.mp.user.bean.UserPagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户管理
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
        try {
            String user = wxClient.get(String.format(url, openId, lang));
            logger.debug("get user: {}", user);
            return JsonMapper.nonEmptyMapper().fromJson(user, User.class);
        } catch (WxRuntimeException e) {
            if (e.getCode() == 40003) {
                logger.warn("open id: {} not found.", openId);
                return null;
            }
            throw e;
        }
    }

    public UserPagination list() {
        return list(null);
    }

    /**
     * 批量获取用户信息，默认lang为zh_CN, 一次最多100个
     * @param openIds
     * @return
     */
    public List<User> batchGet(String... openIds) {
        if(openIds.length > 100) {
            throw new WxRuntimeException(999, "批量获取用户基本信息。最多支持一次拉取100条");
        }
        List<Map<String, String>> list = new ArrayList<>();
        for (String openId: openIds) {
            Map<String, String> map = new HashMap<>();
            map.put("openid", openId);
            map.put("lang", "zh_CN");
            list.add(map);
        }
        return batchGet(list);
    }

    /**
     * 批量获取用户信息，一次最多100个
     * @param openIds
     * @return
     */
    public List<User> batchGet(List<Map<String, String>> openIds) {
        if(openIds.size() > 100) {
            throw new WxRuntimeException(999, "批量获取用户基本信息。最多支持一次拉取100条");
        }

        String url = WxEndpoint.get("url.user.batchget");
        String body = "{\"user_list\":%s}";
        String json = JsonMapper.defaultMapper().toJson(openIds);

        logger.debug("batch get users.");
        String response = wxClient.post(url, String.format(body, json));
        UsersWrapper usersWrapper = JsonMapper.defaultMapper().fromJson(response, UsersWrapper.class);
        return usersWrapper.getList();
    }

    public UserPagination list(String nextOpenId) {
        String url = WxEndpoint.get("url.user.list");
        if (nextOpenId == null || "".equals(nextOpenId)) {
        } else {
            url = url + "?next_openid=" + nextOpenId;
        }

        String response = wxClient.get(url);
        logger.debug("list users: {}", response);

        return JsonMapper.defaultMapper().fromJson(response, UserPagination.class);
    }

    /**
     * 备注
     *
     * @param openId
     * @param remark 长度小于30
     */
    public void remark(String openId, String remark) {
        String url = WxEndpoint.get("url.user.remark");
        String json = String.format("{\"openid\":\"%s\",\"remark\":\"%s\"}", openId, remark);
        logger.debug("remark user: {}", json);
        wxClient.post(url, json);
    }

    static class UsersWrapper {

        @JsonProperty("user_info_list")
        private List<User> list;

        public List<User> getList() {
            return list;
        }

        public void setList(List<User> list) {
            this.list = list;
        }
    }
}

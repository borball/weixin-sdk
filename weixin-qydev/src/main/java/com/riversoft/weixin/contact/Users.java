package com.riversoft.weixin.contact;

import com.riversoft.weixin.base.WxClient;
import com.riversoft.weixin.contact.bean.user.*;
import com.riversoft.weixin.exception.WxRuntimeException;
import com.riversoft.weixin.util.JsonMapper;
import com.riversoft.weixin.base.WxEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by exizhai on 10/3/2015.
 */
public class Users {

    private static Logger logger = LoggerFactory.getLogger(Users.class);

    private static Users users = null;
    private WxClient wxClient;

    public static Users defaultUsers() {
        if (users == null) {
            users = new Users();
            users.setWxClient(WxClient.defaultWxClient());
        }

        return users;
    }

    public void setWxClient(WxClient wxClient) {
        this.wxClient = wxClient;
    }

    public ReadUser get(String uid) {
        String url = WxEndpoint.get("url.user.get");
        String user = wxClient.get(String.format(url, uid));
        logger.debug("get user: {}", user);
        return JsonMapper.nonEmptyMapper().fromJson(user, ReadUser.class);
    }

    public void create(CreateUser user) {
        String url = WxEndpoint.get("url.user.create");
        String json = JsonMapper.nonEmptyMapper().toJson(user);
        logger.debug("create user: {}", json);
        wxClient.post(url, json);
    }

    public void update(UpdateUser user) {
        String url = WxEndpoint.get("url.user.update");
        String json = JsonMapper.nonEmptyMapper().toJson(user);
        logger.debug("update user: {}", user);
        wxClient.post(url, json);
    }

    public void delete(String uid) {
        String url = WxEndpoint.get("url.user.delete");
        logger.debug("delete user: {}", uid);
        wxClient.get(String.format(url, uid));
    }

    public List<SimpleUser> simpleList(int department, boolean fetchChild, Set<UserStatus> statusSet) {
        int status = 0;
        if (statusSet != null && statusSet.size() > 0) {
            for (UserStatus userStatus : statusSet) {
                status = status + userStatus.getStatus();
            }
        }

        String url = WxEndpoint.get("url.user.simple.list");
        String user = wxClient.get(String.format(url, department, fetchChild ? "1" : "0", status));
        logger.debug("list user: {}", user);
        SimpleUserList simpleUserList = JsonMapper.nonEmptyMapper().fromJson(user, SimpleUserList.class);
        return simpleUserList.getUsers();
    }

    public List<SimpleUser> simpleList(int department) {
        return simpleList(department, true, null);
    }

    public List<ReadUser> list(int department, boolean fetchChild, Set<UserStatus> statusSet) {
        int status = 0;
        if (statusSet != null && statusSet.size() > 0) {
            for (UserStatus userStatus : statusSet) {
                status = status + userStatus.getStatus();
            }
        }

        String url = WxEndpoint.get("url.user.list");
        String user = wxClient.get(String.format(url, department, fetchChild ? "1" : "0", status));
        logger.debug("list user: {}", user);
        ReadUserList readUserList = JsonMapper.nonEmptyMapper().fromJson(user, ReadUserList.class);
        return readUserList.getUsers();
    }

    public List<ReadUser> list() {
        return list(1, true, null);
    }

    public List<ReadUser> list(int department) {
        return list(department, true, null);
    }

    public void delete(List<String> users) {
        String url = WxEndpoint.get("url.user.batch.delete");
        String json = String.format("{\"useridlist\":%s}", JsonMapper.defaultMapper().toJson(users));
        logger.debug("delete users: {}", json);
        wxClient.post(url, json);
    }

    public Invitation invite(String uid) {
        String url = WxEndpoint.get("url.user.invite");
        String json = "{\"userid\":\"%s\"}";
        logger.debug("invite user: {}", String.format(json, uid));

        try {
            String response = wxClient.post(url, String.format(json, uid));
            Map<String, Object> result = JsonMapper.defaultMapper().json2Map(response);
            if (result.containsKey("type")) {
                return Invitation.format(Integer.valueOf(result.get("type").toString()));
            } else {
                return Invitation.FAILED;
            }
        } catch (Exception e) {
            if (e instanceof WxRuntimeException) {
                WxRuntimeException wxRuntimeException = (WxRuntimeException) e;
                if (60119 == wxRuntimeException.getCode()) {
                    return Invitation.ALREADY_FOLLOWED;
                }
            }
            return Invitation.FAILED;
        }

    }

}

package com.riversoft.weixin.mp.user;

import com.google.common.base.Joiner;
import com.riversoft.weixin.common.WxClient;
import com.riversoft.weixin.common.util.JsonMapper;
import com.riversoft.weixin.mp.base.AppSetting;
import com.riversoft.weixin.mp.MpWxClientFactory;
import com.riversoft.weixin.mp.base.WxEndpoint;
import com.riversoft.weixin.mp.user.bean.Group;
import com.riversoft.weixin.mp.user.bean.GroupList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * Created by exizhai on 11/4/2015.
 */
public class Groups {

    private static Logger logger = LoggerFactory.getLogger(Groups.class);

    private WxClient wxClient;

    public void setWxClient(WxClient wxClient) {
        this.wxClient = wxClient;
    }

    public static Groups defaultGroups() {
        return with(AppSetting.defaultSettings());
    }

    public static Groups with(AppSetting appSetting) {
        Groups groups = new Groups();
        groups.setWxClient(MpWxClientFactory.getInstance().with(appSetting));
        return groups;
    }

    public Group create(String name){
        String url = WxEndpoint.get("mp.url.group.create");
        String json = String.format("{\"group\":{\"name\":\"%s\"}}", name);
        logger.debug("create group: {}", json);
        String response = wxClient.post(url, json);

        return JsonMapper.defaultUnwrapRootMapper().fromJson(response, Group.class);
    }

    public List<Group> list(){
        String url = WxEndpoint.get("mp.url.group.list");
        String response = wxClient.get(url);
        logger.debug("list groups: {}", response);
        GroupList groupList = JsonMapper.defaultMapper().fromJson(response, GroupList.class);
        return groupList.getGroups();
    }

    public void update(int id, String name) {
        String url = WxEndpoint.get("mp.url.group.update");
        String json = String.format("{\"group\":{\"id\":%s,\"name\":\"%s\"}}", id, name);
        logger.debug("update group: {}", json);
        wxClient.post(url, json);
    }

    public void delete(int id) {
        String url = WxEndpoint.get("mp.url.group.delete");
        String json = String.format("{\"group\":{\"id\":%s}}", id);
        logger.debug("delete group: {}", json);
        wxClient.post(url, json);
    }

    /**
     * 2B微信，只给个id有鸟用
     * @param openId
     * @return
     */
    public int getUserGroup(String openId) {
        String url = WxEndpoint.get("mp.url.group.user.get");
        String json = String.format("{\"openid\":\"%s\"}", openId);
        logger.debug("get user group: {}", json);
        String response = wxClient.post(url, json);

        Map<String, Object> map = JsonMapper.nonEmptyMapper().json2Map(response);
        if(map.containsKey("groupid")) {
            Integer.valueOf(map.get("groupid").toString());
        }
        return -1;
    }

    public void move(String openId, int group) {
        String url = WxEndpoint.get("mp.url.group.user.move");
        String json = String.format("{\"openid\":\"%s\",\"to_groupid\":%s}", openId, group);
        logger.debug("move user group: {}", json);
        wxClient.post(url, json);
    }

    public void move(List<String> openIds, int group) {
        String url = WxEndpoint.get("mp.url.group.user.moves");
        String ids = Joiner.on(",").join(openIds);
        String json = String.format("{\"openid_list\":[%s],\"to_groupid\":%s}", ids, group);
        logger.debug("move users group: {}", json);
        wxClient.post(url, json);
    }
}

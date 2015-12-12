package com.riversoft.weixin.mp.user;

import com.riversoft.weixin.common.WxClient;
import com.riversoft.weixin.common.util.JsonMapper;
import com.riversoft.weixin.mp.MpWxClientFactory;
import com.riversoft.weixin.mp.base.AppSetting;
import com.riversoft.weixin.mp.base.WxEndpoint;
import com.riversoft.weixin.mp.user.bean.Group;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by exizhai on 11/4/2015.
 */
public class Groups {

    private static Logger logger = LoggerFactory.getLogger(Groups.class);

    private WxClient wxClient;

    public static Groups defaultGroups() {
        return with(AppSetting.defaultSettings());
    }

    public static Groups with(AppSetting appSetting) {
        Groups groups = new Groups();
        groups.setWxClient(MpWxClientFactory.getInstance().with(appSetting));
        return groups;
    }

    public void setWxClient(WxClient wxClient) {
        this.wxClient = wxClient;
    }

    /**
     * 创建分组
     *
     * @param name 分组名
     * @return
     */
    public Group create(String name) {
        String url = WxEndpoint.get("url.group.create");
        String json = String.format("{\"group\":{\"name\":\"%s\"}}", name);
        logger.debug("create group: {}", json);
        String response = wxClient.post(url, json);

        return JsonMapper.defaultUnwrapRootMapper().fromJson(response, Group.class);
    }

    /**
     * 查询所有分组
     *
     * @return
     */
    public List<Group> list() {
        String url = WxEndpoint.get("url.group.list");
        String response = wxClient.get(url);
        logger.debug("list groups: {}", response);
        GroupList groupList = JsonMapper.defaultMapper().fromJson(response, GroupList.class);
        return groupList.getGroups();
    }

    /**
     * 修改分组
     *
     * @param id
     * @param name
     */
    public void update(int id, String name) {
        String url = WxEndpoint.get("url.group.update");
        String json = String.format("{\"group\":{\"id\":%s,\"name\":\"%s\"}}", id, name);
        logger.debug("update group: {}", json);
        wxClient.post(url, json);
    }

    /**
     * 删除分组
     *
     * @param id
     */
    public void delete(int id) {
        String url = WxEndpoint.get("url.group.delete");
        String json = String.format("{\"group\":{\"id\":%s}}", id);
        logger.debug("delete group: {}", json);
        wxClient.post(url, json);
    }

    /**
     * 2B微信，只给个id有鸟用
     *
     * @param openId
     * @return
     */
    public int getUserGroup(String openId) {
        String url = WxEndpoint.get("url.group.user.get");
        String json = String.format("{\"openid\":\"%s\"}", openId);
        logger.debug("get user group: {}", json);
        String response = wxClient.post(url, json);

        Map<String, Object> map = JsonMapper.nonEmptyMapper().json2Map(response);
        if (map.containsKey("groupid")) {
            return (Integer) map.get("groupid");
        }
        return -1;
    }

    /**
     * 用户分组迁移
     *
     * @param openId
     * @param group
     */
    public void move(String openId, int group) {
        String url = WxEndpoint.get("url.group.user.move");
        String json = String.format("{\"openid\":\"%s\",\"to_groupid\":%s}", openId, group);
        logger.debug("move user group: {}", json);
        wxClient.post(url, json);
    }

    /**
     * 批量用户分组迁移
     *
     * @param openIds
     * @param group
     */
    public void move(List<String> openIds, int group) {
        String url = WxEndpoint.get("url.group.user.moves");
        Map<String, Object> request = new HashMap<>();
        request.put("openid_list", openIds);
        request.put("to_groupid", group);

        String json = JsonMapper.defaultMapper().toJson(request);
        logger.debug("move users group: {}", json);
        wxClient.post(url, json);
    }

    public static class GroupList {

        private List<Group> groups;

        public List<Group> getGroups() {
            return groups;
        }

        public void setGroups(List<Group> groups) {
            this.groups = groups;
        }
    }
}

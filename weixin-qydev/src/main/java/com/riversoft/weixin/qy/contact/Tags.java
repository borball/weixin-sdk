package com.riversoft.weixin.qy.contact;

import com.riversoft.weixin.common.WxClient;
import com.riversoft.weixin.common.util.JsonMapper;
import com.riversoft.weixin.qy.QyWxClientFactory;
import com.riversoft.weixin.qy.base.CorpSetting;
import com.riversoft.weixin.qy.base.WxEndpoint;
import com.riversoft.weixin.qy.contact.tag.Tag;
import com.riversoft.weixin.qy.contact.tag.TagList;
import com.riversoft.weixin.qy.contact.tag.TagUsers;
import com.riversoft.weixin.qy.contact.tag.TagUsersResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by exizhai on 10/4/2015.
 */
public class Tags {

    private static Logger logger = LoggerFactory.getLogger(Users.class);

    private WxClient wxClient;

    public static Tags defaultTags() {
        return with(CorpSetting.defaultSettings());
    }

    public static Tags with(CorpSetting corpSetting) {
        Tags tags = new Tags();
        tags.setWxClient(QyWxClientFactory.getInstance().with(corpSetting));
        return tags;
    }

    public void setWxClient(WxClient wxClient) {
        this.wxClient = wxClient;
    }

    public void create(Tag tag) {
        String url = WxEndpoint.get("url.tag.create");
        String json = JsonMapper.nonEmptyMapper().toJson(tag);
        logger.debug("create tag: {}", json);
        wxClient.post(url, json);
    }

    public void update(Tag tag) {
        String url = WxEndpoint.get("url.tag.update");
        String json = JsonMapper.nonEmptyMapper().toJson(tag);
        logger.debug("update tag: {}", tag);
        wxClient.post(url, json);
    }

    public void delete(int id) {
        String url = WxEndpoint.get("url.tag.delete");
        logger.debug("delete tag: {}", id);
        wxClient.get(String.format(url, id));
    }

    public List<Tag> list() {
        String url = WxEndpoint.get("url.tag.list");

        String response = wxClient.get(url);
        TagList tagList = JsonMapper.defaultMapper().fromJson(response, TagList.class);
        return tagList.getTags();
    }

    public TagUsersResult addUsers(int tagId, List<String> users, List<Integer> departments) {
        String url = WxEndpoint.get("url.tag.user.add");
        TagUsers tagUsers = new TagUsers();
        tagUsers.setTagId(tagId);
        tagUsers.setUsers(users);
        tagUsers.setDepartments(departments);
        String json = JsonMapper.nonEmptyMapper().toJson(tagUsers);
        logger.debug("add users to tag: {}", json);
        String response = wxClient.post(url, json);
        return JsonMapper.nonEmptyMapper().fromJson(response, TagUsersResult.class);
    }

    public TagUsersResult deleteUsers(int tagId, List<String> users, List<Integer> departments) {
        String url = WxEndpoint.get("url.tag.user.delete");
        TagUsers tagUsers = new TagUsers();
        tagUsers.setTagId(tagId);
        tagUsers.setUsers(users);
        tagUsers.setDepartments(departments);
        String json = JsonMapper.nonEmptyMapper().toJson(tagUsers);
        logger.debug("delete users from tag: {}", json);
        String response = wxClient.post(url, json);
        return JsonMapper.nonEmptyMapper().fromJson(response, TagUsersResult.class);
    }

    public TagUsers getUsers(int id) {
        String url = WxEndpoint.get("url.tag.user.get");
        logger.debug("get tag user: {}", id);
        String response = wxClient.get(String.format(url, id));
        TagUsers tagUsers = JsonMapper.defaultMapper().fromJson(response, TagUsers.class);
        tagUsers.setTagId(id);
        return tagUsers;
    }
}

package com.riversoft.weixin.contact;

import com.riversoft.weixin.base.WxClient;
import com.riversoft.weixin.contact.bean.tag.Tag;
import com.riversoft.weixin.contact.bean.tag.TagList;
import com.riversoft.weixin.contact.bean.tag.TagUsersResult;
import com.riversoft.weixin.contact.bean.tag.TagUsers;
import com.riversoft.weixin.util.JsonMapper;
import com.riversoft.weixin.base.WxEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by exizhai on 10/4/2015.
 */
public class Tags {

    private static Logger logger = LoggerFactory.getLogger(Users.class);

    private static Tags tags = null;
    private WxClient wxClient;

    public static Tags defaultTags() {
        if (tags == null) {
            tags = new Tags();
            tags.setWxClient(WxClient.defaultWxClient());
        }

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

    public List<Tag> list(){
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
}

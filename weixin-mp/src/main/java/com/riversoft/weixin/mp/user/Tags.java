package com.riversoft.weixin.mp.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.riversoft.weixin.common.WxClient;
import com.riversoft.weixin.common.util.JsonMapper;
import com.riversoft.weixin.mp.MpWxClientFactory;
import com.riversoft.weixin.mp.base.AppSetting;
import com.riversoft.weixin.mp.base.WxEndpoint;
import com.riversoft.weixin.mp.user.bean.Tag;
import com.riversoft.weixin.mp.user.bean.UserPagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @borball on 5/8/2016.
 */
public class Tags {

    private static Logger logger = LoggerFactory.getLogger(Tags.class);

    private WxClient wxClient;

    public static Tags defaultTags() {
        return with(AppSetting.defaultSettings());
    }

    public static Tags with(AppSetting appSetting) {
        Tags tags = new Tags();
        tags.setWxClient(MpWxClientFactory.getInstance().with(appSetting));
        return tags;
    }

    public void setWxClient(WxClient wxClient) {
        this.wxClient = wxClient;
    }

    /**
     * 增加标签
     * @param name 标签名字
     * @return
     */
    public Tag create(String name) {
        String url = WxEndpoint.get("url.tag.create");
        TagWrapper tag = new TagWrapper(name);
        String json = JsonMapper.nonEmptyMapper().toJson(tag);
        logger.debug("create tag: {}", json);
        String response = wxClient.post(url, json);
        TagWrapper wrapper = JsonMapper.defaultMapper().fromJson(response, TagWrapper.class);
        return wrapper.getTag();
    }

    /**
     * 修改标签
     * @param tag 标签
     */
    public void update(Tag tag) {
        String url = WxEndpoint.get("url.tag.update");
        String json = JsonMapper.nonEmptyMapper().toJson(new TagWrapper(tag));
        logger.debug("update tag: {}", tag);
        wxClient.post(url, json);
    }

    /**
     * 删除标签
     * @param id 标签ID
     */
    public void delete(int id) {
        String url = WxEndpoint.get("url.tag.delete");
        String json = JsonMapper.nonEmptyMapper().toJson(new TagWrapper(id));
        logger.debug("delete tag: {}", json);
        wxClient.post(url, json);
    }

    /**
     * 获取所有的标签
     * @return
     */
    public List<Tag> list() {
        String url = WxEndpoint.get("url.tag.list");

        String response = wxClient.get(url);
        TagList tagList = JsonMapper.defaultMapper().fromJson(response, TagList.class);
        return tagList.getTags();
    }

    /**
     * 获取标签下的用户列表
     * @param tagId 标签ID
     * @return
     */
    public UserPagination listUsers(int tagId) {
        return listUsers(tagId, null);
    }

    /**
     * 获取标签下的用户列表
     * @param tagId 标签ID
     * @param nextOpenId 下一个openId
     * @return
     */
    public UserPagination listUsers(int tagId, String nextOpenId) {
        Map<String, Object> map = new HashMap<>();
        map.put("tagid", tagId);
        String url = WxEndpoint.get("url.tag.user.list");
        if (nextOpenId == null || "".equals(nextOpenId)) {
        } else {
            map.put("next_openid", nextOpenId);
        }

        String response = wxClient.post(url, JsonMapper.defaultMapper().toJson(map));
        logger.debug("list tag users: {}", response);

        return JsonMapper.defaultMapper().fromJson(response, UserPagination.class);
    }

    /**
     * 给一批用户加标签
     * @param tagId 标签ID
     * @param openIds openID列表
     */
    public void tagUsers(int tagId, List<String> openIds){
        Map<String, Object> map = new HashMap<>();
        map.put("tagid", tagId);
        map.put("openid_list", openIds);
        String url = WxEndpoint.get("url.tag.user.tag");
        logger.debug("add tag for users: {}", JsonMapper.defaultMapper().toJson(map));
        wxClient.post(url, JsonMapper.defaultMapper().toJson(map));
    }

    /**
     * 给一批用户加取消标签
     * @param tagId 标签ID
     * @param openIds openID列表
     */
    public void unTagUsers(int tagId, List<String> openIds){
        Map<String, Object> map = new HashMap<>();
        map.put("tagid", tagId);
        map.put("openid_list", openIds);
        String url = WxEndpoint.get("url.tag.user.untag");
        logger.debug("remove tag for users: {}", JsonMapper.defaultMapper().toJson(map));
        wxClient.post(url, JsonMapper.defaultMapper().toJson(map));
    }

    /**
     * 或者用户身上标签
     * @param openId openId
     * @return
     */
    public List<Integer> getUserTags(String openId) {
        String url = WxEndpoint.get("url.tag.user.gettag");
        Map<String, Object> map = new HashMap<>();
        map.put("openid", openId);
        String response = wxClient.post(url, JsonMapper.nonEmptyMapper().toJson(map));
        TagIdsWrapper tagIdsWrapper = JsonMapper.defaultMapper().fromJson(response, TagIdsWrapper.class);
        return tagIdsWrapper.getList();
    }

    public static class TagList {

        @JsonProperty("tags")
        private List<Tag> tags;

        public List<Tag> getTags() {
            return tags;
        }

        public void setTags(List<Tag> tags) {
            this.tags = tags;
        }
    }

    public static class TagWrapper {

        public TagWrapper() {
        }

        public TagWrapper(int id) {
            Tag tag = new Tag();
            tag.setId(id);
            this.tag = tag;
        }

        public TagWrapper(String name) {
            Tag tag = new Tag();
            tag.setName(name);
            this.tag = tag;
        }

        public TagWrapper(Tag tag) {
            this.tag = tag;
        }

        private Tag tag;

        public Tag getTag() {
            return tag;
        }

        public void setTag(Tag tag) {
            this.tag = tag;
        }
    }

    public static class TagIdsWrapper {

        @JsonProperty("tagid_list")
        private List<Integer> list;

        public List<Integer> getList() {
            return list;
        }

        public void setList(List<Integer> list) {
            this.list = list;
        }
    }

}

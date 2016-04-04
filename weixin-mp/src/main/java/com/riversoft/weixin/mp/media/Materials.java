package com.riversoft.weixin.mp.media;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.riversoft.weixin.common.WxClient;
import com.riversoft.weixin.common.exception.WxRuntimeException;
import com.riversoft.weixin.common.media.MediaType;
import com.riversoft.weixin.common.media.MpArticle;
import com.riversoft.weixin.common.media.MpNews;
import com.riversoft.weixin.common.media.Video;
import com.riversoft.weixin.common.util.DateDeserializer;
import com.riversoft.weixin.common.util.JsonMapper;
import com.riversoft.weixin.mp.MpWxClientFactory;
import com.riversoft.weixin.mp.base.AppSetting;
import com.riversoft.weixin.mp.base.WxEndpoint;
import com.riversoft.weixin.mp.media.bean.Counts;
import com.riversoft.weixin.mp.media.bean.Material;
import com.riversoft.weixin.mp.media.bean.MaterialPagination;
import com.riversoft.weixin.mp.media.bean.MpNewsPagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.*;

/**
 * 永久素材管理
 * Created by exizhai on 11/12/2015.
 */
public class Materials {

    private static Logger logger = LoggerFactory.getLogger(Materials.class);

    private WxClient wxClient;

    public static Materials defaultMaterials() {
        return with(AppSetting.defaultSettings());
    }

    public static Materials with(AppSetting appSetting) {
        Materials materials = new Materials();
        materials.setWxClient(MpWxClientFactory.getInstance().with(appSetting));
        return materials;
    }

    public void setWxClient(WxClient wxClient) {
        this.wxClient = wxClient;
    }

    /**
     * 图文消息的content里面如果有图片，该图片需要使用本方法上传，图片仅支持jpg/png格式，大小必须在1MB以下
     *
     * @param inputStream 图片流
     * @param fileName    文件名
     * @return 图片的url, 可以在图文消息的content里面使用
     */
    public String addMpNewsImage(InputStream inputStream, String fileName) {
        String url = WxEndpoint.get("url.mpnews.image.upload");
        String response = wxClient.post(url, inputStream, fileName);

        Map<String, Object> result = JsonMapper.defaultMapper().json2Map(response);
        if (result.containsKey("url")) {
            return result.get("url").toString();
        } else {
            logger.warn("mpnews image upload failed: {}", response);
            throw new WxRuntimeException(999, response);
        }
    }

    /**
     * 上传一个图文素材
     *
     * @param mpNews 图文素材
     * @return 返回media id
     */
    public String addMpNews(MpNews mpNews) {
        String url = WxEndpoint.get("url.material.mpnews.add");

        String json = JsonMapper.nonEmptyMapper().toJson(mpNews);
        logger.info("add mpnews: {}", json);
        String response =  wxClient.post(url, json);
        Map<String, Object> result = JsonMapper.defaultMapper().json2Map(response);
        if (result.containsKey("media_id")) {
            return result.get("media_id").toString();
        } else {
            logger.warn("mpnews add failed: {}", response);
            throw new WxRuntimeException(999, response);
        }
    }

    /**
     * 获取图文素材
     *
     * @param mediaId media id
     * @return 图文素材
     */
    public MpNews getMpNews(String mediaId) {
        String url = WxEndpoint.get("url.material.mpnews.get");

        String json = String.format("{\"media_id\":\"%s\"}", mediaId);
        logger.info("get mpnews: {}", json);
        String response = wxClient.post(url, json);
        return JsonMapper.defaultMapper().fromJson(response, MpNews.class);
    }

    /**
     * 修改图文素材
     *
     * @param mediaId 图文素材 media id
     * @param index   要更新的文章在图文素材中的位置
     * @param article 文章
     */
    public void updateMpNews(String mediaId, int index, MpArticle article) {
        String url = WxEndpoint.get("url.material.mpnews.update");
        Map<String, Object> request = new HashMap<>();

        request.put("media_id", mediaId);
        request.put("index", index);
        request.put("articles", article);

        String json = JsonMapper.nonEmptyMapper().toJson(request);
        logger.info("update mpnews: {}", json);
        wxClient.post(url, json);
    }

    /**
     * 上传voice
     *
     * @param inputStream voice
     * @param fileName    文件名
     * @return 上传结果
     */
    public Material addVoice(InputStream inputStream, String fileName) {
        return upload(MediaType.voice, inputStream, fileName);
    }

    /**
     * 获取voice
     *
     * @param mediaId media id
     * @return voice
     */
    public InputStream getVoice(String mediaId) {
        return download(mediaId);
    }

    /**
     * 上传图片
     *
     * @param inputStream 图片流
     * @param fileName    文件名
     * @return 上传结果
     */
    public Material addImage(InputStream inputStream, String fileName) {
        return upload(MediaType.image, inputStream, fileName);
    }

    /**
     * 获取image
     *
     * @param mediaId media id
     * @return image
     */
    public InputStream getImage(String mediaId) {
        return download(mediaId);
    }

    /**
     * 上传缩略图
     *
     * @param inputStream 缩略图
     * @param fileName    文件名
     * @return 上传结果
     */
    public Material addThumb(InputStream inputStream, String fileName) {
        return upload(MediaType.thumb, inputStream, fileName);
    }

    /**
     * 获取缩略图
     *
     * @param mediaId media id
     * @return thumb
     */
    public InputStream getThumb(String mediaId) {
        return download(mediaId);
    }

    /**
     * 上传视频
     *
     * @param inputStream 视频流
     * @param fileName    文件名
     * @param title       title
     * @param description 描述
     * @return 上传结果
     */
    public Material addVideo(InputStream inputStream, String fileName, String title, String description) {
        String url = WxEndpoint.get("url.material.binary.upload");
        String desc = "{\"title\":\"%s\",\"introduction\":\"%s\"}";
        Map<String, String> form = new HashMap<>();
        form.put("description", String.format(desc, title, description));
        String response = wxClient.post(String.format(url, MediaType.video.name()), inputStream, fileName, form);

        Map<String, Object> result = JsonMapper.defaultMapper().json2Map(response);
        if (result.containsKey("media_id")) {
            return JsonMapper.defaultMapper().fromJson(response, Material.class);
        } else {
            logger.warn("image upload failed: {}", response);
            throw new WxRuntimeException(999, response);
        }
    }

    /**
     * 获取视频
     *
     * @param mediaId media id
     * @return video
     */
    public Video getVideo(String mediaId) {
        String response = wxClient.get(String.format(WxEndpoint.get("url.material.binary.get"), mediaId));
        Map<String, Object> result = JsonMapper.defaultMapper().json2Map(response);
        if (result.containsKey("title")) {
            return JsonMapper.defaultMapper().fromJson(response, Video.class);
        } else {
            logger.warn("download video failed: {}", response);
            throw new WxRuntimeException(999, response);
        }
    }

    public Counts count() {
        String response = wxClient.get(WxEndpoint.get("url.material.count"));
        return JsonMapper.defaultMapper().fromJson(response, Counts.class);
    }

    public MaterialPagination list(MediaType mediaType, int offset, int count) {
        if (mediaType != MediaType.news && mediaType != MediaType.mpnews) {
            String url = WxEndpoint.get("url.material.list");
            String body = "{\"type\":\"%s\",\"offset\":%s,\"count\":%s}";

            String response = wxClient.post(url, String.format(body, mediaType.name(), offset, count));
            logger.debug("list materials: {}", response);

            return toMaterialSearchResult(JsonMapper.defaultMapper().fromJson(response, MpMaterialSearchResult.class));
        } else {
            throw new WxRuntimeException(999, "cannot support mpnews list.");
        }
    }

    public MpNewsPagination listMpNews(int offset, int count) {
        String url = WxEndpoint.get("url.material.list");
        String body = "{\"type\":\"%s\",\"offset\":%s,\"count\":%s}";

        String response = wxClient.post(url, String.format(body, MediaType.news.name(), offset, count));
        logger.debug("list mpnwes: {}", response);

        return JsonMapper.defaultMapper().fromJson(response, MpNewsPagination.class);
    }

    private Material upload(MediaType type, InputStream inputStream, String fileName) {
        if (type == MediaType.mpnews || type == MediaType.video) {
            throw new WxRuntimeException(999, "cannot support mpnews or video upload.");
        } else {
            String url = WxEndpoint.get("url.material.binary.upload");
            String response = wxClient.post(String.format(url, type.name()), inputStream, fileName);

            Map<String, Object> result = JsonMapper.defaultMapper().json2Map(response);
            if (result.containsKey("media_id")) {
                return JsonMapper.defaultMapper().fromJson(response, Material.class);
            } else {
                logger.warn("image upload failed: {}", response);
                throw new WxRuntimeException(999, response);
            }
        }
    }

    private InputStream download(String mediaId) {
        String post = "{\"media_id\":\"%s\"}";
        return wxClient.copyStream(WxEndpoint.get("url.material.binary.get"), String.format(post, mediaId));
    }

    /**
     * 删除永久素材
     * @param mediaId
     */
    public void delete(String mediaId) {
        String url = WxEndpoint.get("url.material.delete");
        String body = String.format("{\"media_id\":\"%s\"}", mediaId);
        logger.debug("material delete: {}", body);
        wxClient.post(url, body);
    }

    private MaterialPagination toMaterialSearchResult(MpMaterialSearchResult mpMaterialSearchResult) {
        MaterialPagination result = new MaterialPagination();
        result.setTotalCount(mpMaterialSearchResult.getTotalCount());
        result.setCurrentCount(mpMaterialSearchResult.getCurrentCount());

        List<MpMaterialSearchResult.Material> qyItems = mpMaterialSearchResult.getItems();
        List<MaterialPagination.Material> items = new ArrayList<>();

        for (MpMaterialSearchResult.Material mpItem : qyItems) {
            MaterialPagination.Material item = new MaterialPagination.Material();
            item.setFileName(mpItem.getName());
            item.setMediaId(mpItem.getMediaId());
            item.setUpdateTime(mpItem.updateTime);
            item.setUrl(mpItem.getUrl());
            items.add(item);
        }
        result.setItems(items);
        return result;
    }


    public static class MpMaterialSearchResult {

        /**
         * 总素材数
         */
        @JsonProperty("total_count")
        private int totalCount;

        /**
         * 当前页返回素材数母
         */
        @JsonProperty("item_count")
        private int currentCount;

        /**
         * 素材列表
         */
        @JsonProperty("item")
        private List<Material> items;

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public int getCurrentCount() {
            return currentCount;
        }

        public void setCurrentCount(int currentCount) {
            this.currentCount = currentCount;
        }

        public List<Material> getItems() {
            return items;
        }

        public void setItems(List<Material> items) {
            this.items = items;
        }

        public static class Material {

            @JsonProperty("media_id")
            private String mediaId;
            private String name;

            @JsonProperty("update_time")
            @JsonDeserialize(using = DateDeserializer.class)
            private Date updateTime;

            /**
             * only for 图片素材
             */
            private String url;

            public String getMediaId() {
                return mediaId;
            }

            public void setMediaId(String mediaId) {
                this.mediaId = mediaId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public Date getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(Date updateTime) {
                this.updateTime = updateTime;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}

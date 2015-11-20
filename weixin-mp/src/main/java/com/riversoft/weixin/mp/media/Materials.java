package com.riversoft.weixin.mp.media;

import com.riversoft.weixin.common.WxClient;
import com.riversoft.weixin.common.exception.WxRuntimeException;
import com.riversoft.weixin.common.media.MediaType;
import com.riversoft.weixin.common.message.MpArticle;
import com.riversoft.weixin.mp.media.bean.*;
import com.riversoft.weixin.common.message.MpNews;
import com.riversoft.weixin.common.util.JsonMapper;
import com.riversoft.weixin.mp.MpWxClientFactory;
import com.riversoft.weixin.mp.base.AppSetting;
import com.riversoft.weixin.mp.base.WxEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
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
     * @param inputStream 图片流
     * @param fileName 文件名
     * @return 图片的url,可以在图文消息的content里面使用
     */
    public String addMpNewsImage(InputStream inputStream, String fileName){
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
     * @param mpNews 图文素材
     * @return 返回media id
     */
    public String addMpNews(MpNews mpNews){
        String url = WxEndpoint.get("url.material.mpnews.add");

        String json = JsonMapper.nonEmptyMapper().toJson(mpNews);
        logger.info("add mpnews: {}", json);
        return wxClient.post(url, json);
    }

    /**
     * 删除图文素材
     * @param mediaId media id
     */
    public void deleteMpNews(String mediaId){
        delete(mediaId);
    }

    /**
     * 获取图文素材
     * @param mediaId media id
     * @return 图文素材
     */
    public MpNews getMpNews(String mediaId) {
        return null;
    }

    /**
     * 修改图文素材
     * @param mediaId 图文素材 media id
     * @param index 要更新的文章在图文素材中的位置
     * @param article 文章
     */
    public void updateMpNews(String mediaId, int index, MpArticle article){
        String url = WxEndpoint.get("url.material.mpnews.update");
        MpNewsUpdateRequest mpNewsUpdateRequest = new MpNewsUpdateRequest();
        mpNewsUpdateRequest.setMediaId(mediaId);
        mpNewsUpdateRequest.setIndex(index);
        mpNewsUpdateRequest.setArticle(article);

        String json = JsonMapper.nonEmptyMapper().toJson(mpNewsUpdateRequest);
        logger.info("update mpnews: {}", json);
        wxClient.post(url, json);
    }

    /**
     * 上传voice
     * @param inputStream voice
     * @param fileName 文件名
     * @return 上传结果
     */
    public MaterialUploadResult addVoice(InputStream inputStream, String fileName){
        return upload(MediaType.voice, inputStream, fileName);
    }

    /**
     * 获取voice
     * @param mediaId media id
     * @return voice
     */
    public File getVoice(String mediaId) {
        return download(mediaId);
    }

    /**
     * 删除voice
     * @param mediaId  media id
     */
    public void deleteVoice(String mediaId) {
        delete(mediaId);
    }

    /**
     * 上传图片
     * @param inputStream 图片流
     * @param fileName 文件名
     * @return 上传结果
     */
    public MaterialUploadResult addImage(InputStream inputStream, String fileName){
        return upload(MediaType.image, inputStream, fileName);
    }

    /**
     * 获取image
     * @param mediaId media id
     * @return image
     */
    public File getImage(String mediaId) {
        return download(mediaId);
    }

    /**
     * 删除image
     * @param mediaId  media id
     */
    public void deleteImage(String mediaId) {
        delete(mediaId);
    }

    /**
     * 上传缩略图
     * @param inputStream 缩略图
     * @param fileName 文件名
     * @return 上传结果
     */
    public MaterialUploadResult addThumb(InputStream inputStream, String fileName){
        return upload(MediaType.thumb, inputStream, fileName);
    }

    /**
     * 获取缩略图
     * @param mediaId media id
     * @return thumb
     */
    public File getThumb(String mediaId) {
        return download(mediaId);
    }

    /**
     * 删除缩略图
     * @param mediaId  media id
     */
    public void deleteThumb(String mediaId) {
        delete(mediaId);
    }

    /**
     * 上传视频
     * @param inputStream 视频流
     * @param fileName 文件名
     * @param title title
     * @param description 描述
     * @return 上传结果
     */
    public MaterialUploadResult addVideo(InputStream inputStream, String fileName, String title, String description) {
        String url = WxEndpoint.get("url.material.binary.upload");
        String desc = "{\"title\":\"%s\",\"introduction\":\"%s\"}";
        Map<String, String> form = new HashMap<>();
        form.put("description", String.format(desc, title, description));
        String response = wxClient.post(String.format(url, MediaType.video.name()), inputStream, fileName, form);

        Map<String, Object> result = JsonMapper.defaultMapper().json2Map(response);
        if (result.containsKey("media_id")) {
            return JsonMapper.defaultMapper().fromJson(response, MaterialUploadResult.class);
        } else {
            logger.warn("image upload failed: {}", response);
            throw new WxRuntimeException(999, response);
        }
    }

    /**
     * 获取视频
     * @param mediaId media id
     * @return video
     */
    public VideoSearchResult getVideo(String mediaId) {
        String response = wxClient.get(String.format(WxEndpoint.get("url.material.binary.get"), mediaId));
        Map<String, Object> result = JsonMapper.defaultMapper().json2Map(response);
        if (result.containsKey("title")) {
            return JsonMapper.defaultMapper().fromJson(response, VideoSearchResult.class);
        } else {
            logger.warn("download video failed: {}", response);
            throw new WxRuntimeException(999, response);
        }
    }

    /**
     * 删除视频文件
     * @param mediaId  media id
     */
    public void deleteVideo(String mediaId) {
        delete(mediaId);
    }

    public Counts count() {
        String response = wxClient.get(WxEndpoint.get("url.material.count"));
        return JsonMapper.defaultMapper().fromJson(response, Counts.class);
    }

    public MaterialSearchResult list(MediaType mediaType, int offset, int count) {
        if(mediaType != MediaType.mpnews) {
            String url = WxEndpoint.get("url.material.list");
            String body = "{\"type\":\"%s\",\"offset\":%s,\"count\":%s}";

            String response = wxClient.post(url, String.format(body, mediaType.name(), offset, count));
            logger.debug("list materials: {}", response);

            return JsonMapper.defaultMapper().fromJson(response, MaterialSearchResult.class);
        } else {
            throw new WxRuntimeException(999, "cannot support mpnews list.");
        }
    }

    public MpNewsSearchResult listMpNews(int offset, int count) {
        String url = WxEndpoint.get("url.material.list");
        String body = "{\"type\":\"%s\",\"offset\":%s,\"count\":%s}";

        String response = wxClient.post(url, String.format(body, MediaType.mpnews.name(), offset, count));
        logger.debug("list mpnwes: {}", response);

        return JsonMapper.defaultMapper().fromJson(response, MpNewsSearchResult.class);
    }

    private MaterialUploadResult upload(MediaType type, InputStream inputStream, String fileName) {
        if(type == MediaType.mpnews || type == MediaType.video) {
            throw new WxRuntimeException(999, "cannot support mpnews upload.");
        } else {
            String url = WxEndpoint.get("url.material.binary.upload");
            String response = wxClient.post(String.format(url, MediaType.image.name()), inputStream, fileName);

            Map<String, Object> result = JsonMapper.defaultMapper().json2Map(response);
            if (result.containsKey("media_id")) {
                return JsonMapper.defaultMapper().fromJson(response, MaterialUploadResult.class);
            } else {
                logger.warn("image upload failed: {}", response);
                throw new WxRuntimeException(999, response);
            }
        }
    }

    private File download(String mediaId) {
        return wxClient.download(String.format(WxEndpoint.get("url.material.binary.get"), mediaId));
    }

    private void delete(String mediaId) {
        String url = WxEndpoint.get("url.material.delete");
        String body = "{\"media_id\":\"%s\"}";
        String response = wxClient.post(String.format(url, mediaId), body);
        logger.info("material delete result: {}", response);
    }
}

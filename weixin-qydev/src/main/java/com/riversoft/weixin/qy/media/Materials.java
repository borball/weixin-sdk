package com.riversoft.weixin.qy.media;

import com.riversoft.weixin.common.WxClient;
import com.riversoft.weixin.common.message.MpNews;
import com.riversoft.weixin.common.util.JsonMapper;
import com.riversoft.weixin.qy.QyWxClientFactory;
import com.riversoft.weixin.qy.base.CorpSetting;
import com.riversoft.weixin.qy.base.DefaultSettings;
import com.riversoft.weixin.qy.base.WxEndpoint;
import com.riversoft.weixin.qy.exception.WxRuntimeException;
import com.riversoft.weixin.qy.media.bean.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.InputStream;
import java.util.Map;

/**
 * Created by exizhai on 10/12/2015.
 */
public class Materials {

    private static Logger logger = LoggerFactory.getLogger(Materials.class);

    private WxClient wxClient;

    public static Materials defaultMaterials() {
        return with(DefaultSettings.defaultSettings().getCorpSetting());
    }

    public static Materials with(CorpSetting corpSetting) {
        Materials materials = new Materials();
        materials.setWxClient(QyWxClientFactory.getInstance().with(corpSetting));
        return materials;
    }

    public void setWxClient(WxClient wxClient) {
        this.wxClient = wxClient;
    }

    public String addMpNews(MpNews mpNews){
        return addMpNews(DefaultSettings.defaultSettings().getDefaultAgent(), mpNews);
    }

    public String addMpNews(int agent, MpNews mpNews){
        String url = WxEndpoint.get("url.material.mpnews.add");
        AddMpNewsRequest addMpNewsRequest = new AddMpNewsRequest();
        addMpNewsRequest.setAgentId(agent);
        addMpNewsRequest.setMpNews(mpNews);

        String json = JsonMapper.nonEmptyMapper().toJson(addMpNewsRequest);
        logger.info("add mpnews: {}", json);
        return wxClient.post(url, json);
    }

    public void updateMpNews(String mediaId, MpNews mpNews){
        updateMpNews(DefaultSettings.defaultSettings().getDefaultAgent(), mediaId, mpNews);
    }

    public void updateMpNews(int agent, String mediaId, MpNews mpNews){
        String url = WxEndpoint.get("url.material.mpnews.update");
        UpdateMpNewsRequest updateMpNewsRequest = new UpdateMpNewsRequest();
        updateMpNewsRequest.setMpNews(mpNews);
        updateMpNewsRequest.setAgentId(agent);
        updateMpNewsRequest.setMediaId(mediaId);

        String json = JsonMapper.nonEmptyMapper().toJson(updateMpNewsRequest);
        logger.info("update mpnews: {}", json);
        wxClient.post(url, json);
    }

    public MpNews getMpNews(String mediaId){
        return getMpNews(DefaultSettings.defaultSettings().getDefaultAgent(), mediaId);
    }

    public MpNews getMpNews(int agent, String mediaId){
        String url = WxEndpoint.get("url.material.mpnews.get");
        String response = wxClient.get(String.format(url, agent, mediaId));
        GetMpNewsResponse getMpNewsResponse = JsonMapper.defaultMapper().fromJson(response, GetMpNewsResponse.class);
        if(getMpNewsResponse != null) {
            return getMpNewsResponse.getMpNews();
        } else {
            return null;
        }
    }

    public void deleteMpNews(int agentId, String mediaId){
        delete(agentId, mediaId);
    }

    public void deleteMpNews(String mediaId){
        delete(DefaultSettings.defaultSettings().getDefaultAgent(), mediaId);
    }

    public String upload(MediaType type, InputStream inputStream, String fileName) {
        return upload(DefaultSettings.defaultSettings().getDefaultAgent(), type, inputStream, fileName);
    }

    public String upload(int agent, MediaType type, InputStream inputStream, String fileName) {
        String url = WxEndpoint.get("url.material.binary.upload");

        String response = wxClient.post(String.format(url, agent, type.name()), inputStream, fileName);

        Map<String, Object> result = JsonMapper.defaultMapper().json2Map(response);
        if (result.containsKey("errcode") && "0".equals(result.get("errcode").toString())) {
            return result.get("media_id").toString();
        } else {
            logger.warn("material upload failed: {}", response);
            throw new WxRuntimeException(998, response);
        }
    }

    public File download(String mediaId) {
        return download(DefaultSettings.defaultSettings().getDefaultAgent(), mediaId);
    }

    public File download(int agent, String mediaId) {
        return wxClient.download(String.format(WxEndpoint.get("url.material.binary.get"), agent, mediaId));
    }

    public void delete(String mediaId) {
        delete(DefaultSettings.defaultSettings().getDefaultAgent(), mediaId);
    }

    public void delete(int agent, String mediaId) {
        String response = wxClient.get(String.format(WxEndpoint.get("url.material.delete"), agent, mediaId));
        logger.info("material delete result: {}", response);
    }

    public Counts count() {
        return count(DefaultSettings.defaultSettings().getDefaultAgent());
    }

    public Counts count(int agent) {
        String response = wxClient.get(String.format(WxEndpoint.get("url.material.count"), agent));
        return JsonMapper.defaultMapper().fromJson(response, Counts.class);
    }

    public SearchResult list(Pagination pagination) {
        String response = wxClient.post(WxEndpoint.get("url.material.list"), JsonMapper.defaultMapper().toJson(pagination));
        return JsonMapper.defaultMapper().fromJson(response, SearchResult.class);
    }
}

package com.riversoft.weixin.qy.media;

import com.riversoft.weixin.qy.base.DefaultSettings;
import com.riversoft.weixin.qy.base.WxClient;
import com.riversoft.weixin.qy.exception.WxRuntimeException;
import com.riversoft.weixin.qy.media.bean.Counts;
import com.riversoft.weixin.qy.media.bean.MediaType;
import com.riversoft.weixin.qy.media.bean.Pagination;
import com.riversoft.weixin.qy.media.bean.SearchResult;
import com.riversoft.weixin.qy.util.JsonMapper;
import com.riversoft.weixin.qy.base.WxEndpoint;
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

    private static Materials materials = null;
    private WxClient wxClient;

    public static Materials defaultMaterials() {
        if (materials == null) {
            materials = new Materials();
            materials.setWxClient(WxClient.defaultWxClient());
        }

        return materials;
    }

    public void setWxClient(WxClient wxClient) {
        this.wxClient = wxClient;
    }

    public String upload(MediaType type, InputStream inputStream, String extName) {
        return upload(DefaultSettings.defaultSettings().getDefaultAgent(), type, inputStream, extName);
    }

    public String upload(int agent, MediaType type, InputStream inputStream, String extName) {
        String url = WxEndpoint.get("url.material.upload");

        String response = wxClient.post(String.format(url, agent, type.name()), inputStream, extName);

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
        return wxClient.download(String.format(WxEndpoint.get("url.material.get"), agent, mediaId));
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

package com.riversoft.weixin.media;

import com.riversoft.weixin.base.Settings;
import com.riversoft.weixin.base.WxClient;
import com.riversoft.weixin.exception.WxRuntimeException;
import com.riversoft.weixin.media.bean.Counts;
import com.riversoft.weixin.media.bean.MediaType;
import com.riversoft.weixin.media.bean.Pagination;
import com.riversoft.weixin.media.bean.SearchResult;
import com.riversoft.weixin.util.JsonMapper;
import com.riversoft.weixin.base.WxEndpoint;
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
        return upload(Settings.defaultSettings().getDefaultAgent(), type, inputStream, extName);
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
        return download(Settings.defaultSettings().getDefaultAgent(), mediaId);
    }

    public File download(int agent, String mediaId) {
        return wxClient.download(String.format(WxEndpoint.get("url.material.get"), agent, mediaId));
    }

    public void delete(String mediaId) {
        delete(Settings.defaultSettings().getDefaultAgent(), mediaId);
    }

    public void delete(int agent, String mediaId) {
        String response = wxClient.get(String.format(WxEndpoint.get("url.material.delete"), agent, mediaId));
        logger.info("material delete result: {}", response);
    }

    public Counts count() {
        return count(Settings.defaultSettings().getDefaultAgent());
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

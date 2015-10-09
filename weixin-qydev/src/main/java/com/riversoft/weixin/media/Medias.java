package com.riversoft.weixin.media;

import com.riversoft.weixin.base.WxClient;
import com.riversoft.weixin.exception.WxRuntimeException;
import com.riversoft.weixin.media.bean.Counts;
import com.riversoft.weixin.media.bean.MediaType;
import com.riversoft.weixin.media.bean.Pagination;
import com.riversoft.weixin.media.bean.SearchResult;
import com.riversoft.weixin.util.JsonMapper;
import com.riversoft.weixin.util.PropertiesLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.InputStream;
import java.util.Map;

/**
 * Created by exizhai on 10/1/2015.
 */
public class Medias {

    private static Logger logger = LoggerFactory.getLogger(Medias.class);

    private static Medias medias = null;
    private WxClient wxClient;

    public static Medias defaultMedias() {
        if (medias == null) {
            medias = new Medias();
            medias.setWxClient(WxClient.defaultWxClient());
        }

        return medias;
    }

    public void setWxClient(WxClient wxClient) {
        this.wxClient = wxClient;
    }

    public String mediaUpload(MediaType type, InputStream inputStream, String extName) {
        String url = PropertiesLoader.getInstance().getProperty("url.media.upload");

        String response = wxClient.post(String.format(url, type.name()), inputStream, extName);

        //为什么这个成功返回的response没有error code，和其他的格格不入,临时工弄的?
        Map<String, Object> result = JsonMapper.defaultMapper().json2Map(response);
        if (result.containsKey("media_id")) {
            return result.get("media_id").toString();
        } else {
            logger.warn("media upload failed: {}", response);
            throw new WxRuntimeException(998, response);
        }
    }

    public File mediaDownload(String mediaId) {
        return wxClient.download(String.format(PropertiesLoader.getInstance().getProperty("url.media.get"), mediaId));
    }

    public String materialUpload(int agent, MediaType type, InputStream inputStream, String extName) {
        String url = PropertiesLoader.getInstance().getProperty("url.material.upload");

        String response = wxClient.post(String.format(url, agent, type.name()), inputStream, extName);

        Map<String, Object> result = JsonMapper.defaultMapper().json2Map(response);
        if (result.containsKey("errcode") && "0".equals(result.get("errcode").toString())) {
            return result.get("media_id").toString();
        } else {
            logger.warn("material upload failed: {}", response);
            throw new WxRuntimeException(998, response);
        }
    }

    public File materialDownload(int agent, String mediaId) {
        return wxClient.download(String.format(PropertiesLoader.getInstance().getProperty("url.material.get"), agent, mediaId));
    }

    public void materialDelete(int agent, String mediaId) {
        String response = wxClient.get(String.format(PropertiesLoader.getInstance().getProperty("url.material.delete"), agent, mediaId));
        logger.info("material delete result: {}", response);
    }

    public Counts count(int agent) {
        String response = wxClient.get(String.format(PropertiesLoader.getInstance().getProperty("url.material.count"), agent));
        return JsonMapper.defaultMapper().fromJson(response, Counts.class);
    }

    public SearchResult list(Pagination pagination) {
        String response = wxClient.post(PropertiesLoader.getInstance().getProperty("url.material.list"), JsonMapper.defaultMapper().toJson(pagination));
        return JsonMapper.defaultMapper().fromJson(response, SearchResult.class);
    }
}

package com.riversoft.weixin.qy.media;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.riversoft.weixin.common.WxClient;
import com.riversoft.weixin.common.media.MaterialSearchResult;
import com.riversoft.weixin.common.media.MediaType;
import com.riversoft.weixin.common.message.MpNews;
import com.riversoft.weixin.common.util.DateDeserializer;
import com.riversoft.weixin.common.util.JsonMapper;
import com.riversoft.weixin.qy.QyWxClientFactory;
import com.riversoft.weixin.qy.base.CorpSetting;
import com.riversoft.weixin.qy.base.WxEndpoint;
import com.riversoft.weixin.qy.exception.WxRuntimeException;
import com.riversoft.weixin.qy.media.bean.Counts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.InputStream;
import java.util.*;

/**
 * 永久素材管理
 * Created by exizhai on 10/12/2015.
 */
public class Materials {

    private static Logger logger = LoggerFactory.getLogger(Materials.class);

    private WxClient wxClient;

    public static Materials defaultMaterials() {
        return with(CorpSetting.defaultSettings());
    }

    public static Materials with(CorpSetting corpSetting) {
        Materials materials = new Materials();
        materials.setWxClient(QyWxClientFactory.getInstance().with(corpSetting));
        return materials;
    }

    public void setWxClient(WxClient wxClient) {
        this.wxClient = wxClient;
    }

    public String addMpNews(int agent, MpNews mpNews) {
        String url = WxEndpoint.get("url.material.mpnews.add");

        Map<String, Object> request = new HashMap<>();
        request.put("agentid", agent);
        request.put("mpnews", mpNews);

        String json = JsonMapper.nonEmptyMapper().toJson(request);
        logger.info("add mpnews: {}", json);
        return wxClient.post(url, json);
    }

    public void updateMpNews(int agent, String mediaId, MpNews mpNews) {
        String url = WxEndpoint.get("url.material.mpnews.update");
        Map<String, Object> request = new HashMap<>();
        request.put("agentid", agent);
        request.put("mpnews", mpNews);
        request.put("mediaId", mediaId);

        String json = JsonMapper.nonEmptyMapper().toJson(request);
        logger.info("update mpnews: {}", json);
        wxClient.post(url, json);
    }

    public MpNews getMpNews(int agent, String mediaId) {
        String url = WxEndpoint.get("url.material.mpnews.get");
        String response = wxClient.get(String.format(url, agent, mediaId));
        GetMpNewsResponse getMpNewsResponse = JsonMapper.defaultMapper().fromJson(response, GetMpNewsResponse.class);
        if (getMpNewsResponse != null) {
            return getMpNewsResponse.getMpNews();
        } else {
            return null;
        }
    }

    public void deleteMpNews(int agentId, String mediaId) {
        delete(agentId, mediaId);
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

    public File download(int agent, String mediaId) {
        return wxClient.download(String.format(WxEndpoint.get("url.material.binary.get"), agent, mediaId));
    }

    public void delete(int agent, String mediaId) {
        String response = wxClient.get(String.format(WxEndpoint.get("url.material.delete"), agent, mediaId));
        logger.info("material delete result: {}", response);
    }

    public Counts count(int agent) {
        String response = wxClient.get(String.format(WxEndpoint.get("url.material.count"), agent));
        return JsonMapper.defaultMapper().fromJson(response, Counts.class);
    }

    public MaterialSearchResult list(int agentId, MediaType type, int offset, int size) {
        String request = "{\"type\":\"%s\",\"agentid\":%s,\"offset\":%s,\"count\":%s}";
        String url = WxEndpoint.get("url.material.list");
        String response = wxClient.post(url, String.format(request, type.name(), agentId, offset, size));

        return toMaterialSearchResult(JsonMapper.defaultMapper().fromJson(response, QyMaterialSearchResult.class));
    }

    private MaterialSearchResult toMaterialSearchResult(QyMaterialSearchResult qyMaterialSearchResult) {
        MaterialSearchResult result = new MaterialSearchResult();
        result.setTotalCount(qyMaterialSearchResult.getTotalCount());
        result.setCurrentCount(qyMaterialSearchResult.getCurrentCount());

        List<QyMaterialSearchResult.Material> qyItems = qyMaterialSearchResult.getItems();
        List<MaterialSearchResult.Material> items = new ArrayList<>();

        for (QyMaterialSearchResult.Material qyItem : qyItems) {
            MaterialSearchResult.Material item = new MaterialSearchResult.Material();
            item.setFileName(qyItem.getFileName());
            item.setMediaId(qyItem.getMediaId());
            item.setUpdateTime(qyItem.updateTime);

            items.add(item);
        }
        result.setItems(items);
        return result;
    }

    public static class QyMaterialSearchResult {

        private MediaType type;

        @JsonProperty("total_count")
        private int totalCount;

        @JsonProperty("item_count")
        private int currentCount;

        @JsonProperty("itemlist")
        @JsonUnwrapped()
        private List<Material> items;

        public MediaType getType() {
            return type;
        }

        public void setType(MediaType type) {
            this.type = type;
        }

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

            @JsonProperty("filename")
            private String fileName;

            @JsonProperty("update_time")
            @JsonDeserialize(using = DateDeserializer.class)
            private Date updateTime;

            public String getMediaId() {
                return mediaId;
            }

            public void setMediaId(String mediaId) {
                this.mediaId = mediaId;
            }

            public String getFileName() {
                return fileName;
            }

            public void setFileName(String fileName) {
                this.fileName = fileName;
            }

            public Date getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(Date updateTime) {
                this.updateTime = updateTime;
            }
        }
    }

    public static class GetMpNewsResponse {

        private String type;

        @JsonProperty("mpnews")
        private MpNews mpNews;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public MpNews getMpNews() {
            return mpNews;
        }

        public void setMpNews(MpNews mpNews) {
            this.mpNews = mpNews;
        }
    }
}

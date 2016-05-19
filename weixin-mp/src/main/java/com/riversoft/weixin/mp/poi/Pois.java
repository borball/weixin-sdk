package com.riversoft.weixin.mp.poi;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.riversoft.weixin.common.WxClient;
import com.riversoft.weixin.common.exception.WxRuntimeException;
import com.riversoft.weixin.common.util.JsonMapper;
import com.riversoft.weixin.mp.MpWxClientFactory;
import com.riversoft.weixin.mp.base.AppSetting;
import com.riversoft.weixin.mp.base.WxEndpoint;
import com.riversoft.weixin.mp.poi.bean.Business;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 门店接口 V2.3
 * POI: 兴趣点
 * Created by exizhai on 11/28/2015.
 */
public class Pois {

    private static Logger logger = LoggerFactory.getLogger(Pois.class);

    private WxClient wxClient;

    public static Pois defaultPois() {
        return with(AppSetting.defaultSettings());
    }

    public static Pois with(AppSetting appSetting) {
        Pois pois = new Pois();
        pois.setWxClient(MpWxClientFactory.getInstance().with(appSetting));
        return pois;
    }

    public void setWxClient(WxClient wxClient) {
        this.wxClient = wxClient;
    }

    public void create(Business business) {
        String url = WxEndpoint.get("url.poi.create");
        String json = JsonMapper.nonEmptyMapper().toJson(new BusinessWrapper(business));
        logger.debug("create poi: {}", json);
        wxClient.post(url, json);
    }

    public void update(Business business) {
        String url = WxEndpoint.get("url.poi.update");
        String json = JsonMapper.nonEmptyMapper().toJson(new BusinessWrapper(business));
        logger.debug("update poi: {}", json);
        wxClient.post(url, json);
    }

    public Business get(String poiId) {
        String url = WxEndpoint.get("url.poi.get");
        String json = "{\"poi_id\":\"%s\"}";
        logger.debug("get poi: {}", poiId);
        String response = wxClient.post(url, String.format(json, poiId));
        BusinessWrapper businessWrapper = JsonMapper.defaultMapper().fromJson(response, BusinessWrapper.class);
        return businessWrapper.getBusiness();
    }

    public void delete(String poiId) {
        String url = WxEndpoint.get("url.poi.delete");

        String json = "{\"poi_id\":\"%s\"}";
        logger.debug("delete poi: {}", poiId);
        wxClient.post(url, String.format(json, poiId));
    }

    public int count() {
        String url = WxEndpoint.get("url.poi.list");

        String json = "{\"begin\":%s,\"limit\":%s}";
        logger.debug("count poi: {}", String.format(json, 0, 1));
        String response = wxClient.post(url, String.format(json, 0, 1));
        Map<String, Object> result = JsonMapper.defaultMapper().json2Map(response);
        if (result.containsKey("total_count")) {
            return (Integer) result.get("total_count");
        } else {
            throw new WxRuntimeException(999, "count pois failed.");
        }
    }

    public List<Business> list(int begin, int count) {
        String url = WxEndpoint.get("url.poi.list");

        String json = "{\"begin\":%s,\"limit\":%s}";
        logger.debug("list poi: {}", String.format(json, begin, count));
        String response = wxClient.post(url, String.format(json, begin, count));
        logger.debug("list poi response: {}", response);
        BusinessListWrapper businessListWrapper = JsonMapper.defaultMapper().fromJson(response, BusinessListWrapper.class);
        List<Business> businesses = new ArrayList<>();
        for (BusinessWrapper businessWrapper : businessListWrapper.getBusinesses()) {
            businesses.add(businessWrapper.getBusiness());
        }
        return businesses;
    }

    public List<String> getCategories() {
        String url = WxEndpoint.get("url.poi.categories.get");

        logger.debug("get poi categories.");
        String response = wxClient.get(url);

        CategoriesWrapper categoriesWrapper = JsonMapper.defaultMapper().fromJson(response, CategoriesWrapper.class);
        return categoriesWrapper.getCategories();
    }

    public static class CategoriesWrapper {

        @JsonProperty("category_list")
        private List<String> categories;

        public List<String> getCategories() {
            return categories;
        }

        public void setCategories(List<String> categories) {
            this.categories = categories;
        }
    }

    public static class BusinessWrapper {

        @JsonProperty("base_info")
        private Business business;

        public BusinessWrapper() {
        }

        public BusinessWrapper(Business business) {
            this.business = business;
        }

        public Business getBusiness() {
            return business;
        }

        public void setBusiness(Business business) {
            this.business = business;
        }
    }

    public static class BusinessListWrapper {

        @JsonProperty("business_list")
        private List<BusinessWrapper> businesses;

        public List<BusinessWrapper> getBusinesses() {
            return businesses;
        }

        public void setBusinesses(List<BusinessWrapper> businesses) {
            this.businesses = businesses;
        }
    }

}

package com.riversoft.weixin.mp.poi.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.riversoft.weixin.common.util.BooleanDeserializer;
import com.riversoft.weixin.common.util.BooleanSerializer;

import java.util.List;

/**
 * Created by exizhai on 11/30/2015.
 */
public class Business {

    private String sid;

    @JsonProperty("business_name")
    private String businessName;

    @JsonProperty("branch_name")
    private String branchName;
    private String province;
    private String city;
    private String district;
    private String address;
    private String telephone;
    private String[] categories;

    @JsonProperty("offset_type")
    private int offsetType;
    private double longitude;
    private double latitude;

    @JsonProperty("photo_list")
    private List<Photo> photoList;
    private String recommend;
    private String special;
    private String introduction;

    @JsonProperty("open_time")
    private String openTime;

    @JsonProperty("avg_price")
    private int avgPrice;

    @JsonProperty("poi_id")
    private String poiId;

    //以下字段查询的时候会返回，新增或者修改的时候不用提供

    /**
     * 门店是否可用状态。1 表示系统错误、2 表示审核中、3 审核通过、4 审核驳回。当该字段为1、2、4 状态时，poi_id 为空
     */
    @JsonProperty("available_state")
    @JsonDeserialize(using = StateDeserializer.class)
    private AvailableState availableState;

    /**
     * 扩展字段是否正在更新中。1 表示扩展字段正在更新中，尚未生效，不允许再次更新； 0 表示扩展字段没有在更新中或更新已生效，可以再次更新
     */
    @JsonProperty("update_status")
    @JsonDeserialize(using = BooleanDeserializer.class)
    @JsonSerialize(using = BooleanSerializer.class)
    private boolean updated;

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String[] getCategories() {
        return categories;
    }

    public void setCategories(String[] categories) {
        this.categories = categories;
    }

    public int getOffsetType() {
        return offsetType;
    }

    public void setOffsetType(int offsetType) {
        this.offsetType = offsetType;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public List<Photo> getPhotoList() {
        return photoList;
    }

    public void setPhotoList(List<Photo> photoList) {
        this.photoList = photoList;
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }

    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
        this.special = special;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public int getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(int avgPrice) {
        this.avgPrice = avgPrice;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public AvailableState getAvailableState() {
        return availableState;
    }

    public void setAvailableState(AvailableState availableState) {
        this.availableState = availableState;
    }

    public boolean isUpdated() {
        return updated;
    }

    public void setUpdated(boolean updated) {
        this.updated = updated;
    }

    public String getPoiId() {
        return poiId;
    }

    public void setPoiId(String poiId) {
        this.poiId = poiId;
    }

    public static class Photo {

        @JsonProperty("photo_url")
        private String url;

        public Photo() {
        }

        public Photo(String url) {
            this.url = url;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}

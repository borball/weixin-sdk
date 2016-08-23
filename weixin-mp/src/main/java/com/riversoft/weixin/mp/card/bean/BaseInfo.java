package com.riversoft.weixin.mp.card.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.riversoft.weixin.common.util.DateSerializer;

import java.util.Date;

/**
 * 基本的卡券数据，所有卡券类型通用
 * <p/>
 * Created by exizhai on 11/27/2015.
 */
public class BaseInfo {

    /**
     * 卡券ID，创建的时候无，读取的时候有
     */
    private String id;

    /**
     * 状态
     * CARD_STATUS_NOT_VERIFY”,待审核；
     * “CARD_STATUS_VERIFY_FALL”,审核失败；
     * “CARD_STATUS_VERIFY_OK”，通过审核；
     * “CARD_STATUS_USER_DELETE”，卡券被用户删除；
     * “CARD_STATUS_USER_DISPATCH”，在公众平台投放过的卡券
     */
    private String status;

    /**
     * 卡券的商户logo，建议像素为300*300
     */
    @JsonProperty("logo_url")
    private String logoUrl;

    /**
     * 商户名字,字数上限为12个汉字
     */
    @JsonProperty("brand_name")
    private String brandName;

    /**
     * Code展示类型，
     * "CODE_TYPE_TEXT"，文本；
     * "CODE_TYPE_BARCODE"，一维码 ；
     * "CODE_TYPE_QRCODE"，二维码；
     * "CODE_TYPE_ONLY_QRCODE",二维码无code显示；
     * "CODE_TYPE_ONLY_BARCODE",一维码无code显示；
     */
    @JsonProperty("code_type")
    private String codeType;

    /**
     * 卡券名，字数上限为9个汉字。(建议涵盖卡券属性、服务及金额)
     */
    private String title;

    /**
     * 券名，字数上限为18个汉字
     */
    @JsonProperty("sub_title")
    private String subTitle;

    /**
     * 券颜色。按色彩规范标注填写Color010-Color100
     */
    private String color;

    /**
     * 卡券使用提醒，字数上限为16个汉字
     */
    private String notice;

    /**
     * 客服电话
     */
    @JsonProperty("service_phone")
    private String servicePhone;

    /**
     * 卡券使用说明，字数上限为1024个汉字
     */
    private String description;

    /**
     * 时间信息
     */
    @JsonProperty("date_info")
    private DateInfo dateInfo;

    /**
     * 商品信息
     */
    private Sku sku;

    /**
     * 每人可领券的数量限制
     */
    @JsonProperty("get_limit")
    private int getLimit;

    /**
     * 卡券领取页面是否可分享
     */
    @JsonProperty("can_share")
    private boolean canShare;

    /**
     * 是否自定义Code码。填写true或false，默认为false。通常自有优惠码系统的开发者选择自定义Code码，在卡券投放时带入
     */
    @JsonProperty("use_custom_code")
    private boolean useCustomCode;

    /**
     * 是否指定用户领取，填写true或false。默认为false
     */
    @JsonProperty("bind_openid")
    private boolean bindOpenId;

    /**
     * 卡券是否可转赠
     */
    @JsonProperty("can_give_friend")
    private boolean canGiveFriend;

    /**
     * 机场位置ID, 调用POI门店管理接口获取门店位置ID
     */
    @JsonProperty("location_id_list")
    private String[] locationIds;

    /**
     * 第三方来源名，例如同程旅游、大众点评
     */
    private String source;

    /**
     * 自定义跳转外链的入口名字
     */
    @JsonProperty("custom_url_name")
    private String customUrlName;

    /**
     * 自定义跳转的URL
     */
    @JsonProperty("custom_url")
    private String customUrl;

    /**
     * 显示在入口右侧的提示语
     */
    @JsonProperty("custom_url_sub_title")
    private String customUrlSubTitle;

    /**
     * 营销场景的自定义入口名称
     */
    @JsonProperty("promotion_url_name")
    private String promotionUrlName;

    /**
     * 入口跳转外链的地址链接
     */
    @JsonProperty("promotion_url")
    private String promotionUrl;

    /**
     * 显示在营销入口右侧的提示语
     */
    @JsonProperty("promotion_url_sub_title")
    private String promotionUrlSubTitle;

    /**
     * 填写true为用户点击进入会员卡时推送事件，默认为false
     */
    @JsonProperty("need_push_on_view")
    private boolean needPushOnView;

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getCodeType() {
        return codeType;
    }

    public void setCodeType(String codeType) {
        this.codeType = codeType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public String getServicePhone() {
        return servicePhone;
    }

    public void setServicePhone(String servicePhone) {
        this.servicePhone = servicePhone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DateInfo getDateInfo() {
        return dateInfo;
    }

    public void setDateInfo(DateInfo dateInfo) {
        this.dateInfo = dateInfo;
    }

    public Sku getSku() {
        return sku;
    }

    public void setSku(Sku sku) {
        this.sku = sku;
    }

    public int getGetLimit() {
        return getLimit;
    }

    public void setGetLimit(int getLimit) {
        this.getLimit = getLimit;
    }

    public boolean isCanShare() {
        return canShare;
    }

    public void setCanShare(boolean canShare) {
        this.canShare = canShare;
    }

    public boolean isUseCustomCode() {
        return useCustomCode;
    }

    public void setUseCustomCode(boolean useCustomCode) {
        this.useCustomCode = useCustomCode;
    }

    public boolean isBindOpenId() {
        return bindOpenId;
    }

    public void setBindOpenId(boolean bindOpenId) {
        this.bindOpenId = bindOpenId;
    }

    public boolean isCanGiveFriend() {
        return canGiveFriend;
    }

    public void setCanGiveFriend(boolean canGiveFriend) {
        this.canGiveFriend = canGiveFriend;
    }

    public String[] getLocationIds() {
        return locationIds;
    }

    public void setLocationIds(String[] locationIds) {
        this.locationIds = locationIds;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getCustomUrlName() {
        return customUrlName;
    }

    public void setCustomUrlName(String customUrlName) {
        this.customUrlName = customUrlName;
    }

    public String getCustomUrl() {
        return customUrl;
    }

    public void setCustomUrl(String customUrl) {
        this.customUrl = customUrl;
    }

    public String getCustomUrlSubTitle() {
        return customUrlSubTitle;
    }

    public void setCustomUrlSubTitle(String customUrlSubTitle) {
        this.customUrlSubTitle = customUrlSubTitle;
    }

    public String getPromotionUrlName() {
        return promotionUrlName;
    }

    public void setPromotionUrlName(String promotionUrlName) {
        this.promotionUrlName = promotionUrlName;
    }

    public String getPromotionUrl() {
        return promotionUrl;
    }

    public void setPromotionUrl(String promotionUrl) {
        this.promotionUrl = promotionUrl;
    }

    public String getPromotionUrlSubTitle() {
        return promotionUrlSubTitle;
    }

    public void setPromotionUrlSubTitle(String promotionUrlSubTitle) {
        this.promotionUrlSubTitle = promotionUrlSubTitle;
    }

    public boolean isNeedPushOnView() {
        return needPushOnView;
    }

    public void setNeedPushOnView(boolean needPushOnView) {
        this.needPushOnView = needPushOnView;
    }

    public static class DateInfo {

        /**
         * 使用时间的类型，仅支持填写1或2。1为固定日期区间，2为固定时长（自领取后按天算）。
         */
        @JsonProperty("type")
        private DateInfoType type;

        /**
         * type为1时专用，表示起用时间。
         * 从1970年1月1日00:00:00至起用时间的秒数，最终需转换为字符串形态传入。（东八区时间，单位为秒）
         */
        @JsonProperty("begin_timestamp")
        @JsonSerialize(using = DateSerializer.class)
        private Date beginTime;

        /**
         * type为1时专用，表示结束时间。
         * 建议设置为截止日期的23:59:59过期。（东八区时间，单位为秒）
         */
        @JsonProperty("end_timestamp")
        @JsonSerialize(using = DateSerializer.class)
        private Date endTime;

        /**
         * type为2时专用，表示自领取后多少天内有效，领取后当天有效填写0。（单位为天）
         */
        @JsonProperty("fixed_term")
        private int fixedTerm;

        /**
         * type为2时专用，表示自领取后多少天开始生效。（单位为天）
         */
        @JsonProperty("fixed_begin_term")
        private int fixedTermBegin;

        public DateInfoType getType() {
            return type;
        }

        public void setType(DateInfoType type) {
            this.type = type;
        }

        public Date getBeginTime() {
            return beginTime;
        }

        public void setBeginTime(Date beginTime) {
            this.beginTime = beginTime;
        }

        public Date getEndTime() {
            return endTime;
        }

        public void setEndTime(Date endTime) {
            this.endTime = endTime;
        }

        public int getFixedTerm() {
            return fixedTerm;
        }

        public void setFixedTerm(int fixedTerm) {
            this.fixedTerm = fixedTerm;
        }

        public int getFixedTermBegin() {
            return fixedTermBegin;
        }

        public void setFixedTermBegin(int fixedTermBegin) {
            this.fixedTermBegin = fixedTermBegin;
        }

        public enum DateInfoType {

            DATE_TYPE_FIX_TIME_RANGE(1), DATE_TYPE_FIX_TERM(2);

            private int code;

            DateInfoType(int code) {
                this.code = code;
            }

            public static DateInfoType fromCode(int code) {
                for (DateInfoType dateInfoType : DateInfoType.values()) {
                    if (dateInfoType.code == code) {
                        return dateInfoType;
                    }
                }
                return DateInfoType.DATE_TYPE_FIX_TERM;
            }

            public int getCode() {
                return this.code;
            }
        }
    }

    /**
     * 商品信息
     */
    public static class Sku {

        /**
         * 数量
         */
        private int quantity;

        @JsonProperty("total_quantity")
        private int totalQuantity;

        public Sku() {
        }

        public Sku(int quantity) {
            this.quantity = quantity;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public int getTotalQuantity() {
            return totalQuantity;
        }

        public void setTotalQuantity(int totalQuantity) {
            this.totalQuantity = totalQuantity;
        }
    }
}

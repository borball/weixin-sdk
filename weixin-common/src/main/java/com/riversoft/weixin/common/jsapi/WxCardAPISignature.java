package com.riversoft.weixin.common.jsapi;

/**
 * @borball on 2/28/2017.
 */
public class WxCardAPISignature {

    /**
     * 是否是拉起卡券接口，
     * 两种API可以支持：拉起卡券和添加到卡包
     *
     * 拉起卡券接口appID会参与签名
     */
    private boolean chooseCard;

    private long timestamp;
    private String nonce;

    /**
     * 必填
     */
    private String cardId;

    /** 拉起卡券
     */
    private String locationId;

    /** 拉起卡券
     */
    private String cardType;

    /**
     * 添加到卡包:自定义code
     */
    private String code;

    /**
     * 添加到卡包:指定用户领取
     */
    private String openId;

    /**
     * 添加到卡包:红包类型卡券
     */
    private String balance;

    private String signature;

    public boolean isChooseCard() {
        return chooseCard;
    }

    public void setChooseCard(boolean chooseCard) {
        this.chooseCard = chooseCard;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}

package com.riversoft.weixin.mp.card.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

/**
 * 会员卡
 * Created by exizhai on 11/27/2015.
 */
public class Member extends AbstractCard {

    @JsonProperty("prerogative")
    private String prerogative;

    @JsonProperty("auto_activate")
    private boolean autoActivate;

    @JsonProperty("wx_activate")
    private boolean wxActivate;

    @JsonProperty("supply_bonus")
    private boolean supplyBonus;

    @JsonProperty("bonus_url")
    private String bonusUrl;

    @JsonProperty("supply_balance")
    private boolean supplyBalance;

    @JsonProperty("balance_url")
    private String balanceUrl;

    @JsonProperty("custom_field1")
    private Map<String, Object> customField1;

    @JsonProperty("custom_field2")
    private Map<String, Object> customField2;

    @JsonProperty("custom_field3")
    private Map<String, Object> customField3;

    @JsonProperty("bonus_cleared")
    private boolean bonusClear;

    @JsonProperty("bonus_rules")
    private String bonusRules;

    @JsonProperty("balance_rules")
    private String balanceRules;

    @JsonProperty("activate_url")
    private String activateUrl;

    @JsonProperty("custom_cell1")
    private Map<String, Object> customCell1;

    @JsonProperty("bonus_rule")
    private String bonusRule;

    @JsonProperty("cost_money_unit")
    private int costMoneyUnit;

    @JsonProperty("increase_bonus")
    private int increaseBonus;

    @JsonProperty("max_increase_bonus")
    private int maxIncreaseBonus;

    @JsonProperty("init_increase_bonus")
    private int initIncreaseBonus;

    @JsonProperty("discount")
    private int discount;

    public String getPrerogative() {
        return prerogative;
    }

    public void setPrerogative(String prerogative) {
        this.prerogative = prerogative;
    }

    public boolean isAutoActivate() {
        return autoActivate;
    }

    public void setAutoActivate(boolean autoActivate) {
        this.autoActivate = autoActivate;
    }

    public boolean isWxActivate() {
        return wxActivate;
    }

    public void setWxActivate(boolean wxActivate) {
        this.wxActivate = wxActivate;
    }

    public boolean isSupplyBonus() {
        return supplyBonus;
    }

    public void setSupplyBonus(boolean supplyBonus) {
        this.supplyBonus = supplyBonus;
    }

    public String getBonusUrl() {
        return bonusUrl;
    }

    public void setBonusUrl(String bonusUrl) {
        this.bonusUrl = bonusUrl;
    }

    public boolean isSupplyBalance() {
        return supplyBalance;
    }

    public void setSupplyBalance(boolean supplyBalance) {
        this.supplyBalance = supplyBalance;
    }

    public String getBalanceUrl() {
        return balanceUrl;
    }

    public void setBalanceUrl(String balanceUrl) {
        this.balanceUrl = balanceUrl;
    }

    public Map<String, Object> getCustomField1() {
        return customField1;
    }

    public void setCustomField1(Map<String, Object> customField1) {
        this.customField1 = customField1;
    }

    public Map<String, Object> getCustomField2() {
        return customField2;
    }

    public void setCustomField2(Map<String, Object> customField2) {
        this.customField2 = customField2;
    }

    public Map<String, Object> getCustomField3() {
        return customField3;
    }

    public void setCustomField3(Map<String, Object> customField3) {
        this.customField3 = customField3;
    }

    public boolean isBonusClear() {
        return bonusClear;
    }

    public void setBonusClear(boolean bonusClear) {
        this.bonusClear = bonusClear;
    }

    public String getBonusRules() {
        return bonusRules;
    }

    public void setBonusRules(String bonusRules) {
        this.bonusRules = bonusRules;
    }

    public String getBalanceRules() {
        return balanceRules;
    }

    public void setBalanceRules(String balanceRules) {
        this.balanceRules = balanceRules;
    }

    public String getActivateUrl() {
        return activateUrl;
    }

    public void setActivateUrl(String activateUrl) {
        this.activateUrl = activateUrl;
    }

    public Map<String, Object> getCustomCell1() {
        return customCell1;
    }

    public void setCustomCell1(Map<String, Object> customCell1) {
        this.customCell1 = customCell1;
    }

    public String getBonusRule() {
        return bonusRule;
    }

    public void setBonusRule(String bonusRule) {
        this.bonusRule = bonusRule;
    }

    public int getCostMoneyUnit() {
        return costMoneyUnit;
    }

    public void setCostMoneyUnit(int costMoneyUnit) {
        this.costMoneyUnit = costMoneyUnit;
    }

    public int getIncreaseBonus() {
        return increaseBonus;
    }

    public void setIncreaseBonus(int increaseBonus) {
        this.increaseBonus = increaseBonus;
    }

    public int getMaxIncreaseBonus() {
        return maxIncreaseBonus;
    }

    public void setMaxIncreaseBonus(int maxIncreaseBonus) {
        this.maxIncreaseBonus = maxIncreaseBonus;
    }

    public int getInitIncreaseBonus() {
        return initIncreaseBonus;
    }

    public void setInitIncreaseBonus(int initIncreaseBonus) {
        this.initIncreaseBonus = initIncreaseBonus;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

}

package com.riversoft.weixin.common.menu;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 个性化菜单
 * @borball on 6/7/2016.
 */
public class RuleMenu extends Menu {

    @JsonProperty("matchrule")
    private Rule rule;

    public Rule getRule() {
        return rule;
    }

    public void setRule(Rule rule) {
        this.rule = rule;
    }

    @JsonProperty("menuid")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RuleMenu rule(Rule rule) {
        this.setRule(rule);
        return this;
    }
}

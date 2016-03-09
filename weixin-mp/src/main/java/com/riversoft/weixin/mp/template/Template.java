package com.riversoft.weixin.mp.template;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @borball on 3/8/2016.
 */
public class Template {

    @JsonProperty("template_id")
    private String templateId;
    private String title;

    @JsonProperty("primary_industry")
    private String primaryIndustry;

    @JsonProperty("deputy_industry")
    private String secondaryIndustry;

    private String content;
    private String example;

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrimaryIndustry() {
        return primaryIndustry;
    }

    public void setPrimaryIndustry(String primaryIndustry) {
        this.primaryIndustry = primaryIndustry;
    }

    public String getSecondaryIndustry() {
        return secondaryIndustry;
    }

    public void setSecondaryIndustry(String secondaryIndustry) {
        this.secondaryIndustry = secondaryIndustry;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }
}

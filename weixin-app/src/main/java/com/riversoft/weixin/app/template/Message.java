package com.riversoft.weixin.app.template;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

/**
 * @borball on 11/7/2016.
 */
public class Message {

    @JsonProperty("touser")
    private String toUser;

    @JsonProperty("template_id")
    private String templateId;

    private String page;

    @JsonProperty("form_id")
    private String formId;

    private Map<String, Data> data;

    @JsonProperty("emphasis_keyword")
    private String highlight;

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public Map<String, Data> getData() {
        return data;
    }

    public void setData(Map<String, Data> data) {
        this.data = data;
    }

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    public String getHighlight() {
        return highlight;
    }

    public void setHighlight(String highlight) {
        this.highlight = highlight;
    }

    /**
     * Created by exizhai on 12/16/2015.
     */
    public static class Data {

        private String value;
        private String color;

        public Data() {
        }

        public Data(String value, String color) {
            this.value = value;
            this.color = color;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }
    }
}

package com.riversoft.weixin.mp.template;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @borball on 4/11/2017.
 */
public class MiniProgram {

    @JsonProperty("appid")
    private String appId;

    @JsonProperty("pagepath")
    private String pagePath;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getPagePath() {
        return pagePath;
    }

    public void setPagePath(String pagePath) {
        this.pagePath = pagePath;
    }
}

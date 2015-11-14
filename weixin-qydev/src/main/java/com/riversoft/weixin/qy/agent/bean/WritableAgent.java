package com.riversoft.weixin.qy.agent.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.riversoft.weixin.common.util.BooleanDeserializer;
import com.riversoft.weixin.common.util.BooleanSerializer;
import com.riversoft.weixin.qy.util.ReportLocationDeserializer;
import com.riversoft.weixin.qy.util.ReportLocationSerializer;

import java.io.Serializable;

/**
 * Created by exizhai on 9/27/2015.
 */
public class WritableAgent implements Serializable {

    @JsonProperty("agentid")
    private int agentId;
    private String name;
    private String description;
    @JsonProperty("logo_mediaid")
    private String logoMediaId;
    @JsonProperty("redirect_domain")
    private String redirectDomain;
    @JsonSerialize(using = ReportLocationSerializer.class)
    @JsonDeserialize(using = ReportLocationDeserializer.class)
    @JsonProperty("report_location_flag")
    private ReportLocation reportLocationFlag;
    @JsonSerialize(using = BooleanSerializer.class)
    @JsonDeserialize(using = BooleanDeserializer.class)
    @JsonProperty("isreportuser")
    private boolean reportUserChange;
    @JsonSerialize(using = BooleanSerializer.class)
    @JsonDeserialize(using = BooleanDeserializer.class)
    @JsonProperty("isreportenter")
    private boolean reportUserEnter;

    public int getAgentId() {
        return agentId;
    }

    public void setAgentId(int agentId) {
        this.agentId = agentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogoMediaId() {
        return logoMediaId;
    }

    public void setLogoMediaId(String logoMediaId) {
        this.logoMediaId = logoMediaId;
    }

    public String getRedirectDomain() {
        return redirectDomain;
    }

    public void setRedirectDomain(String redirectDomain) {
        this.redirectDomain = redirectDomain;
    }

    public ReportLocation getReportLocationFlag() {
        return reportLocationFlag;
    }

    public void setReportLocationFlag(ReportLocation reportLocationFlag) {
        this.reportLocationFlag = reportLocationFlag;
    }

    public boolean isReportUserChange() {
        return reportUserChange;
    }

    public void setReportUserChange(boolean reportUserChange) {
        this.reportUserChange = reportUserChange;
    }

    public boolean isReportUserEnter() {
        return reportUserEnter;
    }

    public void setReportUserEnter(boolean reportUserEnter) {
        this.reportUserEnter = reportUserEnter;
    }

    public enum ReportLocation {

        NO, ONLY_IN_SESSION, ALWAYS

    }
}

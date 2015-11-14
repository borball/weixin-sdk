package com.riversoft.weixin.qy.contact.job;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

/**
 * Created by exizhai on 10/7/2015.
 */
public class JobResult {

    private int status;
    private JobType type;
    private int total;
    private int percentage;
    @JsonProperty("remaintime")
    private int remainTime;
    private List<Map<String, Object>> result;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public JobType getType() {
        return type;
    }

    public void setType(JobType type) {
        this.type = type;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public int getRemainTime() {
        return remainTime;
    }

    public void setRemainTime(int remainTime) {
        this.remainTime = remainTime;
    }

    public List<Map<String, Object>> getResult() {
        return result;
    }

    public void setResult(List<Map<String, Object>> result) {
        this.result = result;
    }

    /**
     * Created by exizhai on 10/7/2015.
     */
    public enum JobType {

        sync_user, replace_user, invite_user, replace_party

    }
}

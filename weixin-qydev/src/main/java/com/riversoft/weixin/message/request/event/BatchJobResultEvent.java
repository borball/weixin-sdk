package com.riversoft.weixin.message.request.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;

/**
 * Created by exizhai on 9/30/2015.
 */
public class BatchJobResultEvent extends EventXmlRequest {

    @JsonProperty("BatchJob")
    private BatchJob batchJob;

    public BatchJob getBatchJob() {
        return batchJob;
    }

    public void setBatchJob(BatchJob batchJob) {
        this.batchJob = batchJob;
    }

    public enum JobType {
        sync_user, replace_user, invite_user, replace_party
    }

    public static class BatchJob {

        @JsonProperty("JobId")
        @JacksonXmlCData
        private String jobId;

        @JsonProperty("JobType")
        @JacksonXmlCData
        private JobType jobType;

        @JsonProperty("ErrCode")
        private String errorCode;

        @JsonProperty("ErrMsg")
        @JacksonXmlCData
        private String errorMessage;

        public String getJobId() {
            return jobId;
        }

        public void setJobId(String jobId) {
            this.jobId = jobId;
        }

        public JobType getJobType() {
            return jobType;
        }

        public void setJobType(JobType jobType) {
            this.jobType = jobType;
        }

        public String getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(String errorCode) {
            this.errorCode = errorCode;
        }

        public String getErrorMessage() {
            return errorMessage;
        }

        public void setErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
        }
    }
}

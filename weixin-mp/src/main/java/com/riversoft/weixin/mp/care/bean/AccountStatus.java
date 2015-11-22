package com.riversoft.weixin.mp.care.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by exizhai on 11/22/2015.
 */
public class AccountStatus {

    @JsonProperty("kf_id")
    private String id;

    @JsonProperty("kf_account")
    private String account;
    private Status status;

    @JsonProperty("auto_accept")
    private int autoAccept;

    @JsonProperty("accepted_case")
    private int accepted;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getAutoAccept() {
        return autoAccept;
    }

    public void setAutoAccept(int autoAccept) {
        this.autoAccept = autoAccept;
    }

    public int getAccepted() {
        return accepted;
    }

    public void setAccepted(int accepted) {
        this.accepted = accepted;
    }

    public static enum Status {
        PC(1), MOBILE(2), ALL(3), UNKNOWN(-1);

        private int code;

        Status(int code) {
            this.code = code;
        }

        public static Status fromCode(int code) {
            for (Status status : Status.values()) {
                if (status.code == code) {
                    return status;
                }
            }
            return Status.UNKNOWN;
        }

        public int getCode() {
            return this.code;
        }
    }

}

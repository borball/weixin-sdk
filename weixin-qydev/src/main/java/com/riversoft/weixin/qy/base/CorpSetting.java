package com.riversoft.weixin.qy.base;

/**
 * Created by exizhai on 9/26/2015.
 */
public class CorpSetting {

    private String corpId;
    private String corpSecret;

    public CorpSetting() {
    }

    public CorpSetting(String corpId, String corpSecret) {
        this.corpId = corpId;
        this.corpSecret = corpSecret;
    }

    public String getCorpId() {
        return corpId;
    }

    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    public String getCorpSecret() {
        return corpSecret;
    }

    public void setCorpSecret(String corpSecret) {
        this.corpSecret = corpSecret;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CorpSetting that = (CorpSetting) o;

        if (!corpId.equals(that.corpId)) return false;
        return corpSecret.equals(that.corpSecret);

    }

    @Override
    public int hashCode() {
        int result = corpId.hashCode();
        result = 31 * result + corpSecret.hashCode();
        return result;
    }
}

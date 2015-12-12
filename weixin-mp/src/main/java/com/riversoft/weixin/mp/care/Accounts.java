package com.riversoft.weixin.mp.care;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.riversoft.weixin.common.WxClient;
import com.riversoft.weixin.common.util.JsonMapper;
import com.riversoft.weixin.mp.MpWxClientFactory;
import com.riversoft.weixin.mp.base.AppSetting;
import com.riversoft.weixin.mp.base.WxEndpoint;
import com.riversoft.weixin.mp.care.bean.Account;
import com.riversoft.weixin.mp.care.bean.AccountStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 客服账号管理
 * Created by exizhai on 11/22/2015.
 */
public class Accounts {

    private static Logger logger = LoggerFactory.getLogger(Accounts.class);

    private WxClient wxClient;

    public static Accounts defaultAccounts() {
        return with(AppSetting.defaultSettings());
    }

    public static Accounts with(AppSetting appSetting) {
        Accounts accounts = new Accounts();
        accounts.setWxClient(MpWxClientFactory.getInstance().with(appSetting));
        return accounts;
    }

    public void setWxClient(WxClient wxClient) {
        this.wxClient = wxClient;
    }

    public void add(String account, String nickName, String password) {
        String url = WxEndpoint.get("url.care.account.add");
        Map<String, String> request = new HashMap<>();
        request.put("kf_account", account);
        request.put("nickname", nickName);
        request.put("password", password);

        String json = JsonMapper.nonEmptyMapper().toJson(request);
        logger.debug("add care account: {}", json);
        wxClient.post(url, json);
    }

    public void update(String account, String nickName, String password) {
        String url = WxEndpoint.get("url.care.account.update");
        Map<String, String> request = new HashMap<>();
        request.put("kf_account", account);
        request.put("nickname", nickName);
        request.put("password", password);

        String json = JsonMapper.nonEmptyMapper().toJson(request);
        logger.debug("add care account: {}", json);
        wxClient.post(url, json);
    }

    public void delete(String account) {
        String url = WxEndpoint.get("url.care.account.delete");
        logger.debug("delete care account: {}", account);
        wxClient.get(String.format(url, account));
    }

    public void uploadHeadImage(String account, InputStream inputStream, String fileName) {
        String url = WxEndpoint.get("url.care.account.uploadhead");
        logger.debug("upload care account :{} head image: {}", account, fileName);
        wxClient.post(String.format(url, account), inputStream, fileName);
    }

    public List<Account> list() {
        String url = WxEndpoint.get("url.care.account.list");
        String response = wxClient.get(url);
        logger.debug("list care accounts :{}", response);
        CareListResponse careListResponse = JsonMapper.defaultMapper().fromJson(response, CareListResponse.class);
        return careListResponse.getList();
    }

    public List<AccountStatus> listStatus() {
        String url = WxEndpoint.get("url.care.account.liststatus");
        String response = wxClient.get(url);
        logger.debug("list care account status :{}", response);
        CareListStatusResponse careListStatusResponse = JsonMapper.defaultMapper().fromJson(response, CareListStatusResponse.class);
        return careListStatusResponse.getList();
    }

    public static class CareListResponse {

        @JsonProperty("kf_list")
        private List<Account> list;

        public List<Account> getList() {
            return list;
        }

        public void setList(List<Account> list) {
            this.list = list;
        }
    }

    public static class CareListStatusResponse {

        @JsonProperty("kf_online_list")
        private List<AccountStatus> list;

        public List<AccountStatus> getList() {
            return list;
        }

        public void setList(List<AccountStatus> list) {
            this.list = list;
        }
    }
}

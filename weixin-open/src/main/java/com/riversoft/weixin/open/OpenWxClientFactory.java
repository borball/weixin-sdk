package com.riversoft.weixin.open;

import com.riversoft.weixin.common.WxClient;
import com.riversoft.weixin.open.base.AppSetting;
import com.riversoft.weixin.open.base.WxEndpoint;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by exizhai on 11/12/2015.
 */
public class OpenWxClientFactory {

    private static OpenWxClientFactory instance = null;
    private static ConcurrentHashMap<String, WxClient> wxClients = new ConcurrentHashMap<>();

    private OpenWxClientFactory() {
    }

    public synchronized static OpenWxClientFactory getInstance() {
        if (instance == null) {
            instance = new OpenWxClientFactory();
        }
        return instance;
    }

    public WxClient defaultWxClient() {
        return with(AppSetting.defaultSettings());
    }

    public WxClient with(AppSetting appSetting) {
        if (!wxClients.containsKey(key(appSetting))) {
            String url = WxEndpoint.get("url.token.get");
            WxClient wxClient = new WxClient(url, appSetting.getAppId(), appSetting.getSecret());
            wxClients.putIfAbsent(key(appSetting), wxClient);
        }

        return wxClients.get(key(appSetting));
    }

    private String key(AppSetting appSetting) {
        return appSetting.getAppId() + ":" + appSetting.getSecret();
    }
}


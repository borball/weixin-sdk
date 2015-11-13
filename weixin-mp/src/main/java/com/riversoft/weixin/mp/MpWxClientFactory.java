package com.riversoft.weixin.mp;

import com.riversoft.weixin.common.WxClient;
import com.riversoft.weixin.mp.base.AppSetting;
import com.riversoft.weixin.mp.base.WxEndpoint;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by exizhai on 11/12/2015.
 */public class MpWxClientFactory {

    private static MpWxClientFactory instance = null;
    private static ConcurrentHashMap<AppSetting, WxClient> wxClients = new ConcurrentHashMap<>();

    public static MpWxClientFactory getInstance(){
        if(instance == null) {
            instance = new MpWxClientFactory();
        }
        return instance;
    }

    public WxClient defaultWxClient(){
        return with(AppSetting.defaultSettings());
    }

    public WxClient with(AppSetting appSetting) {
        if(!wxClients.containsKey(appSetting)) {
            String url = WxEndpoint.get("url.token.get");
            WxClient wxClient = new WxClient(url, appSetting.getAppId(), appSetting.getSecret());
            wxClients.putIfAbsent(appSetting, wxClient);
        }

        return wxClients.get(appSetting);
    }

}


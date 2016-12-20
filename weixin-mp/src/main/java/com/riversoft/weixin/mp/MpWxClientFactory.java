package com.riversoft.weixin.mp;

import com.riversoft.weixin.common.AccessTokenHolder;
import com.riversoft.weixin.common.DefaultAccessTokenHolder;
import com.riversoft.weixin.common.WxClient;
import com.riversoft.weixin.mp.base.AppSetting;
import com.riversoft.weixin.mp.base.WxEndpoint;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by exizhai on 11/12/2015.
 */
public class MpWxClientFactory {

    private static MpWxClientFactory instance = null;
    private static ConcurrentHashMap<String, WxClient> wxClients = new ConcurrentHashMap<>();

    private MpWxClientFactory() {
    }

    public synchronized static MpWxClientFactory getInstance() {
        if (instance == null) {
            instance = new MpWxClientFactory();
        }
        return instance;
    }

    public WxClient defaultWxClient() {
        return with(AppSetting.defaultSettings());
    }

    public WxClient with(AppSetting appSetting) {
        if (!wxClients.containsKey(key(appSetting))) {
            String url = WxEndpoint.get("url.token.get");
            String clazz = appSetting.getTokenHolderClass();

            AccessTokenHolder accessTokenHolder = null;
            if(clazz == null || "".equals(clazz)) {
                accessTokenHolder = new DefaultAccessTokenHolder(url, appSetting.getAppId(), appSetting.getSecret());
            } else {
                try {
                    accessTokenHolder = (AccessTokenHolder)Class.forName(clazz).newInstance();
                    accessTokenHolder.setClientId(appSetting.getAppId());
                    accessTokenHolder.setClientSecret(appSetting.getSecret());
                    accessTokenHolder.setTokenUrl(url);
                } catch (Exception e) {
                    accessTokenHolder = new DefaultAccessTokenHolder(url, appSetting.getAppId(), appSetting.getSecret());
                }
            }

            WxClient wxClient = new WxClient(appSetting.getAppId(), appSetting.getSecret(), accessTokenHolder);
            wxClients.putIfAbsent(key(appSetting), wxClient);
        }

        return wxClients.get(key(appSetting));
    }

    private String key(AppSetting appSetting) {
        return appSetting.getAppId() + ":" + appSetting.getSecret();
    }
}


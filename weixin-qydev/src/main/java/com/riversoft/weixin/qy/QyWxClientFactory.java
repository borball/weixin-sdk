package com.riversoft.weixin.qy;

import com.google.common.cache.CacheBuilder;
import com.riversoft.weixin.common.AccessTokenHolder;
import com.riversoft.weixin.common.DefaultAccessTokenHolder;
import com.riversoft.weixin.common.WxClient;
import com.riversoft.weixin.qy.base.CorpSetting;
import com.riversoft.weixin.qy.base.WxEndpoint;

import java.util.concurrent.ConcurrentMap;

/**
 * Created by exizhai on 11/12/2015.
 */
public class QyWxClientFactory {

    private static QyWxClientFactory instance = null;
    private static ConcurrentMap<String, WxClient> wxClients = CacheBuilder.newBuilder().initialCapacity(8)
            .maximumSize(128).<String, WxClient>build().asMap();

    private QyWxClientFactory(){
    }

    public synchronized static QyWxClientFactory getInstance() {
        if (instance == null) {
            instance = new QyWxClientFactory();
        }
        return instance;
    }

    public WxClient defaultWxClient() {
        return with(CorpSetting.defaultSettings());
    }

    public WxClient with(CorpSetting corpSetting) {
        if (!wxClients.containsKey(key(corpSetting))) {
            String url = WxEndpoint.get("url.token.get");
            String clazz = corpSetting.getTokenHolderClass();

            AccessTokenHolder accessTokenHolder = null;
            if(clazz == null || "".equals(clazz)) {
                accessTokenHolder = new DefaultAccessTokenHolder(url, corpSetting.getCorpId(), corpSetting.getCorpSecret());
            } else {
                try {
                    accessTokenHolder = (AccessTokenHolder)Class.forName(clazz).newInstance();
                    accessTokenHolder.setClientId(corpSetting.getCorpId());
                    accessTokenHolder.setClientSecret(corpSetting.getCorpSecret());
                    accessTokenHolder.setTokenUrl(url);
                } catch (Exception e) {
                    accessTokenHolder = new DefaultAccessTokenHolder(url, corpSetting.getCorpId(), corpSetting.getCorpSecret());
                }
            }

            WxClient wxClient = new WxClient(corpSetting.getCorpId(), corpSetting.getCorpSecret(), accessTokenHolder);
            wxClients.putIfAbsent(key(corpSetting), wxClient);
        }

        return wxClients.get(key(corpSetting));
    }

    private String key(CorpSetting corpSetting) {
        return corpSetting.getCorpId() + ":" + corpSetting.getCorpSecret();
    }
}


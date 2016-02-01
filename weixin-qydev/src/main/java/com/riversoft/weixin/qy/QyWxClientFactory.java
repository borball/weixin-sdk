package com.riversoft.weixin.qy;

import com.riversoft.weixin.common.WxClient;
import com.riversoft.weixin.qy.base.CorpSetting;
import com.riversoft.weixin.qy.base.WxEndpoint;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by exizhai on 11/12/2015.
 */
public class QyWxClientFactory {

    private static QyWxClientFactory instance = null;
    private static ConcurrentHashMap<String, WxClient> wxClients = new ConcurrentHashMap<>();

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
            WxClient wxClient = new WxClient(url, corpSetting.getCorpId(), corpSetting.getCorpSecret());
            wxClients.putIfAbsent(key(corpSetting), wxClient);
        }

        return wxClients.get(key(corpSetting));
    }

    private String key(CorpSetting corpSetting) {
        return corpSetting.getCorpId() + ":" + corpSetting.getCorpSecret();
    }
}


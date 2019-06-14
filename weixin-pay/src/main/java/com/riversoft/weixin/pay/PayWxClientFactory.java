package com.riversoft.weixin.pay;

import com.google.common.cache.CacheBuilder;
import com.riversoft.weixin.common.WxSslClient;
import com.riversoft.weixin.pay.base.PaySetting;

import java.util.concurrent.ConcurrentMap;

/**
 * Created by exizhai on 11/12/2015.
 */
public class PayWxClientFactory {

    private static PayWxClientFactory instance = null;
    private static ConcurrentMap<String, WxSslClient> wxClients = CacheBuilder.newBuilder().initialCapacity(8)
            .maximumSize(128).<String, WxSslClient>build().asMap();

    public static PayWxClientFactory getInstance() {
        if (instance == null) {
            instance = new PayWxClientFactory();
        }
        return instance;
    }

    public WxSslClient defaultWxSslClient() {
        return with(PaySetting.defaultSetting());
    }

    public WxSslClient with(PaySetting paySetting) {
        if (!wxClients.containsKey(key(paySetting))) {
            WxSslClient wxClient = new WxSslClient(paySetting.getCertPath(), paySetting.getCertPassword());
            wxClients.putIfAbsent(key(paySetting), wxClient);
        }

        return wxClients.get(key(paySetting));
    }


    private String key(PaySetting paySetting) {
        return paySetting.getAppId() + ":" + paySetting.getMchId();
    }
}


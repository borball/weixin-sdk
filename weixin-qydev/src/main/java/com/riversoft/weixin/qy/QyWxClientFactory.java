package com.riversoft.weixin.qy;

import com.riversoft.weixin.common.WxClient;
import com.riversoft.weixin.qy.base.CorpSetting;
import com.riversoft.weixin.qy.base.DefaultSettings;
import com.riversoft.weixin.qy.base.WxEndpoint;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by exizhai on 11/12/2015.
 */
public class QyWxClientFactory {

    private static QyWxClientFactory instance = null;
    private static ConcurrentHashMap<CorpSetting, WxClient> wxClients = new ConcurrentHashMap<>();

    public static QyWxClientFactory getInstance() {
        if (instance == null) {
            instance = new QyWxClientFactory();
        }
        return instance;
    }

    public WxClient defaultWxClient() {
        return with(DefaultSettings.defaultSettings().getCorpSetting());
    }

    public WxClient with(CorpSetting corpSetting) {
        if (!wxClients.containsKey(corpSetting)) {
            String url = WxEndpoint.get("url.token.get");
            WxClient wxClient = new WxClient(url, corpSetting.getCorpId(), corpSetting.getCorpSecret());
            wxClients.putIfAbsent(corpSetting, wxClient);
        }

        return wxClients.get(corpSetting);
    }

}


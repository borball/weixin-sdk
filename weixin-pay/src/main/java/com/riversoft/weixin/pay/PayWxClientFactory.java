package com.riversoft.weixin.pay;

import com.riversoft.weixin.common.WxClient;
import com.riversoft.weixin.common.WxSslClient;
import com.riversoft.weixin.pay.base.PaySetting;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by exizhai on 11/12/2015.
 */
public class PayWxClientFactory {

    private static PayWxClientFactory instance = null;
    private static ConcurrentHashMap<PaySetting, WxSslClient> wxClients = new ConcurrentHashMap<>();

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
        if (!wxClients.containsKey(paySetting)) {
            WxSslClient wxClient = new WxSslClient(paySetting.getCertPath(), paySetting.getCertPassword());
            wxClients.putIfAbsent(paySetting, wxClient);
        }

        return wxClients.get(paySetting);
    }

}


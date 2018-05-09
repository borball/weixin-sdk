package com.riversoft.weixin.pay;

import com.riversoft.weixin.common.WxSslClient;
import com.riversoft.weixin.pay.base.PaySetting;
import com.riversoft.weixin.common.cert.CertContent;

import java.io.InputStream;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by exizhai on 11/12/2015.
 */
public class PayWxClientFactory {

    private static PayWxClientFactory instance = null;
    private static ConcurrentHashMap<String, WxSslClient> wxClients = new ConcurrentHashMap<>();

    public static PayWxClientFactory getInstance() {
        if (instance == null) {
            instance = new PayWxClientFactory();
        }
        return instance;
    }

    public WxSslClient defaultWxSslClient() {
        PaySetting paySetting = PaySetting.defaultSetting();
        return with(paySetting,paySetting.getCertContent());
    }

    public WxSslClient with(PaySetting paySetting) {
        if (!wxClients.containsKey(key(paySetting))) {
            WxSslClient wxClient = new WxSslClient(paySetting.getCertContent(), paySetting.getCertPassword());
            wxClients.putIfAbsent(key(paySetting), wxClient);
        }

        return wxClients.get(key(paySetting));
    }

    public WxSslClient with(PaySetting paySetting,CertContent certPath) {
        if (!wxClients.containsKey(key(paySetting))) {
            WxSslClient wxClient = new WxSslClient(certPath, paySetting.getCertPassword());
            wxClients.putIfAbsent(key(paySetting), wxClient);
        }

        return wxClients.get(key(paySetting));
    }


    private String key(PaySetting paySetting) {
        return paySetting.getAppId() + ":" + paySetting.getMchId();
    }
}


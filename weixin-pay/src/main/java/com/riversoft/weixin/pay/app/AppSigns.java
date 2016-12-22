package com.riversoft.weixin.pay.app;

import com.riversoft.weixin.common.util.MD5;
import com.riversoft.weixin.common.util.RandomStringGenerator;
import com.riversoft.weixin.pay.base.PaySetting;
import com.riversoft.weixin.pay.payment.bean.Signature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 小程序支付：签名算法
 * @borball on 12/20/2016.
 */
public class AppSigns {

    private static Logger logger = LoggerFactory.getLogger(AppSigns.class);

    private PaySetting paySetting;

    public void setPaySetting(PaySetting paySetting) {
        this.paySetting = paySetting;
    }

    public static AppSigns defaultAppSigns() {
        return with(PaySetting.defaultSetting());
    }

    public static AppSigns with(PaySetting paySetting) {
        AppSigns appSigns = new AppSigns();
        appSigns.setPaySetting(paySetting);
        return appSigns;
    }

    /**
     * 创建供小程序调用的签名
     * @param prepayId
     * @return
     */
    public Signature createSignature(String prepayId) {
        long timestamp = System.currentTimeMillis() / 1000;
        String nonce = RandomStringGenerator.getRandomStringByLength(16);

        String pack = "Sign=WXPay";
        String sortString = String.format("appId=%s&nonceStr=%s&package=%s&partnerid=%s&&prepayid=%s&timeStamp=%s",
                paySetting.getAppId(), nonce, pack, paySetting.getMchId(), prepayId, timestamp);

        String signature = MD5.MD5Encode(sortString + "&key=" + paySetting.getKey()).toUpperCase();
        Signature appSignature = new Signature();
        appSignature.setAppId(paySetting.getAppId());
        appSignature.setNonce(nonce);
        appSignature.setTimestamp(timestamp);
        appSignature.setSignature(signature);
        appSignature.setPack(pack);
        return appSignature;
    }
}

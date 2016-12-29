package com.riversoft.weixin.pay.payment;

import com.riversoft.weixin.common.util.MD5;
import com.riversoft.weixin.common.util.RandomStringGenerator;
import com.riversoft.weixin.pay.base.PaySetting;
import com.riversoft.weixin.pay.payment.bean.Signature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @borball on 12/29/2016.
 */
public class Signatures {

    private static Logger logger = LoggerFactory.getLogger(Signatures.class);

    private PaySetting paySetting;

    public void setPaySetting(PaySetting paySetting) {
        this.paySetting = paySetting;
    }

    public static Signatures defaultSignatures() {
        return with(PaySetting.defaultSetting());
    }

    public static Signatures with(PaySetting paySetting) {
        Signatures signatures = new Signatures();
        signatures.setPaySetting(paySetting);
        return signatures;
    }

    /**
     * 创建供APP调用的签名
     * @param prepayId
     * @return
     */
    public Signature createAppSignature(String prepayId) {
        String pack = "Sign=WXPay";

        long timestamp = System.currentTimeMillis() / 1000;
        String nonce = RandomStringGenerator.getRandomStringByLength(16);
        String appId = paySetting.getAppId();
        String mchId = paySetting.getMchId();
        String key = paySetting.getKey();

        String sortString = String.format("appId=%s&nonceStr=%s&package=%s&partnerid=%s&&prepayid=%s&timeStamp=%s",
                appId, nonce, pack, mchId, prepayId, timestamp);

        return createSignature(pack, timestamp, nonce, appId, key, sortString);
    }

    /**
     * 创建供公众号或者小程序调用的签名
     * @param prepayId
     * @return
     */
    public Signature createJsSignature(String prepayId) {
        String pack = "prepay_id=" + prepayId;

        long timestamp = System.currentTimeMillis() / 1000;
        String nonce = RandomStringGenerator.getRandomStringByLength(16);
        String appId = paySetting.getAppId();
        String key = paySetting.getKey();

        String sortString = String.format("appId=%s&nonceStr=%s&package=%s&signType=MD5&timeStamp=%s",
                appId, nonce, pack, timestamp);

        return createSignature(pack, timestamp, nonce, appId, key, sortString);
    }

    private Signature createSignature(String pack, long timestamp, String nonce, String appId, String key, String sortString) {
        String signature = MD5.md5Hex(sortString + "&key=" + key).toUpperCase();
        Signature sign = new Signature();
        sign.setAppId(appId);
        sign.setNonce(nonce);
        sign.setTimestamp(timestamp);
        sign.setSignature(signature);
        sign.setPack(pack);
        return sign;
    }

}

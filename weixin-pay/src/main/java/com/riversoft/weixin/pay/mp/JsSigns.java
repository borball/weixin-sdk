package com.riversoft.weixin.pay.mp;

import com.riversoft.weixin.common.util.MD5;
import com.riversoft.weixin.common.util.RandomStringGenerator;
import com.riversoft.weixin.pay.base.PaySetting;
import com.riversoft.weixin.pay.mp.bean.JSSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @borball on 6/2/2016.
 */
public class JsSigns {

    private static Logger logger = LoggerFactory.getLogger(JsSigns.class);

    private PaySetting paySetting;

    public void setPaySetting(PaySetting paySetting) {
        this.paySetting = paySetting;
    }

    public static JsSigns defaultJsSigns() {
        return with(PaySetting.defaultSetting());
    }

    public static JsSigns with(PaySetting paySetting) {
        JsSigns jsSigns = new JsSigns();
        jsSigns.setPaySetting(paySetting);
        return jsSigns;
    }

    /**
     * 创建供JS调用的签名
     * @param prepayId
     * @return
     */
    public JSSignature createJsSignature(String prepayId) {
        long timestamp = System.currentTimeMillis() / 1000;
        String nonce = RandomStringGenerator.getRandomStringByLength(16);

        String pack = "prepay_id=" + prepayId;
        String sortString = String.format("appId=%s&nonceStr=%s&package=%s&signType=MD5&timeStamp=%s",
                paySetting.getAppId(), nonce, pack, timestamp);

        String signature = MD5.MD5Encode(sortString + "&key=" + paySetting.getKey()).toUpperCase();
        JSSignature jsSignature = new JSSignature();
        jsSignature.setAppId(paySetting.getAppId());
        jsSignature.setNonce(nonce);
        jsSignature.setTimestamp(timestamp);
        jsSignature.setSignature(signature);
        jsSignature.setPack(pack);
        return jsSignature;
    }
}

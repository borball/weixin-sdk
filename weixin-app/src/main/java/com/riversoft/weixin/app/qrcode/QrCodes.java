package com.riversoft.weixin.app.qrcode;

import com.riversoft.weixin.app.AppWxClientFactory;
import com.riversoft.weixin.app.base.AppSetting;
import com.riversoft.weixin.common.WxClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 二维码工具
 * @borball on 12/29/2016.
 */
public class QrCodes {

    private static Logger logger = LoggerFactory.getLogger(QrCodes.class);

    private WxClient wxClient;

    public static QrCodes defaultQrCodes() {
        return with(AppSetting.defaultSettings());
    }

    public static QrCodes with(AppSetting appSetting) {
        QrCodes qrCodes = new QrCodes();
        qrCodes.setWxClient(AppWxClientFactory.getInstance().with(appSetting));
        return qrCodes;
    }

    public void setWxClient(WxClient wxClient) {
        this.wxClient = wxClient;
    }

}

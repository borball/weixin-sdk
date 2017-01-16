package com.riversoft.weixin.app.qrcode;

import com.riversoft.weixin.app.AppWxClientFactory;
import com.riversoft.weixin.app.base.AppSetting;
import com.riversoft.weixin.app.base.WxEndpoint;
import com.riversoft.weixin.common.WxClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

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

    /**
     * 获取小程序页面二维码
     * @param path, path 需要在 app.json 的 pages 中定义
     * @return
     */
    public InputStream create(String path) {
        return create(path, 430);
    }

    /**
     * 获取小程序页面二维码
     * @param path, path 需要在 app.json 的 pages 中定义
     * @param size
     * @return
     */
    public InputStream create(String path, int size) {
        String url = WxEndpoint.get("url.qrcode.create");
        String json = "{\"path\": \"%s\", \"width\": %s}";
        return wxClient.copyStream(url, String.format(json, path, size));
    }
}

package com.riversoft.weixin.app.template;

import com.riversoft.weixin.app.AppWxClientFactory;
import com.riversoft.weixin.app.base.AppSetting;
import com.riversoft.weixin.app.base.WxEndpoint;
import com.riversoft.weixin.common.WxClient;
import com.riversoft.weixin.common.util.JsonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 模板消息API
 *
 * Created by Borball on 11/07/2016.
 */
public class Templates {

    private static Logger logger = LoggerFactory.getLogger(Templates.class);

    private WxClient wxClient;

    public static Templates defaultTemplates() {
        return with(AppSetting.defaultSettings());
    }

    public static Templates with(AppSetting appSetting) {
        Templates templates = new Templates();
        templates.setWxClient(AppWxClientFactory.getInstance().with(appSetting));
        return templates;
    }

    public void setWxClient(WxClient wxClient) {
        this.wxClient = wxClient;
    }

    /**
     * 发送模板消息
     *
     * @param message
     */
    public void send(Message message) {
        String sendUrl = WxEndpoint.get("url.template.send");
        String json = JsonMapper.defaultMapper().toJson(message);

        logger.debug("template message, send message: {}", json);
        String response = wxClient.post(sendUrl, json);
        JsonMapper.defaultMapper().json2Map(response);
    }

}

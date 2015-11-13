package com.riversoft.weixin.qy.message;

import com.riversoft.weixin.common.WxClient;
import com.riversoft.weixin.qy.QyWxClientFactory;
import com.riversoft.weixin.qy.base.CorpSetting;
import com.riversoft.weixin.qy.base.DefaultSettings;
import com.riversoft.weixin.qy.message.json.JsonMessage;
import com.riversoft.weixin.qy.util.JsonMapper;
import com.riversoft.weixin.qy.base.WxEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by exizhai on 9/26/2015.
 */
public class Messages {

    private static Logger logger = LoggerFactory.getLogger(Messages.class);
    private WxClient wxClient;

    public void setWxClient(WxClient wxClient) {
        this.wxClient = wxClient;
    }

    public static Messages defaultMessages() {
        return with(DefaultSettings.defaultSettings().getCorpSetting());
    }

    public static Messages with(CorpSetting corpSetting) {
        Messages messages = new Messages();
        messages.setWxClient(QyWxClientFactory.getInstance().with(corpSetting));
        return messages;
    }

    public void send(JsonMessage message) {
        if (message.getAgentId() <= 0) {
            message.setAgentId(DefaultSettings.defaultSettings().getDefaultAgent());
        }
        String url = WxEndpoint.get("url.message.send");
        String json = JsonMapper.nonEmptyMapper().toJson(message);
        logger.info("send message: {}", json);
        wxClient.post(url, json);
    }

}

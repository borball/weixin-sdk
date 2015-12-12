package com.riversoft.weixin.qy.message;

import com.riversoft.weixin.common.WxClient;
import com.riversoft.weixin.common.util.JsonMapper;
import com.riversoft.weixin.qy.QyWxClientFactory;
import com.riversoft.weixin.qy.base.CorpSetting;
import com.riversoft.weixin.qy.base.WxEndpoint;
import com.riversoft.weixin.qy.message.json.JsonMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by exizhai on 9/26/2015.
 */
public class Messages {

    private static Logger logger = LoggerFactory.getLogger(Messages.class);
    private WxClient wxClient;

    public static Messages defaultMessages() {
        return with(CorpSetting.defaultSettings());
    }

    public static Messages with(CorpSetting corpSetting) {
        Messages messages = new Messages();
        messages.setWxClient(QyWxClientFactory.getInstance().with(corpSetting));
        return messages;
    }

    public void setWxClient(WxClient wxClient) {
        this.wxClient = wxClient;
    }

    public void send(JsonMessage message) {
        String url = WxEndpoint.get("url.message.send");
        String json = JsonMapper.nonEmptyMapper().toJson(message);
        logger.info("send message: {}", json);
        wxClient.post(url, json);
    }

}

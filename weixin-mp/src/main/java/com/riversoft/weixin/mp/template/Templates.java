package com.riversoft.weixin.mp.template;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.riversoft.weixin.common.WxClient;
import com.riversoft.weixin.common.exception.WxRuntimeException;
import com.riversoft.weixin.common.util.JsonMapper;
import com.riversoft.weixin.mp.MpWxClientFactory;
import com.riversoft.weixin.mp.base.AppSetting;
import com.riversoft.weixin.mp.base.WxEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * 模板消息API
 *
 * Created by exizhai on 12/16/2015.
 */
public class Templates {

    private static Logger logger = LoggerFactory.getLogger(Templates.class);

    private WxClient wxClient;

    public static Templates defaultTemplates() {
        return with(AppSetting.defaultSettings());
    }

    public static Templates with(AppSetting appSetting) {
        Templates templates = new Templates();
        templates.setWxClient(MpWxClientFactory.getInstance().with(appSetting));
        return templates;
    }

    public void setWxClient(WxClient wxClient) {
        this.wxClient = wxClient;
    }

    /**
     * 设置所属行业
     * @param id1 众号模板消息所属行业编号1
     * @param id2 众号模板消息所属行业编号2
     */
    public void setIndustries(String id1, String id2) {
        String url = WxEndpoint.get("url.template.industry.set");
        String json = String.format("{\"industry_id1\":\"%s\", \"industry_id2\":\"%s\"}", id1, id2);

        logger.debug("template message, set industry: {}", json);
        wxClient.post(url, json);
    }

    /**
     * 设置所属行业
     * @param id1 众号模板消息所属行业编号1
     */
    public void setIndustry(String id1) {
        String url = WxEndpoint.get("url.template.industry.set");
        String json = String.format("{\"industry_id1\":\"%s\"}", id1);

        logger.debug("template message, set industry: {}", json);
        wxClient.post(url, json);
    }

    /**
     * 获取模板, 容易被微信的文档误导，这里其实是获取模板到服务号的管理端，并不单纯是获取ID那么简单
     * 对于相同的code，每调用一次微信后台会生成一条新的记录
     * @param code  模板库中模板的编号，有“TM**”和“OPENTMTM**”等形式
     * @return
     */
    public String fetch(String code) {
        String url = WxEndpoint.get("url.template.get");
        String json = String.format("{\"template_id_short\":\"%s\"}", code);

        logger.debug("template message, get template id: {}", json);
        String response = wxClient.post(url, json);
        Map<String, Object> result = JsonMapper.defaultMapper().json2Map(response);

        if(result.containsKey("template_id")) {
            return result.get("template_id").toString();
        } else {
            throw new WxRuntimeException(999, "fetch template id failed.");
        }
    }

    /**
     * 发送模板消息
     *
     * @param toUser
     * @param templateId
     * @param url
     * @param messages
     * @return 消息ID
     */
    public int send(String toUser, String templateId, String url, Map<String, Item> messages) {
        String sendUrl = WxEndpoint.get("url.template.send");
        MessageWrapper messageWrapper = new MessageWrapper();
        messageWrapper.setToUser(toUser);
        messageWrapper.setTemplateId(templateId);
        messageWrapper.setUrl(url);
        messageWrapper.setData(messages);

        String json = JsonMapper.defaultMapper().toJson(messageWrapper);

        logger.debug("template message, send message: {}", json);
        String response = wxClient.post(sendUrl, json);
        Map<String, Object> result = JsonMapper.defaultMapper().json2Map(response);

        if(result.containsKey("msgid")) {
            return (Integer)result.get("msgid");
        } else {
            throw new WxRuntimeException(999, "send template message failed.");
        }
    }


    public static class MessageWrapper {

        @JsonProperty("touser")
        private String toUser;

        @JsonProperty("template_id")
        private String templateId;

        private String url;

        private Map<String, Item> data;

        public String getToUser() {
            return toUser;
        }

        public void setToUser(String toUser) {
            this.toUser = toUser;
        }

        public String getTemplateId() {
            return templateId;
        }

        public void setTemplateId(String templateId) {
            this.templateId = templateId;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public Map<String, Item> getData() {
            return data;
        }

        public void setData(Map<String, Item> data) {
            this.data = data;
        }
    }
}

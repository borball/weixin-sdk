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

import java.util.List;
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
     * @param primary 众号模板消息所属行业编号1(主业)
     * @param secondary 众号模板消息所属行业编号2(副业)
     */
    public void setIndustries(String primary, String secondary) {
        String url = WxEndpoint.get("url.template.industry.set");
        String json = String.format("{\"industry_id1\":\"%s\", \"industry_id2\":\"%s\"}", primary, secondary);

        logger.debug("template message, set industry: {}", json);
        wxClient.post(url, json);
    }

    /**
     * 获取所属行业
     */
    public Industry getIndustries() {
        String url = WxEndpoint.get("url.template.industry.get");

        logger.debug("template message, get industry.");
        String response = wxClient.get(url);
        IndustryWrapper industryWrapper = JsonMapper.defaultMapper().fromJson(response, IndustryWrapper.class);
        return new Industry(industryWrapper.getPrimary().toString(), industryWrapper.getSecondary().toString());
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
     * 获取所有的模板
     * @return
     */
    public List<Template> list(){
        String url = WxEndpoint.get("url.template.list");
        logger.debug("template message, list templates.");
        String response = wxClient.get(url);
        TemplateListWrapper templateListWrapper = JsonMapper.defaultMapper().fromJson(response, TemplateListWrapper.class);
        return templateListWrapper.getTemplateList();
    }

    /**
     * 删除模板
     * @param templateId
     */
    public void delete(String templateId) {
        String url = WxEndpoint.get("url.template.delete");
        String json = String.format("{\"template_id\":\"%s\"}", templateId);
        logger.debug("template message, delete template: {}", json);
        wxClient.post(url, json);
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
    public long send(String toUser, String templateId, String url, Map<String, Data> messages) {
        return send(toUser, templateId, url, null, messages);
    }

    /**
     * 发送模板消息
     * @param toUser
     * @param templateId
     * @param miniProgram
     * @param messages
     * @return
     */
    public long send(String toUser, String templateId, MiniProgram miniProgram, Map<String, Data> messages) {
        return send(toUser, templateId, null, miniProgram, messages);
    }

    /**
     * 发送模板消息
     *
     * url和miniprogram都是非必填字段，若都不传则模板无跳转；
     * 若都传，会优先跳转至小程序。
     * 开发者可根据实际需要选择其中一种跳转方式即可。
     * 当用户的微信客户端版本不支持跳小程序时，将会跳转至url
     *
     * @param toUser
     * @param templateId
     * @param url
     * @param miniProgram
     * @param messages
     * @return 消息ID
     */
    public long send(String toUser, String templateId, String url, MiniProgram miniProgram, Map<String, Data> messages) {
        String sendUrl = WxEndpoint.get("url.template.send");
        MessageWrapper messageWrapper = new MessageWrapper();
        messageWrapper.setToUser(toUser);
        messageWrapper.setTemplateId(templateId);

        if(url != null) {
            messageWrapper.setUrl(url);
        }
        if(miniProgram != null) {
            messageWrapper.setMiniProgram(miniProgram);
        }
        messageWrapper.setData(messages);

        String json = JsonMapper.defaultMapper().toJson(messageWrapper);

        logger.debug("template message, send message: {}", json);
        String response = wxClient.post(sendUrl, json);
        Map<String, Object> result = JsonMapper.defaultMapper().json2Map(response);

        if(result.containsKey("msgid")) {
            return ((Number)result.get("msgid")).longValue();
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

        @JsonProperty("miniprogram")
        private MiniProgram miniProgram;

        private Map<String, Data> data;

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

        public MiniProgram getMiniProgram() {
            return miniProgram;
        }

        public void setMiniProgram(MiniProgram miniProgram) {
            this.miniProgram = miniProgram;
        }

        public Map<String, Data> getData() {
            return data;
        }

        public void setData(Map<String, Data> data) {
            this.data = data;
        }
    }

    public static class TemplateListWrapper {

        @JsonProperty("template_list")
        private List<Template> templateList;

        public List<Template> getTemplateList() {
            return templateList;
        }

        public void setTemplateList(List<Template> templateList) {
            this.templateList = templateList;
        }
    }

    public static class IndustryWrapper {

        @JsonProperty("primary_industry")
        private IndustryClassWrapper primary;

        @JsonProperty("secondary_industry")
        private IndustryClassWrapper secondary;

        public IndustryClassWrapper getPrimary() {
            return primary;
        }

        public void setPrimary(IndustryClassWrapper primary) {
            this.primary = primary;
        }

        public IndustryClassWrapper getSecondary() {
            return secondary;
        }

        public void setSecondary(IndustryClassWrapper secondary) {
            this.secondary = secondary;
        }
    }

    public static class IndustryClassWrapper {

        @JsonProperty("first_class")
        private String first;
        @JsonProperty("second_class")
        private String second;

        public String getFirst() {
            return first;
        }

        public void setFirst(String first) {
            this.first = first;
        }

        public String getSecond() {
            return second;
        }

        public void setSecond(String second) {
            this.second = second;
        }

        @Override
        public String toString() {
            return first + "|" + second;
        }
    }
}

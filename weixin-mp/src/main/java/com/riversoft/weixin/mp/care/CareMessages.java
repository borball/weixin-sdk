package com.riversoft.weixin.mp.care;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.riversoft.weixin.common.WxClient;
import com.riversoft.weixin.common.message.Media;
import com.riversoft.weixin.common.message.News;
import com.riversoft.weixin.common.message.Text;
import com.riversoft.weixin.common.util.JsonMapper;
import com.riversoft.weixin.mp.MpWxClientFactory;
import com.riversoft.weixin.mp.base.AppSetting;
import com.riversoft.weixin.mp.base.WxEndpoint;
import com.riversoft.weixin.mp.care.bean.Card;
import com.riversoft.weixin.mp.care.bean.Music;
import com.riversoft.weixin.mp.care.bean.Video;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 客服接口发送消息
 * Created by exizhai on 11/23/2015.
 */
public class CareMessages {

    private static Logger logger = LoggerFactory.getLogger(CareMessages.class);

    private WxClient wxClient;

    public static CareMessages defaultCareMessages() {
        return with(AppSetting.defaultSettings());
    }

    public static CareMessages with(AppSetting appSetting) {
        CareMessages messages = new CareMessages();
        messages.setWxClient(MpWxClientFactory.getInstance().with(appSetting));
        return messages;
    }

    public void setWxClient(WxClient wxClient) {
        this.wxClient = wxClient;
    }

    /**
     * 客服发送文本消息
     *
     * @param openId
     * @param text
     */
    public void text(String openId, String text) {
        text(openId, text, null);
    }

    /**
     * 客服发送文本消息
     *
     * @param openId
     * @param text
     * @param from   客服账号
     */
    public void text(String openId, String text, String from) {
        Map<String, Object> request = initMessage(openId, "text", from);
        request.put("text", new Text(text));

        String url = WxEndpoint.get("url.care.message.send");
        wxClient.post(url, JsonMapper.defaultMapper().toJson(request));
    }

    /**
     * 客服发送文本消息
     *
     * @param openId
     * @param image
     */
    public void image(String openId, String image) {
        image(openId, image, null);
    }

    /**
     * 客服发送图片消息
     *
     * @param openId
     * @param image
     * @param from   客服账号
     */
    public void image(String openId, String image, String from) {
        Map<String, Object> request = initMessage(openId, "image", from);
        request.put("image", new Media(image));

        String url = WxEndpoint.get("url.care.message.send");
        wxClient.post(url, JsonMapper.defaultMapper().toJson(request));
    }

    /**
     * 客服发送语音消息
     *
     * @param openId
     * @param voice
     */
    public void voice(String openId, String voice) {
        voice(openId, voice, null);
    }

    /**
     * 客服发送语音消息
     *
     * @param openId
     * @param voice
     * @param from   客服账号
     */
    public void voice(String openId, String voice, String from) {
        Map<String, Object> request = initMessage(openId, "voice", from);
        request.put("voice", new Media(voice));

        String url = WxEndpoint.get("url.care.message.send");
        wxClient.post(url, JsonMapper.defaultMapper().toJson(request));
    }

    /**
     * 客服发送video消息
     *
     * @param openId
     * @param video
     */
    public void video(String openId, Video video) {
        video(openId, video, null);
    }

    /**
     * 客服发送video消息
     *
     * @param openId
     * @param video
     * @param from   客服账号
     */
    public void video(String openId, Video video, String from) {
        Map<String, Object> request = initMessage(openId, "video", from);
        request.put("video", video);

        String url = WxEndpoint.get("url.care.message.send");
        wxClient.post(url, JsonMapper.defaultMapper().toJson(request));
    }

    /**
     * 客服发送music消息
     *
     * @param openId
     * @param music
     */
    public void music(String openId, Music music) {
        music(openId, music, null);
    }

    /**
     * 客服发送music消息
     *
     * @param openId
     * @param music
     * @param from   客服账号
     */
    public void music(String openId, Music music, String from) {
        Map<String, Object> request = initMessage(openId, "music", from);
        request.put("music", music);

        String url = WxEndpoint.get("url.care.message.send");
        wxClient.post(url, JsonMapper.defaultMapper().toJson(request));
    }

    /**
     * 客服发送news消息
     *
     * @param openId
     * @param news
     */
    public void news(String openId, News news) {
        news(openId, news, null);
    }

    /**
     * 客服发送news消息
     *
     * @param openId
     * @param news
     * @param from   客服账号
     */
    public void news(String openId, News news, String from) {
        Map<String, Object> request = initMessage(openId, "news", from);
        request.put("news", news);

        String url = WxEndpoint.get("url.care.message.send");
        wxClient.post(url, JsonMapper.defaultMapper().toJson(request));
    }

    /**
     * 客服发送mpNews消息
     *
     * @param openId
     * @param mpNews
     */
    public void mpNews(String openId, String mpNews) {
        mpNews(openId, mpNews, null);
    }

    /**
     * 客服发送mpNews消息
     *
     * @param openId
     * @param mpNews
     * @param from   客服账号
     */
    public void mpNews(String openId, String mpNews, String from) {
        Map<String, Object> request = initMessage(openId, "mpnews", from);
        request.put("mpnews", new Media(mpNews));

        String url = WxEndpoint.get("url.care.message.send");
        wxClient.post(url, JsonMapper.defaultMapper().toJson(request));
    }

    /**
     * 发送卡券
     *
     * @param openId
     * @param cardId
     */
    public void card(String openId, String cardId) {
        card(openId, cardId, null);
    }

    /**
     * 发送卡券
     *
     * @param openId
     * @param cardId
     * @param from   客服账号
     */
    public void card(String openId, String cardId, String from) {
        Map<String, Object> request = initMessage(openId, "wxcard", from);
        request.put("wxcard", new Card(cardId));

        String url = WxEndpoint.get("url.care.message.send");
        wxClient.post(url, JsonMapper.defaultMapper().toJson(request));
    }

    private Map<String, Object> initMessage(String openId, String msgType, String from) {
        Map<String, Object> request = new HashMap<>();
        request.put("msgtype", msgType);
        request.put("touser", openId);

        if (from != null && !"".equals(from)) {
            request.put("customservice", new KfAccount(from));
        }

        return request;
    }

    public static class KfAccount {

        @JsonProperty("kf_account")
        private String account;

        public KfAccount(String account) {
            this.account = account;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }
    }


}

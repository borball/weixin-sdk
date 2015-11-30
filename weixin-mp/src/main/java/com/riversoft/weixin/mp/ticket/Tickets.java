package com.riversoft.weixin.mp.ticket;

import com.riversoft.weixin.common.WxClient;
import com.riversoft.weixin.common.exception.WxRuntimeException;
import com.riversoft.weixin.common.util.JsonMapper;
import com.riversoft.weixin.common.util.URLEncoder;
import com.riversoft.weixin.mp.MpWxClientFactory;
import com.riversoft.weixin.mp.base.AppSetting;
import com.riversoft.weixin.mp.base.WxEndpoint;
import com.riversoft.weixin.mp.ticket.bean.Ticket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Map;

/**
 * Created by exizhai on 11/30/2015.
 */
public class Tickets {

    private static Logger logger = LoggerFactory.getLogger(Tickets.class);

    private WxClient wxClient;

    public static Tickets defaultTickets() {
        return with(AppSetting.defaultSettings());
    }

    public static Tickets with(AppSetting appSetting) {
        Tickets tickets = new Tickets();
        tickets.setWxClient(MpWxClientFactory.getInstance().with(appSetting));
        return tickets;
    }

    public void setWxClient(WxClient wxClient) {
        this.wxClient = wxClient;
    }

    /**
     * 创建临时二维码
     * @return
     */
    public Ticket temporary(int expires, int sceneId) {
        String url = WxEndpoint.get("url.ticket.create");
        String json = "{\"expire_seconds\":%s,\"action_name\":\"QR_SCENE\",\"action_info\":{\"scene\":{\"scene_id\":%s}}}";

        logger.debug("add temporary ticket : {}", String.format(json, expires, sceneId));
        String response = wxClient.post(url, String.format(json, expires, sceneId));
        return JsonMapper.nonEmptyMapper().fromJson(response, Ticket.class);
    }

    /**
     * 创建永久二维码
     * @return
     */
    public Ticket permanent(int sceneId) {
        String url = WxEndpoint.get("url.ticket.create");
        String json = "{\"action_name\":\"QR_LIMIT_SCENE\",\"action_info\":{\"scene\":{\"scene_id\":%s}}}";

        logger.debug("add permanent ticket : {}", String.format(json, sceneId));
        String response = wxClient.post(url, json);
        return JsonMapper.nonEmptyMapper().fromJson(response, Ticket.class);
    }

    /**
     * 创建永久二维码
     * @return
     */
    public Ticket permanent(String sceneStr) {
        String url = WxEndpoint.get("url.ticket.create");
        String json = "{\"action_name\":\"QR_LIMIT_STR_SCENE\",\"action_info\":{\"scene\":{\"scene_str\":\"%s\"}}}";

        logger.debug("add permanent ticket : {}", String.format(json, sceneStr));
        String response = wxClient.post(url, json);
        return JsonMapper.nonEmptyMapper().fromJson(response, Ticket.class);
    }

    /**
     * 长链接转换成短链接
     * @param longUrl
     * @return
     */
    public String url2short(String longUrl) {
        String url = WxEndpoint.get("url.url.toshort");
        String json = "{\"action\":\"long2short\",\"long_url\":\"%s\"}";

        logger.debug("long url to short: {}", String.format(json, longUrl));
        String response = wxClient.post(url, json);

        Map<String, Object> result = JsonMapper.defaultMapper().json2Map(response);
        if(result.containsKey("short_url")) {
            return result.get("short_url").toString();
        } else {
            throw new WxRuntimeException(999, "long url to short failed.");
        }
    }

    /**
     * 使用ticket换取二维码
     * @param ticket
     * @return
     */
    public InputStream getQrcode(String ticket) {
        String url = WxEndpoint.get("url.ticket.qrcode");
        return wxClient.getBinary(String.format(url, URLEncoder.encode(ticket)), false);
    }

}

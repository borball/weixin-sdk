package com.riversoft.weixin.mp.ticket;

import com.riversoft.weixin.common.WxClient;
import com.riversoft.weixin.common.util.JsonMapper;
import com.riversoft.weixin.common.util.URLEncoder;
import com.riversoft.weixin.mp.MpWxClientFactory;
import com.riversoft.weixin.mp.base.AppSetting;
import com.riversoft.weixin.mp.base.WxEndpoint;
import com.riversoft.weixin.mp.ticket.bean.Ticket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
     *
     * @return
     */
    public Ticket temporary(int expires, int sceneId) {
        String url = WxEndpoint.get("url.ticket.create");
        String json = "{\"expire_seconds\":%s,\"action_name\":\"QR_SCENE\",\"action_info\":{\"scene\":{\"scene_id\":%s}}}";

        logger.debug("create temporary ticket : {}", String.format(json, expires, sceneId));
        String response = wxClient.post(url, String.format(json, expires, sceneId));
        return JsonMapper.nonEmptyMapper().fromJson(response, Ticket.class);
    }

    /**
     * 创建永久二维码
     *
     * @return
     */
    public Ticket permanent(int sceneId) {
        if(sceneId < 1 || sceneId > 100000) {
            throw new IllegalArgumentException("sceneId shall be between 1 and 100000.");
        }
        String url = WxEndpoint.get("url.ticket.create");
        String json = "{\"action_name\":\"QR_LIMIT_SCENE\",\"action_info\":{\"scene\":{\"scene_id\":%s}}}";

        logger.debug("create permanent ticket : {}", String.format(json, sceneId));
        String response = wxClient.post(url, String.format(json, sceneId));
        return JsonMapper.nonEmptyMapper().fromJson(response, Ticket.class);
    }

    /**
     * 创建永久二维码
     *
     * @return
     */
    public Ticket permanent(String sceneStr) {
        if(sceneStr == null || sceneStr.length() == 0 || sceneStr.length() > 64) {
            throw new IllegalArgumentException("sceneStr shall not be empty and the length shall be less than 64.");
        }

        String url = WxEndpoint.get("url.ticket.create");
        String json = "{\"action_name\":\"QR_LIMIT_STR_SCENE\",\"action_info\":{\"scene\":{\"scene_str\":\"%s\"}}}";

        logger.debug("create permanent ticket : {}", String.format(json, sceneStr));
        String response = wxClient.post(url, String.format(json, sceneStr));
        return JsonMapper.nonEmptyMapper().fromJson(response, Ticket.class);
    }

    /**
     * 使用ticket换取二维码
     *
     * @param ticket
     * @return
     */
    public byte[] getQrcode(String ticket) {
        String url = WxEndpoint.get("url.ticket.qrcode");
        return wxClient.getBinary(String.format(url, URLEncoder.encode(ticket)), false);
    }

}

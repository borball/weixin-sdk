package com.riversoft.weixin.mp.user;

import com.riversoft.weixin.common.WxClient;
import com.riversoft.weixin.common.util.JsonMapper;
import com.riversoft.weixin.mp.MpWxClientFactory;
import com.riversoft.weixin.mp.base.AppSetting;
import com.riversoft.weixin.mp.base.WxEndpoint;
import com.riversoft.weixin.mp.user.bean.UserPagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 黑名单管理
 * @borball on 4/7/2017.
 */
public class BlackLists {

    private static Logger logger = LoggerFactory.getLogger(BlackLists.class);

    private WxClient wxClient;

    public static BlackLists defaultBlackLists() {
        return with(AppSetting.defaultSettings());
    }

    public static BlackLists with(AppSetting appSetting) {
        BlackLists blackLists = new BlackLists();
        blackLists.setWxClient(MpWxClientFactory.getInstance().with(appSetting));
        return blackLists;
    }

    public void setWxClient(WxClient wxClient) {
        this.wxClient = wxClient;
    }

    /**
     * 拉黑
     * @param openids
     */
    public void black(List<String> openids) {
        String url = WxEndpoint.get("url.blacklist.black");
        Map<String, Object> body = new HashMap<>();
        body.put("opened_list", openids);
        logger.debug("black users: {}", openids);
        wxClient.post(url, JsonMapper.defaultMapper().toJson(body));
    }

    /**
     * 取消拉黑
     * @param openids
     */
    public void unblack(List<String> openids) {
        String url = WxEndpoint.get("url.blacklist.unblack");
        Map<String, Object> body = new HashMap<>();
        body.put("opened_list", openids);
        logger.debug("unblack users: {}", openids);
        wxClient.post(url, JsonMapper.defaultMapper().toJson(body));
    }

    /**
     * 获取黑名单列表
     * @return
     */
    public UserPagination list(){
        return list("");
    }

    /**
     * 获取黑名单列表
     * @param nextOpenId
     * @return
     */
    public UserPagination list(String nextOpenId){
        String url = WxEndpoint.get("url.blacklist.list");
        Map<String, Object> body = new HashMap<>();
        body.put("begin_openid", nextOpenId);
        logger.debug("list all black list: {}", nextOpenId);
        String response = wxClient.post(url, JsonMapper.defaultMapper().toJson(body));
        return JsonMapper.defaultMapper().fromJson(response, UserPagination.class);
    }

}

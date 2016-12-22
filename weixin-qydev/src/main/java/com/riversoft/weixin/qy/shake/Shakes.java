package com.riversoft.weixin.qy.shake;

import com.riversoft.weixin.common.WxClient;
import com.riversoft.weixin.common.util.JsonMapper;
import com.riversoft.weixin.qy.QyWxClientFactory;
import com.riversoft.weixin.qy.base.CorpSetting;
import com.riversoft.weixin.qy.base.WxEndpoint;
import com.riversoft.weixin.qy.shake.bean.Shake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * Created by exizhai on 9/25/2015.
 */
public class Shakes {

    private static Logger logger = LoggerFactory.getLogger(Shakes.class);
    private WxClient wxClient;

    public static Shakes defaultShakes() {
        return with(CorpSetting.defaultSettings());
    }

    public static Shakes with(CorpSetting corpSetting) {
        Shakes shakes = new Shakes();
        shakes.setWxClient(QyWxClientFactory.getInstance().with(corpSetting));
        return shakes;
    }

    public void setWxClient(WxClient wxClient) {
        this.wxClient = wxClient;
    }

    /**
     * 获取设备及用户信息
     * @param ticket
     * @return
     */
    public Shake get(String ticket) {
        String url = WxEndpoint.get("url.shake.get");
        String body = String.format("{\"ticket\":\"%s\"}", ticket);
        logger.debug("get shake info: {}", body);
        String content = wxClient.post(url, body);
        ShakeWrapper shakeWrapper = JsonMapper.nonEmptyMapper().fromJson(content, ShakeWrapper.class);
        return shakeWrapper.getData();
    }

    public static class ShakeWrapper implements Serializable {

        private Shake data;

        public Shake getData() {
            return data;
        }

        public void setData(Shake data) {
            this.data = data;
        }
    }

}

package com.riversoft.weixin.mp.stat;

import com.riversoft.weixin.common.WxClient;
import com.riversoft.weixin.common.util.JsonMapper;
import com.riversoft.weixin.mp.MpWxClientFactory;
import com.riversoft.weixin.mp.base.AppSetting;
import com.riversoft.weixin.mp.base.WxEndpoint;
import com.riversoft.weixin.mp.stat.bean.UserCumulative;
import com.riversoft.weixin.mp.stat.bean.UserSummary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * @borball on 5/25/2016.
 */
public class Stats {

    private static Logger logger = LoggerFactory.getLogger(Stats.class);

    private WxClient wxClient;

    public static Stats defaultStats() {
        return with(AppSetting.defaultSettings());
    }

    public static Stats with(AppSetting appSetting) {
        Stats stats = new Stats();
        stats.setWxClient(MpWxClientFactory.getInstance().with(appSetting));
        return stats;
    }

    public void setWxClient(WxClient wxClient) {
        this.wxClient = wxClient;
    }

    /**
     * 获取用户增减数据
     * @param start
     * @param end
     * @return
     */
    public UserSummary getUserSummary(Date start, Date end) {
        return null;
    }

    /**
     * 获取用户增减数据
     * @param start
     * @return
     */
    public UserSummary getUserSummary(Date start) {
        return getUserSummary(start, new Date());
    }

    /**
     * 获取累计用户数据
     * @param start
     * @param end
     * @return
     */
    public UserCumulative getUserCumulative(Date start, Date end) {
        return null;
    }

    /**
     * 获取累计用户数据
     * @param start
     * @return
     */
    public UserCumulative getUserCumulative(Date start) {
        return getUserCumulative(start, new Date());
    }
}

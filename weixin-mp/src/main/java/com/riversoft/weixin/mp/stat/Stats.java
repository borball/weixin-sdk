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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
    public List<UserSummary> getUserSummary(Date start, Date end) {
        if(diffThan6Days(start, end)) {
            throw new IllegalArgumentException("start和end相差不能超过6天以上");
        }

        String url = WxEndpoint.get("url.stats.user.summary");
        String json = "{\"begin_date\":\"%s\",\"end_date\":\"%s\"}";

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String body = String.format(json, dateFormat.format(start), dateFormat.format(end));
        logger.debug("stats: get user summary: {}", body);
        String response = wxClient.post(url, body);
        UserSummaryWrapper userSummaryWrapper = JsonMapper.defaultMapper().fromJson(response, UserSummaryWrapper.class);
        return userSummaryWrapper.getList();
    }

    /**
     * 获取用户增减数据
     * @param start
     * @return
     */
    public List<UserSummary> getUserSummary(Date start) {
        return getUserSummary(start, new Date());
    }

    /**
     * 获取累计用户数据
     * @param start
     * @param end
     * @return
     */
    public List<UserCumulative> getUserCumulative(Date start, Date end) {
        if(diffThan6Days(start, end)) {
            throw new IllegalArgumentException("start和end相差不能超过6天以上");
        }

        String url = WxEndpoint.get("url.stats.user.cumulative");
        String json = "{\"begin_date\":\"%s\",\"end_date\":\"%s\"}";

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String body = String.format(json, dateFormat.format(start), dateFormat.format(end));
        logger.debug("stats: get user summary: {}", body);
        String response = wxClient.post(url, body);
        UserCumulativeWrapper userCumulativeWrapper = JsonMapper.defaultMapper().fromJson(response, UserCumulativeWrapper.class);
        return userCumulativeWrapper.getList();
    }

    private boolean diffThan6Days(Date start, Date end) {
        Calendar calendarStart = Calendar.getInstance();
        calendarStart.setTime(start);
        calendarStart.set(Calendar.HOUR, 0);
        calendarStart.set(Calendar.MINUTE, 0);
        calendarStart.set(Calendar.SECOND, 0);
        calendarStart.set(Calendar.MILLISECOND, 0);

        Calendar calendarEnd = Calendar.getInstance();
        calendarEnd.setTime(start);
        calendarEnd.set(Calendar.HOUR, 0);
        calendarEnd.set(Calendar.MINUTE, 0);
        calendarEnd.set(Calendar.SECOND, 0);
        calendarEnd.set(Calendar.MILLISECOND, 0);

        return (calendarStart.getTime().getTime() - calendarEnd.getTime().getTime()) > (1000 * 60 * 60 * 24 * 6);
    }

    /**
     * 获取累计用户数据
     * @param start
     * @return
     */
    public List<UserCumulative> getUserCumulative(Date start) {
        return getUserCumulative(start, new Date());
    }

    public static class UserSummaryWrapper {

        private List<UserSummary> list;

        public List<UserSummary> getList() {
            return list;
        }

        public void setList(List<UserSummary> list) {
            this.list = list;
        }
    }

    public static class UserCumulativeWrapper {

        private List<UserCumulative> list;

        public List<UserCumulative> getList() {
            return list;
        }

        public void setList(List<UserCumulative> list) {
            this.list = list;
        }
    }
}

package com.riversoft.weixin.mp.stat;

import com.riversoft.weixin.mp.stat.bean.UserCumulative;
import com.riversoft.weixin.mp.stat.bean.UserSummary;
import org.junit.Test;

import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.assertNotNull;

/**
 * @borball on 6/3/2016.
 */
public class StatsTest {

    @Test
      public void testGetUserSummary(){
        Calendar calendar = Calendar.getInstance();
        calendar.roll(Calendar.DAY_OF_YEAR, -6);
        List<UserSummary> userSummaries = Stats.defaultStats().getUserSummary(calendar.getTime());
        assertNotNull(userSummaries);
    }

    @Test
    public void testGetUserCumulative(){
        Calendar calendar = Calendar.getInstance();
        calendar.roll(Calendar.DAY_OF_YEAR, -6);
        List<UserCumulative> userCumulativeList = Stats.defaultStats().getUserCumulative(calendar.getTime());
        assertNotNull(userCumulativeList);
    }
}

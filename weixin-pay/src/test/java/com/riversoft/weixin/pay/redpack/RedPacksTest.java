package com.riversoft.weixin.pay.redpack;

import com.riversoft.weixin.pay.redpack.bean.RedPackRequest;
import com.riversoft.weixin.pay.redpack.bean.RedPackResponse;
import com.riversoft.weixin.pay.redpack.bean.RedPackResult;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by exizhai on 12/2/2015.
 */
public class RedPacksTest {

    @Test
    public void testSendSingle(){
        //woden: oby4gwXNTVGvcroy9ehU7LGWkRtY
        //cyrus: oby4gwWNM0GkuU1LTS4lqK1qMg1s
        //borball: oby4gwX7iPPp-lWZ3t7yGsQ0GbHw
        //ivory:oby4gwRjelcBpbU0s-Dwnovl9xIw
        //johnny: oby4gwaYRmbfI5LuPLzGtdK6fgnM
        RedPackRequest redPackRequest = new RedPackRequest();
        redPackRequest.setActivityName("土豪发红包");
        redPackRequest.setAmount(100);
        redPackRequest.setBillNumber("1292063901201605150012300014");
        redPackRequest.setNumber(1);
        redPackRequest.setOpenId("oELhlt7Q-lRmLbRsPsaKeVX6pqjg");
        redPackRequest.setRemark("测试发红包");
        redPackRequest.setWishing("恭喜发财");
        redPackRequest.setClientIp("127.0.0.1");
        redPackRequest.setSendName("创河软件");

        RedPackResponse redPackResponse = RedPacks.defaultRedPacks().sendSingle(redPackRequest);
        Assert.assertNotNull(redPackResponse);

    }

    @Test
    public void testSendGroup(){
        //woden|wx28a41929a2d6a297|obJjTwAptnfB5pBDpDuKCRA2oVDQ
        RedPackRequest redPackRequest = new RedPackRequest();
        redPackRequest.setAppId("wx28a41929a2d6a297");
        redPackRequest.setActivityName("欢迎光临创河软件");
        redPackRequest.setAmount(600);
        redPackRequest.setBillNumber("1292063901201512030010000022");
        redPackRequest.setNumber(5);
        redPackRequest.setOpenId("obJjTwAptnfB5pBDpDuKCRA2oVDQ");
        redPackRequest.setRemark("测试裂变红包");
        redPackRequest.setWishing("欢迎光临");
        //redPackRequest.setClientIp("127.0.0.1");
        redPackRequest.setSendName("创河软件");

        RedPackResponse redPackResponse = RedPacks.defaultRedPacks().sendGroup(redPackRequest);

        Assert.assertNotNull(redPackResponse);
        Assert.assertFalse(redPackResponse.success());
    }

    @Test
    public void testGet(){
        RedPackResult redPackResult = RedPacks.defaultRedPacks().query("1292063901201512030000000001");
        Assert.assertNotNull(redPackResult);
    }
}

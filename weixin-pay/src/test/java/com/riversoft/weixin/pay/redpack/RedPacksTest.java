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
        RedPackRequest redPackRequest = new RedPackRequest();
        redPackRequest.setActivityName("土豪发红包");
        redPackRequest.setAmount(100);
        redPackRequest.setBillNumber("1292063901201512030000000001");
        redPackRequest.setNumber(1);
        redPackRequest.setOpenId("oELhlt7Q-lRmLbRsPsaKeVX6pqjg");
        redPackRequest.setRemark("第一个测试红包");
        redPackRequest.setWishing("祝您发财");
        redPackRequest.setClientIp("127.0.0.1");
        redPackRequest.setSendName("创河软件测试组");

        RedPackResponse redPackResponse = RedPacks.defaultRedPacks().sendSingle(redPackRequest);
        Assert.assertNotNull(redPackResponse);
        Assert.assertFalse(redPackResponse.isSuccess());
    }

    @Test
    public void testSendGroup(){
        RedPackRequest redPackRequest = new RedPackRequest();
        redPackRequest.setActivityName("土豪发红包");
        redPackRequest.setAmount(500);
        redPackRequest.setBillNumber("1292063901201512030000000001");
        redPackRequest.setNumber(3);
        redPackRequest.setOpenId("oELhlt7Q-lRmLbRsPsaKeVX6pqjg");
        redPackRequest.setRemark("第一个测试红包");
        redPackRequest.setWishing("祝您发财");
        redPackRequest.setClientIp("127.0.0.1");
        redPackRequest.setSendName("创河软件测试组");

        RedPackResponse redPackResponse = RedPacks.defaultRedPacks().sendGroup(redPackRequest);
        Assert.assertNotNull(redPackResponse);
        Assert.assertFalse(redPackResponse.isSuccess());
    }

    @Test
    public void testGet(){
        RedPackResult redPackResult = RedPacks.defaultRedPacks().get("1292063901201512030000000001");
        Assert.assertNotNull(redPackResult);
    }
}

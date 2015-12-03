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
        String[] users = {"oELhltw0Aq6d_gbd-01W6npGyh70", "oELhlt7Q-lRmLbRsPsaKeVX6pqjg", "oELhlt0b7w9q7TiyRUq7iZ4r3FZM", "oELhlt7JHGmSC47bG0hmm4L1LYzg"};
        int i = 5;
        for(String user: users) {
            RedPackRequest redPackRequest = new RedPackRequest();
            redPackRequest.setActivityName("土豪发红包");
            redPackRequest.setAmount(100);
            redPackRequest.setBillNumber("129206390120151203000000000" + i);
            redPackRequest.setNumber(1);
            redPackRequest.setOpenId(user);
            redPackRequest.setRemark("测试发红包");
            redPackRequest.setWishing("恭喜发财");
            redPackRequest.setClientIp("127.0.0.1");
            redPackRequest.setSendName("创河软件");

            RedPackResponse redPackResponse = RedPacks.defaultRedPacks().sendSingle(redPackRequest);
            Assert.assertNotNull(redPackResponse);
            i++;
        }
    }

    @Test
    public void testSendGroup(){
        RedPackRequest redPackRequest = new RedPackRequest();
        redPackRequest.setActivityName("土豪发红包");
        redPackRequest.setAmount(600);
        redPackRequest.setBillNumber("1292063901201512030000000002");
        redPackRequest.setNumber(5);
        redPackRequest.setOpenId("oELhlt7Q-lRmLbRsPsaKeVX6pqjg");
        redPackRequest.setRemark("测试裂变红包");
        redPackRequest.setWishing("恭喜发财");
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

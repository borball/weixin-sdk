package com.riversoft.weixin.pay.redpack;

import com.riversoft.weixin.pay.transfer.Transfers;
import com.riversoft.weixin.pay.transfer.bean.TransferRequest;
import org.junit.Test;

/**
 * Created by exizhai on 12/2/2015.
 */
public class TransfersTest {

    @Test
    public void testTransfer(){
        String[] users = {"oELhlt7Q-lRmLbRsPsaKeVX6pqjg"};
        int i = 5;
        for(String user: users) {
            TransferRequest transferRequest = new TransferRequest();
            transferRequest.setAmount(100);
            transferRequest.setCheckName("NO_CHECK");
            transferRequest.setClientIp("127.0.0.1");
            transferRequest.setDesc("测试企业付款");
            transferRequest.setOpenId(user);
            transferRequest.setPartnerTradeNo("129206390120161203000010001" + i);

            Transfers.defaultTransfers().transfer(transferRequest);
            i++;
        }

    }
}

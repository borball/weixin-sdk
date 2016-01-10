package com.riversoft.weixin.mp.message;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by exizhai on 11/24/2015.
 */
public class MpMessagesTest {

    @Test
    public void testSend(){
        List<String> users = new ArrayList<>();
        users.add("o7Tmfs3EHCndVenva5knKxA4D3XA");
        users.add("o7Tmfs96UDesd920Gzi0jYJPnBzQ");
        MpMessages.defaultMpMessages().mpNews(users, "oR82Fct6wNYgjjFMQVNdmi089sdyuSVtovm1DFqG0Wg");
    }

    @Test
    public void testSendAll(){
        long msgId = MpMessages.defaultMpMessages().mpNews("oR82Fct6wNYgjjFMQVNdmi089sdyuSVtovm1DFqG0Wg");
        boolean result = MpMessages.defaultMpMessages().success(msgId);

        Assert.assertTrue(result);
    }

}

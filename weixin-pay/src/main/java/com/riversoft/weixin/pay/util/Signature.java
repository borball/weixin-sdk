package com.riversoft.weixin.pay.util;

import com.google.common.base.Joiner;

import java.util.Map;

/**
 * Created by exizhai on 12/1/2015.
 */
public class Signature {

    public static String sign(Map<String, Object> map, String key){
        String str = Joiner.on("&").withKeyValueSeparator("=").join(map);
        str += "&key=" + key;
        return MD5.MD5Encode(str).toUpperCase();
    }
}

package com.riversoft.weixin.pay.util;

import com.google.common.base.Joiner;
import com.riversoft.weixin.common.util.MD5;

import java.util.Map;

/**
 * Created by exizhai on 12/1/2015.
 */
public class SignatureUtil {

    public static String sign(Map<String, Object> map, String key){
        String str = Joiner.on("&").withKeyValueSeparator("=").join(map);
        str += "&key=" + key;
        return MD5.md5Hex(str).toUpperCase();
    }
}

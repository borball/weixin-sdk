/**
 * 对公众平台发送给公众账号的消息加解密示例代码.
 *
 * @copyright Copyright (c) 1998-2014 Tencent Inc.
 */

// ------------------------------------------------------------------------

package com.riversoft.weixin.common.decrypt;

import java.security.MessageDigest;
import java.util.Arrays;

/**
 * SHA1 class
 * <p/>
 * 计算公众平台的消息签名接口.
 */
public class SHA1 {

    /**
     * 用SHA1算法生成安全签名
     *
     * @param array     字符串
     * @return 安全签名
     * @throws AesException
     */
    public static String getSHA1(String... array) throws AesException {
        StringBuffer sb = new StringBuffer();
        // 字符串排序
        Arrays.sort(array);
        for(String item: array) {
            sb.append(item);
        }
        return getSHA1(sb.toString());
    }

    public static String getSHA1(String string) throws AesException {
        // SHA1签名生成
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(string.getBytes());
            byte[] digest = md.digest();

            StringBuffer hexstr = new StringBuffer();
            String shaHex = "";
            for (int i = 0; i < digest.length; i++) {
                shaHex = Integer.toHexString(digest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexstr.append(0);
                }
                hexstr.append(shaHex);
            }
            return hexstr.toString();
        } catch (Exception e) {
            throw new AesException(AesException.ComputeSignatureError);
        }
    }
}

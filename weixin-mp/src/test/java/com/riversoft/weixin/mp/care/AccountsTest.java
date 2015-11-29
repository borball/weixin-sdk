package com.riversoft.weixin.mp.care;

import com.riversoft.weixin.mp.care.bean.Account;
import com.riversoft.weixin.mp.care.bean.AccountStatus;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.List;

/**
 * Created by exizhai on 11/25/2015.
 */
public class AccountsTest {

    @Test
    public void testCreate(){
        Accounts.defaultAccounts().add("kf1@riversoft", "kf1", md5("river2015"));
    }

    @Test
    public void testList(){
        List<Account> accounts = Accounts.defaultAccounts().list();

        Assert.assertNotNull(accounts);
    }

    @Test
    public void testListStatus(){
        List<AccountStatus> accountStatuses = Accounts.defaultAccounts().listStatus();

        Assert.assertNotNull(accountStatuses);
    }

    private String md5(String x) {
        try {
            MessageDigest m = MessageDigest.getInstance("MD5"); // 创建一个MD5消息文搞
            m.update(x.getBytes("UTF8")); // 更新被文搞描述的位元组

            byte[] s = m.digest(); // 最后更新使用位元组的被叙述的排列,然后完成文摘计算

            String result = "";

            for (int i = 0; i < s.length; i++) {
                result += Integer.toHexString((0x000000FF & s[i]) | 0xFFFFFF00).substring(6);
                // 进行十六进制转换
            }

            return result;
        } catch (Exception e) {
            return null;
        }
    }

    private String md5(byte[] bytes) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(bytes);
            BigInteger bi = new BigInteger(1, md5.digest());
            return bi.toString(16);
        } catch (Exception e) {
            return null;
        }
    }

}

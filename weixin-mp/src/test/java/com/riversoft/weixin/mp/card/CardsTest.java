package com.riversoft.weixin.mp.card;

import com.riversoft.weixin.mp.card.bean.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by exizhai on 11/28/2015.
 */
public class CardsTest {

    @Test
    public void testCreateMember(){
        Member member = new Member();
        member.setPrerogative("请加班的时候出示");
        member.setAutoActivate(true);
        member.setWxActivate(false);
        member.setSupplyBonus(false);
        member.setSupplyBalance(false);
        member.setActivateUrl("http://oa.gzriver.com/mp/card/member/activate");

        BaseInfo baseInfo = new BaseInfo();
        baseInfo.setLogoUrl("https://mmbiz.qlogo.cn/mmbiz/KicPmFdM4XbnaJkp7SD8rVjyPsoyN4xkuI6luQbMaZqIm48gaMmVojz1wz1MF8m3w2IMfxfnH7ia59UFNphjd0rg/0?wx_fmt=png");
        baseInfo.setCodeType("CODE_TYPE_BARCODE");
        baseInfo.setBrandName("创河软件");
        baseInfo.setTitle("好人卡");
        baseInfo.setColor("Color010");
        baseInfo.setNotice("请加班的时候出示");
        baseInfo.setDescription("测试使用的会员卡");
        baseInfo.setSku(new BaseInfo.Sku(20));

        BaseInfo.DateInfo dateInfo = new BaseInfo.DateInfo();
        dateInfo.setType(BaseInfo.DateInfo.DateInfoType.DATE_TYPE_FIX_TERM);
        dateInfo.setFixedTerm(60);
        dateInfo.setFixedTermBegin(0);

        baseInfo.setDateInfo(dateInfo);
        baseInfo.setServicePhone("020-38556299");

        member.setBaseInfo(baseInfo);
        Cards.defaultCards().member(member);
    }

    @Test
    public void testCreateDiscount(){
        Discount discount = new Discount();
        discount.setDiscount(10);

        BaseInfo baseInfo = new BaseInfo();
        baseInfo.setLogoUrl("https://mmbiz.qlogo.cn/mmbiz/KicPmFdM4XbnaJkp7SD8rVjyPsoyN4xkuI6luQbMaZqIm48gaMmVojz1wz1MF8m3w2IMfxfnH7ia59UFNphjd0rg/0?wx_fmt=png");
        baseInfo.setCodeType("CODE_TYPE_BARCODE");
        baseInfo.setBrandName("创河软件");
        baseInfo.setTitle("9折卡");
        baseInfo.setColor("Color010");
        baseInfo.setNotice("请加班的时候出示");
        baseInfo.setDescription("测试使用的打折卡");
        baseInfo.setSku(new BaseInfo.Sku(10));

        BaseInfo.DateInfo dateInfo = new BaseInfo.DateInfo();
        dateInfo.setType(BaseInfo.DateInfo.DateInfoType.DATE_TYPE_FIX_TERM);
        dateInfo.setFixedTerm(30);
        dateInfo.setFixedTermBegin(0);
        baseInfo.setDateInfo(dateInfo);
        baseInfo.setServicePhone("020-38556299");

        discount.setBaseInfo(baseInfo);
        Cards.defaultCards().discount(discount);
    }

    @Test
    public void testWhiteList(){
        List<String> users = new ArrayList<>();
        users.add("o7Tmfs5ezesAcMR-Uu1HrFTm0VPM");
        users.add("o7Tmfs6Bx0Tw6en7idZabXGG9Di8");
        users.add("o7Tmfs3EHCndVenva5knKxA4D3XA");
        users.add("o7Tmfs8y4gBrwzX_YXKAuL7TFrsQ");
        users.add("o7Tmfs2uEnDbaJ_rs2OejuWZPH2M");
        users.add("o7Tmfs6gPvhH9FXtFz8JR2jfeL0g");
        users.add("o7Tmfs0CkrVdq7og3sKWmot55iS8");
        users.add("o7Tmfs7pE8QMVDj9jF--Y74wjUpE");
        users.add("o7Tmfs96UDesd920Gzi0jYJPnBzQ");
        Cards.defaultCards().setWhiteList(users, null);
    }

    @Test
    public void testList(){
        List<String> list = Cards.defaultCards().list(0, 10, null);
        Assert.assertNotNull(list);
    }

    @Test
    public void testCount(){
        int count = Cards.defaultCards().count(null);
        Assert.assertTrue(count > 0);
    }

    @Test
    public void testGet(){
        //p7Tmfs0e7WUNurXRrEnaagja7SQs
        //p7Tmfs2kxdGp2YN7LX2BMzEYCI2Q
        Card card = Cards.defaultCards().get("p7Tmfs2kxdGp2YN7LX2BMzEYCI2Q");
        System.out.println(card.getCardType());
        card = Cards.defaultCards().get("p7Tmfs0e7WUNurXRrEnaagja7SQs");
        System.out.println(card.getCardType());
    }

    @Test
    public void testGetColors(){
        List<Color> colors = Cards.defaultCards().listColors();
        Assert.assertNotNull(colors);
    }

    @Test
    public void testCreateCash(){
        Cash cash = new Cash();
        BaseInfo baseInfo = new BaseInfo();
        baseInfo.setLogoUrl("https://mmbiz.qlogo.cn/mmbiz/KicPmFdM4XbnaJkp7SD8rVjyPsoyN4xkuI6luQbMaZqIm48gaMmVojz1wz1MF8m3w2IMfxfnH7ia59UFNphjd0rg/0?wx_fmt=png");
        baseInfo.setCodeType("CODE_TYPE_BARCODE");
        baseInfo.setBrandName("创河软件");
        baseInfo.setTitle("代金券");
        baseInfo.setColor("Color010");
        baseInfo.setNotice("请吃饭的时候出示");
        baseInfo.setDescription("测试使用的代金券");
        baseInfo.setSku(new BaseInfo.Sku(2));

        BaseInfo.DateInfo dateInfo = new BaseInfo.DateInfo();
        dateInfo.setType(BaseInfo.DateInfo.DateInfoType.DATE_TYPE_FIX_TIME_RANGE);
//        dateInfo.setFixedTerm(60);
//        dateInfo.setFixedTermBegin(0);
        Calendar now = Calendar.getInstance();
        dateInfo.setBeginTime(now.getTime());
        now.add(Calendar.MONTH, 2);
        dateInfo.setEndTime(now.getTime());

        baseInfo.setDateInfo(dateInfo);
        baseInfo.setServicePhone("020-38556299");

        cash.setBaseInfo(baseInfo);
        cash.setLeastCost(500);
        cash.setReduceCost(500);
        Cards.defaultCards().cash(cash);
    }

    @Test
    public void testDate(){
        Date date = new Date();
        long time = date.getTime();

        System.out.println(time);

        System.out.println(String.valueOf(time).substring(0, 9));
        System.out.println(String.valueOf(time).substring(0, 10));
    }
}

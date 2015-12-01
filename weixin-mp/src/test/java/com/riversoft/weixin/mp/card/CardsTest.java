package com.riversoft.weixin.mp.card;

import com.riversoft.weixin.mp.card.bean.BaseInfo;
import com.riversoft.weixin.mp.card.bean.Color;
import com.riversoft.weixin.mp.card.bean.Discount;
import com.riversoft.weixin.mp.card.bean.Member;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by exizhai on 11/28/2015.
 */
public class CardsTest {

    @Test
    public void testCreateMember(){
        Member member = new Member();
        member.setPrerogative("请加班的时候出示");
        member.setAutoActivate(false);
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
        dateInfo.setType(BaseInfo.DateInfo.DateInfoType.DATE_TYPE_PERMANENT);

        baseInfo.setDateInfo(dateInfo);
        baseInfo.setServicePhone("020-38556299");

        member.setBaseInfo(baseInfo);
        Cards.defaultCards().createMember(member);
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
        Cards.defaultCards().createDiscount(discount);
    }

    @Test
    public void testWhiteList(){
        List<String> users = new ArrayList<>();
        users.add("borball");
        Cards.defaultCards().setWhiteList(null, users);
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
        Cards.defaultCards().get("p7Tmfs0e7WUNurXRrEnaagja7SQs");
    }

    @Test
    public void testGetColors(){
        List<Color> colors = Cards.defaultCards().listColors();
        Assert.assertNotNull(colors);
    }
}

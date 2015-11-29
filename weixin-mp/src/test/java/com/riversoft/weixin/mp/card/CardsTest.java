package com.riversoft.weixin.mp.card;

import com.riversoft.weixin.mp.card.bean.BaseInfo;
import com.riversoft.weixin.mp.card.bean.Member;
import org.junit.Test;

/**
 * Created by exizhai on 11/28/2015.
 */
public class CardsTest {

    @Test
    public void testCreateMember(){
        Member member = new Member();
        member.setPrerogative("持白金会员卡到店消费，可享8折优惠");
        member.setAutoActivate(false);
        member.setWxActivate(false);
        member.setSupplyBonus(true);
        member.setSupplyBalance(true);
        member.setActivateUrl("http://oa.gzriver.com/mp/card/member/activate");

        BaseInfo baseInfo = new BaseInfo();
        baseInfo.setLogoUrl("https://mmbiz.qlogo.cn/mmbiz/KicPmFdM4XbnaJkp7SD8rVjyPsoyN4xkuI6luQbMaZqIm48gaMmVojz1wz1MF8m3w2IMfxfnH7ia59UFNphjd0rg/0?wx_fmt=png");
        baseInfo.setCodeType("CODE_TYPE_BARCODE");
        baseInfo.setBrandName("创河软件");
        baseInfo.setTitle("好人卡");
        baseInfo.setColor("Color010");
        baseInfo.setNotice("请出示二维码核销卡券");
        baseInfo.setDescription("不可与其他优惠同享");
        baseInfo.setSku(new BaseInfo.Sku(1000));

        BaseInfo.DateInfo dateInfo = new BaseInfo.DateInfo();
        dateInfo.setType(BaseInfo.DateInfo.DateInfoType.DATE_TYPE_PERMANENT);

        baseInfo.setDateInfo(dateInfo);
        baseInfo.setServicePhone("020-38556299");

        Cards.defaultCards().createMember(member);
    }
}

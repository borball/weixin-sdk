package com.riversoft.weixin.mp.card;

import com.riversoft.weixin.common.WxClient;
import com.riversoft.weixin.common.exception.WxRuntimeException;
import com.riversoft.weixin.common.util.JsonMapper;
import com.riversoft.weixin.mp.MpWxClientFactory;
import com.riversoft.weixin.mp.base.AppSetting;
import com.riversoft.weixin.mp.base.WxEndpoint;
import com.riversoft.weixin.mp.card.bean.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 卡券接口
 * Created by exizhai on 11/28/2015.
 */
public class Cards {

    private static Logger logger = LoggerFactory.getLogger(Cards.class);

    private WxClient wxClient;

    public static Cards defaultCards() {
        return with(AppSetting.defaultSettings());
    }

    public static Cards with(AppSetting appSetting) {
        Cards cards = new Cards();
        cards.setWxClient(MpWxClientFactory.getInstance().with(appSetting));
        return cards;
    }

    public void setWxClient(WxClient wxClient) {
        this.wxClient = wxClient;
    }

    /**
     * 创建团购券
     * @param groupon
     * @return
     */
    public String createGroupOn(Groupon groupon) {
        Card card = new Card();
        card.setCardType("GROUPON");
        card.setCard("groupon", groupon);

        return createCard(groupon);
    }

    /**
     * 创建代金券
     * @param cash
     * @return
     */
    public String createCash(Cash cash) {
        Card card = new Card();
        card.setCardType("CASH");
        card.setCard("cash", cash);

        return createCard(cash);
    }

    /**
     * 创建礼品券
     * @param gift
     * @return
     */
    public String createGift(Gift gift) {
        Card card = new Card();
        card.setCardType("GIFT");
        card.setCard("gift", gift);

        return createCard(gift);
    }

    /**
     * 创建折扣券
     * @param discount
     * @return
     */
    public String createDiscount(Discount discount) {
        Card card = new Card();
        card.setCardType("DISCOUNT");
        card.setCard("discount", discount);

        return createCard(discount);
    }

    /**
     * 创建优惠券
     * @param coupon
     * @return
     */
    public String createGift(Coupon coupon) {
        Card card = new Card();
        card.setCardType("GENERAL_COUPON");
        card.setCard("general_coupon", coupon);

        return createCard(coupon);
    }

    public String createMember(Member member) {
        Card card = new Card();
        card.setCardType("MEMBER_CARD");
        card.setCard("member_card", member);

        return createCard(member);
    }

    private String createCard(AbstractCard card) {
        Map<String, Object> request = new HashMap<>();
        request.put("card", card);

        String json = JsonMapper.nonEmptyMapper().toJson(request);
        logger.debug("create card: {}", json);

        String url = WxEndpoint.get("url.card.create");
        String response = wxClient.post(url, json);

        Map<String, Object> result = JsonMapper.defaultMapper().json2Map(response);

        if(result.containsKey("card_id")) {
            return result.get("card_id").toString();
        } else {
            throw new WxRuntimeException(999, "create card failed.");
        }
    }

    class Card {

        private Map<String, Object> card = new HashMap<>();

        public Map<String, Object> getCard() {
            return card;
        }

        public void setCard(Map<String, Object> card) {
            this.card = card;
        }

        public void setCardType(String cardType) {
            card.put("card_type", cardType);
        }

        public void setCard(String key, AbstractCard abstractCard) {
            card.put(key, abstractCard);
        }
    }
}

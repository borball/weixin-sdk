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
import java.util.List;
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
     *
     * @param groupon
     * @return
     */
    public String groupon(Groupon groupon) {
        Card card = new Card();
        card.setCardType("GROUPON");
        card.setGroupon(groupon);

        return createCard(card);
    }

    /**
     * 创建代金券
     *
     * @param cash
     * @return
     */
    public String cash(Cash cash) {
        Card card = new Card();
        card.setCardType("CASH");
        card.setCash(cash);

        return createCard(card);
    }

    /**
     * 创建礼品券
     *
     * @param gift
     * @return
     */
    public String gift(Gift gift) {
        Card card = new Card();
        card.setCardType("GIFT");
        card.setGift(gift);

        return createCard(card);
    }

    /**
     * 创建折扣券
     *
     * @param discount
     * @return
     */
    public String discount(Discount discount) {
        Card card = new Card();
        card.setCardType("DISCOUNT");
        card.setDiscount(discount);

        return createCard(card);
    }

    /**
     * 创建优惠券
     *
     * @param coupon
     * @return
     */
    public String coupon(Coupon coupon) {
        Card card = new Card();
        card.setCardType("GENERAL_COUPON");
        card.setCoupon(coupon);

        return createCard(card);
    }

    public String member(Member member) {
        Card card = new Card();
        card.setCardType("MEMBER_CARD");
        card.setMember(member);

        return createCard(card);
    }

    private String createCard(Card card) {
        String json = JsonMapper.nonDefaultMapper().toJson(new CardWrapper(card));
        logger.debug("create card: {}", json);

        String url = WxEndpoint.get("url.card.create");
        String response = wxClient.post(url, json);

        Map<String, Object> result = JsonMapper.nonDefaultMapper().json2Map(response);

        if (result.containsKey("card_id")) {
            return result.get("card_id").toString();
        } else {
            throw new WxRuntimeException(999, "create card failed.");
        }
    }

    /**
     * 获取卡片总数
     *
     * @param statusList “CARD_STATUS_NOT_VERIFY”,待审核；
     *                   “CARD_STATUS_VERIFY_FALL”,审核失败；
     *                   “CARD_STATUS_VERIFY_OK”，通过审核；
     *                   “CARD_STATUS_USER_DELETE”，卡券被用户删除；
     *                   “CARD_STATUS_USER_DISPATCH”，在公众平台投放过的卡券
     * @return
     */
    public int count(List<String> statusList) {
        Map<String, Object> request = new HashMap<>();
        request.put("offset", 0);
        request.put("count", 1);
        if (statusList != null && !statusList.isEmpty()) {
            request.put("status_list", statusList);
        }

        String url = WxEndpoint.get("url.card.list");
        String json = JsonMapper.defaultMapper().toJson(request);
        String response = wxClient.post(url, json);

        Map<String, Object> result = JsonMapper.defaultMapper().json2Map(response);
        if (result.containsKey("total_num")) {
            return (Integer) result.get("total_num");
        } else {
            throw new WxRuntimeException(999, "create card failed.");
        }
    }

    /**
     * 获取卡片列表
     *
     * @param offset
     * @param count
     * @param statusList “CARD_STATUS_NOT_VERIFY”,待审核；
     *                   “CARD_STATUS_VERIFY_FALL”,审核失败；
     *                   “CARD_STATUS_VERIFY_OK”，通过审核；
     *                   “CARD_STATUS_USER_DELETE”，卡券被用户删除；
     *                   “CARD_STATUS_USER_DISPATCH”，在公众平台投放过的卡券
     * @return
     */
    public List<String> list(int offset, int count, List<String> statusList) {
        Map<String, Object> request = new HashMap<>();
        request.put("offset", offset);
        request.put("count", count);
        if (statusList != null && !statusList.isEmpty()) {
            request.put("status_list", statusList);
        }

        String url = WxEndpoint.get("url.card.list");
        String json = JsonMapper.defaultMapper().toJson(request);
        String response = wxClient.post(url, json);

        Map<String, Object> result = JsonMapper.defaultMapper().json2Map(response);
        if (result.containsKey("card_id_list")) {
            return (List<String>) result.get("card_id_list");
        } else {
            throw new WxRuntimeException(999, "create card failed.");
        }
    }

    public Card get(String cardId) {
        String json = "{\"card_id\":\"%s\"}";
        logger.debug("get card: {}", cardId);

        String url = WxEndpoint.get("url.card.get");
        String response = wxClient.post(url, String.format(json, cardId));

        CardWrapper cardWrapper = JsonMapper.defaultMapper().fromJson(response, CardWrapper.class);
        return cardWrapper.getCard();
    }

    /**
     * 设置测试使用的白名单
     *
     * @param openIds
     * @param userNames
     */
    public void setWhiteList(List<String> openIds, List<String> userNames) {
        Map<String, Object> request = new HashMap<>();
        if (openIds != null && !openIds.isEmpty()) {
            request.put("openid", openIds);
        }
        if (userNames != null && !userNames.isEmpty()) {
            request.put("username", userNames);
        }

        String url = WxEndpoint.get("url.card.test.whitelist");
        String json = JsonMapper.defaultMapper().toJson(request);
        logger.debug("set test white list: {}", json);
        wxClient.post(url, json);
    }

    /**
     * 根据card id获取图文消息里面的content字段
     *
     * @param cardId
     * @return
     */
    String getContentByCardId(String cardId) {
        Map<String, Object> request = new HashMap<>();
        request.put("card_id", cardId);

        String url = WxEndpoint.get("url.card.mpnews.gethtml");
        String json = JsonMapper.defaultMapper().toJson(request);
        logger.debug("get mpnews content by card id: {}", json);
        String response = wxClient.post(url, json);
        Map<String, Object> result = JsonMapper.defaultMapper().json2Map(response);
        if (result.containsKey("content")) {
            return result.get("content").toString();
        } else {
            throw new WxRuntimeException(999, "get html content by card id failed.");
        }
    }

    /**
     * 获取卡券背景颜色列表
     *
     * @return
     */
    public List<Color> listColors() {
        String url = WxEndpoint.get("url.card.colors.get");
        logger.debug("list colors.");
        String response = wxClient.get(url);
        ColorWrapper colorWrapper = JsonMapper.defaultMapper().fromJson(response, ColorWrapper.class);
        return colorWrapper.getColors();
    }

    public static class ColorWrapper {

        private List<Color> colors;

        public List<Color> getColors() {
            return colors;
        }

        public void setColors(List<Color> colors) {
            this.colors = colors;
        }
    }

    public static class CardWrapper {

        private Card card;

        public CardWrapper() {
        }

        public CardWrapper(Card card) {
            this.card = card;
        }

        public Card getCard() {
            return card;
        }

        public void setCard(Card card) {
            this.card = card;
        }

    }

}

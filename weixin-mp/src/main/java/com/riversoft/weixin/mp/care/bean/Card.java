package com.riversoft.weixin.mp.care.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by exizhai on 12/5/2015.
 */
public class Card {

    @JsonProperty("card_id")
    private String cardId;

    @JsonProperty("card_ext")
    private String cardExt;

    public Card() {
    }

    public Card(String cardId) {
        this.cardId = cardId;
    }

    public Card(String cardId, String cardExt) {
        this.cardId = cardId;
        this.cardExt = cardExt;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getCardExt() {
        return cardExt;
    }

    public void setCardExt(String cardExt) {
        this.cardExt = cardExt;
    }
}

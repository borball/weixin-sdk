package com.riversoft.weixin.qy.message.json;

import com.riversoft.weixin.common.message.MsgType;
import com.riversoft.weixin.common.message.News;

/**
 * Created by exizhai on 9/26/2015.
 */
public class NewsMessage extends JsonMessage {

    private News news;

    public NewsMessage() {
        this.msgType = MsgType.news;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    public NewsMessage news(News news) {
        this.news = news;
        return this;
    }
}

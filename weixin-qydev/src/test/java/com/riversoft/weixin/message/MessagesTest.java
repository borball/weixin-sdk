package com.riversoft.weixin.message;

import com.riversoft.weixin.WxPropLoader;
import com.riversoft.weixin.message.base.Article;
import com.riversoft.weixin.message.base.News;
import com.riversoft.weixin.message.base.Text;
import com.riversoft.weixin.message.json.JsonMessage;
import com.riversoft.weixin.message.json.NewsMessage;
import com.riversoft.weixin.message.json.TextMessage;
import org.junit.Test;

/**
 * Created by exizhai on 9/28/2015.
 */
public class MessagesTest {

    WxPropLoader wxPropLoader = new WxPropLoader(this.getClass().getClassLoader().getResourceAsStream("wx-test.properties"));

    @Test
    public void testText() {
        TextMessage text = new TextMessage();
        text.setSafe(true);
        text.setToUser(testUser());
        Text content = new Text("message sent from new SDK.");
        text.setText(content);

        Messages.defaultMessages().send(text);
    }

    @Test
    public void testChineseText() {
        TextMessage text = new TextMessage();
        text.setSafe(true);
        text.setToUser(testUser());
        Text content = new Text("这是一条保密消息[测试中文]");
        text.setText(content);

        Messages.defaultMessages().send(text);
    }

    @Test
    public void testNews() {
        News news = new News();
        Article article = new Article();
        article.setTitle("测试news");
        article.setDescription("今日头条，正在调试message API, 测试是否能正常发送news类型。");
        article.setUrl("http://riversoft.com.cn/Upload/Pic/banner4.jpg");
        article.setPicUrl("http://riversoft.com.cn/Upload/Pic/banner2.jpg");
        news.add(article);

        NewsMessage newsMessage = new NewsMessage();
        newsMessage.setNews(news);
        newsMessage.setToUser(testUser());

        Messages.defaultMessages().send(newsMessage);
    }

    @Test
    public void testFluentAPI() {
        JsonMessage textMessage = new TextMessage().text("测试流式API").toUser(testUser());
        Messages.defaultMessages().send(textMessage);
    }

    @Test
    public void testMpNews() {

    }

    private String testUser() {
        return wxPropLoader.getProperty("messages.test.user");
    }

}

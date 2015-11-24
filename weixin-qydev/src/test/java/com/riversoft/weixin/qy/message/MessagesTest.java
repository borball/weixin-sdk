package com.riversoft.weixin.qy.message;

import com.riversoft.weixin.common.message.*;
import com.riversoft.weixin.qy.TestConfiguration;
import com.riversoft.weixin.qy.media.Medias;
import com.riversoft.weixin.common.media.MediaType;
import com.riversoft.weixin.qy.message.json.*;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by exizhai on 9/28/2015.
 */
public class MessagesTest {

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
        Article article1 = new Article();
        article1.setTitle("iTerm：让你的命令行也能丰富多彩");
        article1.setDescription("iTerm：让你的命令行也能丰富多彩");
        article1.setUrl("http://swiftcafe.io/2015/07/25/iterm");
        article1.setPicUrl("http://swiftcafe.io/images/iterm/1.png");
        news.add(article1);

        Article article2 = new Article();
        article2.setTitle("GitHub 漫游指南");
        article2.setDescription("GitHub 漫游指南");
        article2.setUrl("https://github.com/phodal/github-roam");
        article2.setPicUrl("http://7rf34y.com1.z0.glb.clouddn.com/user/7ec9b7dc0f494919b68d6f6be9504790/thumb");

        news.add(article2);
        Article article3 = new Article();
        article3.setTitle("[译] 为 JavaScript 程序员准备的 10 本免费书籍");
        article3.setDescription("[译] 为 JavaScript 程序员准备的 10 本免费书籍");
        article3.setUrl("http://info.9iphp.com/10-free-javascript-books-for-beginners");
        article3.setPicUrl("http://www.pinyourclient.com/wp-content/uploads/2015/01/9455413519_javascript.png");
        news.add(article3);

        Article article4 = new Article();
        article4.setTitle("疯狂 HTML + CSS + JS 中 CSS 总结");
        article4.setDescription("疯狂 HTML + CSS + JS 中 CSS 总结");
        article4.setUrl("http://mzkmzk.github.io/blog/2015/10/18/amazeing-css.markdwon");
        article4.setPicUrl("http://extjs.org.cn/screen_capture/extjswebbook/crazy-ajax-03.jpg");
        news.add(article4);

        Article article5 = new Article();
        article5.setTitle("Facebook CEO 扎克伯格用中文讲了三个故事");
        article5.setDescription("Facebook CEO 扎克伯格用中文讲了三个故事");
        article5.setUrl("http://www.cyzone.cn/a/20151024/282339.html");
        article5.setPicUrl("http://img0.pconline.com.cn/pconline/1410/23/5615376_03_thumb.jpg");
        news.add(article5);

        NewsMessage newsMessage = new NewsMessage();
        newsMessage.setNews(news);
        newsMessage.setToUser(testUser());

        Messages.defaultMessages().send(newsMessage);
    }

    @Test
    public void testFluentAPI() {
        JsonMessage textMessage = new TextMessage().text("文本消息").safe().toUser("woden|borball|xxx").toParty("1|2|3").toTag("1|2|3");
        Messages.defaultMessages().send(textMessage);
    }

    @Test
    public void testMpNews() throws IOException {
        String html5 = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("media/html5.html"));

        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("media/image.png");
        String mediaId = Medias.defaultMedias().upload(MediaType.image, inputStream, "image.png");

        MpNews mpNews = new MpNews();
        MpArticle mpArticle1 = new MpArticle();
        mpArticle1.author("woden").digest("我所理解的大数据个性化推荐").showCover().title("我所理解的大数据个性化推荐").thumbMediaId(mediaId);
        mpArticle1.content(html5).contentSourceUrl("http://www.blogchong.com/post/127.html");
        mpNews.add(mpArticle1);

        MpArticle mpArticle2 = new MpArticle();
        mpArticle2.author("woden").digest("HTML5").showCover().title("HTML5").thumbMediaId(mediaId);
        mpArticle2.content(html5).contentSourceUrl("http://www.blogchong.com/post/127.html");
        mpNews.add(mpArticle2);

        Messages.defaultMessages().send(new MpNewsMessage().mpNews(mpNews).agentId(45).toUser("@all"));
    }

    @Test
    public void testMpNewsWithMediaId() {
        Messages.defaultMessages().send(new MpNewsMediaIdMessage("2tt-ZHoEbMjF4cweRKEdpYQ0TFtfHGHoBujB-zr5am9k5k7iSNeGf2szpllfwEGw8").agentId(45).toUser("@all"));
    }

    private String testUser() {
        return TestConfiguration.getInstance().testUser();
    }

}

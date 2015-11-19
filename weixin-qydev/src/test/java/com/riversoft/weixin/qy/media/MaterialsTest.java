package com.riversoft.weixin.qy.media;

import com.riversoft.weixin.common.message.MpArticle;
import com.riversoft.weixin.common.message.MpNews;
import com.riversoft.weixin.qy.base.DefaultSettings;
import com.riversoft.weixin.qy.media.bean.*;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.InputStream;

/**
 * Created by exizhai on 10/2/2015.
 */
public class MaterialsTest {

    @Test
    public void testLifeCycle() {
        Counts counts = Materials.defaultMaterials().count();
        int imageCountBefore = counts.getImage();

        String mediaId = Materials.defaultMaterials().upload(MediaType.image, this.getClass().getClassLoader().getResourceAsStream("media/image.png"), "image.png");

        counts = Materials.defaultMaterials().count();
        int imageCountAfter = counts.getImage();

        Assert.assertEquals(imageCountAfter, imageCountBefore + 1);

        File image = Materials.defaultMaterials().download(mediaId);
        Assert.assertNotNull(image);
        Assert.assertTrue(image.exists());

        Materials.defaultMaterials().delete(mediaId);

        counts = Materials.defaultMaterials().count();
        Assert.assertEquals(imageCountBefore, counts.getImage());

    }

    @Test
    public void testCount() {
        Counts counts = Materials.defaultMaterials().count();
        Assert.assertNotNull(counts);
    }

    @Test
    public void testList() {
        Pagination pagination = new Pagination();
        pagination.setAgentId(DefaultSettings.defaultSettings().getDefaultAgent());
        pagination.setType(MediaType.image);
        pagination.setOffset(0);
        pagination.setCount(20);
        SearchResult searchResult = Materials.defaultMaterials().list(pagination);

        Assert.assertNotNull(searchResult);

        for (SearchResult.Item item : searchResult.getItems()) {
            File file = Materials.defaultMaterials().download(item.getMediaId());
            Assert.assertTrue(file.exists());
        }

        searchResult = Materials.defaultMaterials().list(pagination);

        Assert.assertNotNull(searchResult);
    }

    @Test
    public void testAddMpNews(){
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("media/image.png");
        String mediaId = Medias.defaultMedias().upload(MediaType.image, inputStream, "image.png");

        MpNews mpNews = new MpNews();
        MpArticle mpArticle1 = new MpArticle();
        mpArticle1.author("riversoft").digest("我所理解的大数据个性化推荐(1)").showCover().title("我所理解的大数据个性化推荐(1)").thumbMediaId(mediaId);
        mpArticle1.content("这里是内容，据说可以支持html").contentSourceUrl("http://www.blogchong.com/post/127.html");
        mpNews.add(mpArticle1);

        MpArticle mpArticle2 = new MpArticle();
        mpArticle2.author("riversoft").digest("我所理解的大数据个性化推荐(2)").showCover().title("我所理解的大数据个性化推荐(2)").thumbMediaId(mediaId);
        mpArticle2.content("这里是内容，据说可以支持html").contentSourceUrl("http://www.blogchong.com/post/127.html");
        mpNews.add(mpArticle2);

        String mpNewsMedia = Materials.defaultMaterials().addMpNews(45, mpNews);
        System.out.println(mpNewsMedia);
    }

}

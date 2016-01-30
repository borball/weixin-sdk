package com.riversoft.weixin.qy.media;

import com.riversoft.weixin.common.media.MaterialSearchResult;
import com.riversoft.weixin.common.media.MediaType;
import com.riversoft.weixin.common.message.MpArticle;
import com.riversoft.weixin.common.message.MpNews;
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
        Counts counts = Materials.defaultMaterials().count(45);
        int imageCountBefore = counts.getImage();

        String mediaId = Materials.defaultMaterials().upload(45, MediaType.image, this.getClass().getClassLoader().getResourceAsStream("media/image.png"), "image.png");

        counts = Materials.defaultMaterials().count(45);
        int imageCountAfter = counts.getImage();

        Assert.assertEquals(imageCountAfter, imageCountBefore + 1);

        File image = Materials.defaultMaterials().download(45, mediaId);
        Assert.assertNotNull(image);
        Assert.assertTrue(image.exists());

        Materials.defaultMaterials().delete(45, mediaId);

        counts = Materials.defaultMaterials().count(45);
        Assert.assertEquals(imageCountBefore, counts.getImage());

    }

    @Test
    public void testCount() {
        Counts counts = Materials.defaultMaterials().count(45);
        Assert.assertNotNull(counts);
    }

    @Test
    public void testList() {
        MaterialSearchResult qyMaterialSearchResult = Materials.defaultMaterials().list(45, MediaType.image, 0, 10);

        Assert.assertNotNull(qyMaterialSearchResult);

        for (MaterialSearchResult.Material item : qyMaterialSearchResult.getItems()) {
            File file = Materials.defaultMaterials().download(45, item.getMediaId());
            Assert.assertTrue(file.exists());
        }

        qyMaterialSearchResult = Materials.defaultMaterials().list(45, MediaType.image, 0, 10);

        Assert.assertNotNull(qyMaterialSearchResult);
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

    @Test
    public void testAddMpNewsImage() {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("media/image.png");
        String url = Materials.defaultMaterials().addMpNewsImage(inputStream, "testMpNewsImage.png");
        Assert.assertNotNull(url);
    }
}

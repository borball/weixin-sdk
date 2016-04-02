package com.riversoft.weixin.qy.media;

import com.riversoft.weixin.common.media.MpArticle;
import com.riversoft.weixin.common.media.MpNews;
import com.riversoft.weixin.qy.media.bean.MaterialPagination;
import com.riversoft.weixin.common.media.MediaType;
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
        Counts counts = Materials.defaultMaterials().count(84);
        int imageCountBefore = counts.getImage();

        String mediaId = Materials.defaultMaterials().upload(84, MediaType.image, this.getClass().getClassLoader().getResourceAsStream("media/image.png"), "image.png");

        counts = Materials.defaultMaterials().count(84);
        int imageCountAfter = counts.getImage();

        Assert.assertEquals(imageCountAfter, imageCountBefore + 1);

        File image = Materials.defaultMaterials().download(84, mediaId);
        Assert.assertNotNull(image);
        Assert.assertTrue(image.exists());

        Materials.defaultMaterials().delete(84, mediaId);

        counts = Materials.defaultMaterials().count(84);
        Assert.assertEquals(imageCountBefore, counts.getImage());

    }

    @Test
    public void testCount() {
        Counts counts = Materials.defaultMaterials().count(84);
        Assert.assertNotNull(counts);
    }

    @Test
    public void testList() {
        MaterialPagination qyMaterialPagination = Materials.defaultMaterials().list(84, MediaType.image, 0, 10);

        Assert.assertNotNull(qyMaterialPagination);

        for (MaterialPagination.Material item : qyMaterialPagination.getItems()) {
            File file = Materials.defaultMaterials().download(84, item.getMediaId());
            Assert.assertTrue(file.exists());
        }

        qyMaterialPagination = Materials.defaultMaterials().list(84, MediaType.image, 0, 10);

        Assert.assertNotNull(qyMaterialPagination);
    }

    @Test
    public void testListMpNews() {
        MpNewsPagination mpNewsPagination = Materials.defaultMaterials().listMpNews(84, 0, 10);
        Assert.assertNotNull(mpNewsPagination);
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

        String mpNewsMedia = Materials.defaultMaterials().addMpNews(84, mpNews);
        System.out.println(mpNewsMedia);
    }

    @Test
    public void testAddMpNewsImage() {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("media/image.png");
        String url = Materials.defaultMaterials().addMpNewsImage(inputStream, "testMpNewsImage.png");
        Assert.assertNotNull(url);
    }
}

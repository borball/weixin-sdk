package com.riversoft.weixin.mp.media;

import com.riversoft.weixin.common.media.MaterialSearchResult;
import com.riversoft.weixin.common.media.MediaType;
import com.riversoft.weixin.common.media.MpArticle;
import com.riversoft.weixin.common.media.MpNews;
import com.riversoft.weixin.mp.media.bean.Counts;
import com.riversoft.weixin.mp.media.bean.Material;
import com.riversoft.weixin.mp.media.bean.MpNewsSearchResult;
import org.junit.Assert;
import org.junit.Test;

import java.io.InputStream;

/**
 * Created by exizhai on 10/2/2015.
 */
public class MaterialsTest {

    @Test
    public void testCount() {
        Counts counts = Materials.defaultMaterials().count();
        Assert.assertNotNull(counts);
    }

    @Test
    public void testList() {
        MaterialSearchResult materialSearchResult = Materials.defaultMaterials().list(MediaType.image, 0, 10);
        Assert.assertNotNull(materialSearchResult);

        MpNewsSearchResult mpNewsSearchResult = Materials.defaultMaterials().listMpNews(0, 10);
        Assert.assertNotNull(mpNewsSearchResult);
    }

    @Test
    public void testAddImage(){
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("media/borball.jpg");
        Material material = Materials.defaultMaterials().addImage(inputStream, "borball.jpg");
        Assert.assertNotNull(material);

        String mediaId = material.getMediaId();
        String url = material.getUrl();
        System.out.println("图片ID:" + mediaId);
        System.out.println("图片URL:" + url);
    }

    @Test
    public void testAddMpNews(){
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("media/image.png");
        String mediaId = Materials.defaultMaterials().addImage(inputStream, "image.png").getMediaId();

        MpNews mpNews = new MpNews();
        MpArticle mpArticle1 = new MpArticle();
        mpArticle1.author("riversoft").digest("我所理解的大数据个性化推荐(1)").showCover().title("我所理解的大数据个性化推荐(1)").thumbMediaId(mediaId);
        mpArticle1.content("这里是内容，据说可以支持html").contentSourceUrl("http://www.blogchong.com/post/127.html");
        mpNews.add(mpArticle1);

        MpArticle mpArticle2 = new MpArticle();
        mpArticle2.author("riversoft").digest("我所理解的大数据个性化推荐(2)").showCover().title("我所理解的大数据个性化推荐(2)").thumbMediaId(mediaId);
        mpArticle2.content("这里是内容，据说可以支持html").contentSourceUrl("http://www.blogchong.com/post/127.html");
        mpNews.add(mpArticle2);

        String mpNewsMedia = Materials.defaultMaterials().addMpNews(mpNews);
        System.out.println(mpNewsMedia);
    }

}

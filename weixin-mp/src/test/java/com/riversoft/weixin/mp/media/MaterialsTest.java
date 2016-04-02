package com.riversoft.weixin.mp.media;

import com.riversoft.weixin.common.media.*;
import com.riversoft.weixin.mp.media.bean.Counts;
import com.riversoft.weixin.mp.media.bean.Material;
import com.riversoft.weixin.mp.media.bean.MaterialPagination;
import com.riversoft.weixin.mp.media.bean.MpNewsPagination;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
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
        MaterialPagination materialPagination = Materials.defaultMaterials().list(MediaType.image, 0, 10);
        Assert.assertNotNull(materialPagination);

        MpNewsPagination mpNewsPagination = Materials.defaultMaterials().listMpNews(0, 10);
        Assert.assertNotNull(mpNewsPagination);
    }

    @Test
    public void testAddImage() throws IOException {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("media/borball.jpg");
        Material material = Materials.defaultMaterials().addImage(inputStream, "borball.jpg");
        Assert.assertNotNull(material);

        String mediaId = material.getMediaId();
        String url = material.getUrl();
        System.out.println("图片ID:" + mediaId);
        System.out.println("图片URL:" + url);

        InputStream is = Materials.defaultMaterials().getImage(mediaId);

        FileUtils.copyInputStreamToFile(is, new File("borball.jpg"));
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

    @Test
    public void testAddMpNewsImage() throws IOException {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("media/tetstt.jpg");
        String url = Materials.defaultMaterials().addMpNewsImage(inputStream, "tetstt.jpg");
        System.out.println("图片URL:" + url);
    }

    @Test
    public void testVoice() {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("media/test.mp3");
        Material material = Materials.defaultMaterials().addVoice(inputStream, "test.mp3");
        Assert.assertNotNull(material);

        InputStream is = Materials.defaultMaterials().getVoice(material.getMediaId());
        Assert.assertNotNull(is);
    }

}

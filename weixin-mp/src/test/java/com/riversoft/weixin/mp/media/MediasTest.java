package com.riversoft.weixin.mp.media;

import com.riversoft.weixin.common.media.Media;
import com.riversoft.weixin.common.media.MediaType;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.InputStream;

/**
 * Created by exizhai on 10/12/2015.
 */
public class MediasTest {

    @Test
    public void testImage() {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("media/borball.jpg");
        Media media = Medias.defaultMedias().upload(MediaType.image, inputStream, "borball.jpg");
        Assert.assertNotNull(media);

        File file = Medias.defaultMedias().download(media.getMediaId());
        Assert.assertTrue(file.exists());
    }

    @Test
    public void testVoice() throws InterruptedException {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("media/test.mp3");
        Media media = Medias.defaultMedias().upload(MediaType.voice, inputStream, "test.mp3");
        Assert.assertNotNull(media);

        File file = Medias.defaultMedias().download(media.getMediaId());
        Assert.assertTrue(file.exists());

        // 暂停2s等待微信转码
        Thread.sleep(2000);

        file = Medias.defaultMedias().downloadHDVoice(media.getMediaId());
        Assert.assertTrue(file.exists());
    }

    @Test
    public void testVideo() {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("media/test.mp4");
        Media media = Medias.defaultMedias().upload(MediaType.video, inputStream, "test.mp4");
        Assert.assertNotNull(media);

        File file = Medias.defaultMedias().download(media.getMediaId());
        Assert.assertTrue(file.exists());
    }

}

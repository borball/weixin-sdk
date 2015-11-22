package com.riversoft.weixin.qy.media;

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
    public void testUpload() {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("media/image.png");
        String mediaId = Medias.defaultMedias().upload(MediaType.image, inputStream, "image.png");
        Assert.assertNotNull(mediaId);
    }

    @Test
    public void testDownload() {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("media/image.png");
        String mediaId = Medias.defaultMedias().upload(MediaType.image, inputStream, "image.png");
        Assert.assertNotNull(mediaId);

        File file = Medias.defaultMedias().download(mediaId);
        Assert.assertTrue(file.exists());
    }


    @Test
    public void testVoice() {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("media/test.mp3");
        String media = Medias.defaultMedias().upload(MediaType.voice, inputStream, "test.mp3");
        Assert.assertNotNull(media);

        File file = Medias.defaultMedias().download(media);
        Assert.assertTrue(file.exists());
    }

    @Test
    public void testVideo() {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("media/test.mp4");
        String media = Medias.defaultMedias().upload(MediaType.video, inputStream, "test.mp4");
        Assert.assertNotNull(media);

        File file = Medias.defaultMedias().download(media);
        Assert.assertTrue(file.exists());
    }
}

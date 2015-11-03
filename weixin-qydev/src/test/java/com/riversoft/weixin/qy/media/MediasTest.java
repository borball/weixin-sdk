package com.riversoft.weixin.qy.media;

import com.riversoft.weixin.qy.media.bean.MediaType;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.InputStream;

/**
 * Created by exizhai on 10/12/2015.
 */
public class MediasTest {

    @Test
    public void testUpload(){
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("media/image.png");
        String mediaId = Medias.defaultMedias().upload(MediaType.image, inputStream, "png");
        Assert.assertNotNull(mediaId);
    }

    @Test
    public void testDownload(){
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("media/image.png");
        String mediaId = Medias.defaultMedias().upload(MediaType.image, inputStream, "png");
        Assert.assertNotNull(mediaId);

        File file = Medias.defaultMedias().download(mediaId);
        Assert.assertTrue(file.exists());
    }
}

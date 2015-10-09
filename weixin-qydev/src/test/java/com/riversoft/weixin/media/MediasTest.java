package com.riversoft.weixin.media;

import com.riversoft.weixin.base.Settings;
import com.riversoft.weixin.media.bean.Counts;
import com.riversoft.weixin.media.bean.MediaType;
import com.riversoft.weixin.media.bean.Pagination;
import com.riversoft.weixin.media.bean.SearchResult;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

/**
 * Created by exizhai on 10/2/2015.
 */
public class MediasTest {

    @Test
    public void testLifeCycle() {
        Counts counts = Medias.defaultMedias().count(Settings.buildIn().getDefaultAgent());
        int imageCountBefore = counts.getImage();

        String mediaId = Medias.defaultMedias().materialUpload(Settings.buildIn().getDefaultAgent(),
                MediaType.image, this.getClass().getClassLoader().getResourceAsStream("media/image.png"), "png");

        counts = Medias.defaultMedias().count(Settings.buildIn().getDefaultAgent());
        int imageCountAfter = counts.getImage();

        Assert.assertEquals(imageCountAfter, imageCountBefore + 1);

        File image = Medias.defaultMedias().materialDownload(Settings.buildIn().getDefaultAgent(), mediaId);
        Assert.assertNotNull(image);
        Assert.assertTrue(image.exists());

        Medias.defaultMedias().materialDelete(Settings.buildIn().getDefaultAgent(), mediaId);

        counts = Medias.defaultMedias().count(Settings.buildIn().getDefaultAgent());
        Assert.assertEquals(imageCountBefore, counts.getImage());

    }

    @Test
    public void testCount() {
        Counts counts = Medias.defaultMedias().count(Settings.buildIn().getDefaultAgent());
        Assert.assertNotNull(counts);
    }

    @Test
    public void testList() {
        Pagination pagination = new Pagination();
        pagination.setAgentId(Settings.buildIn().getDefaultAgent());
        pagination.setType(MediaType.image);
        pagination.setOffset(0);
        pagination.setCount(20);
        SearchResult searchResult = Medias.defaultMedias().list(pagination);

        Assert.assertNotNull(searchResult);

        for (SearchResult.Item item : searchResult.getItems()) {
            Medias.defaultMedias().materialDelete(Settings.buildIn().getDefaultAgent(), item.getMediaId());
        }

        searchResult = Medias.defaultMedias().list(pagination);

        Assert.assertNotNull(searchResult);
    }
}

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
public class MaterialsTest {

    @Test
    public void testLifeCycle() {
        Counts counts = Materials.defaultMaterials().count();
        int imageCountBefore = counts.getImage();

        String mediaId = Materials.defaultMaterials().upload(MediaType.image, this.getClass().getClassLoader().getResourceAsStream("media/image.png"), "png");

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
        pagination.setAgentId(Settings.buildIn().getDefaultAgent());
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
}

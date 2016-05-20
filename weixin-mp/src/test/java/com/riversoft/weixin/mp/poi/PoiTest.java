package com.riversoft.weixin.mp.poi;

import com.riversoft.weixin.common.util.JsonMapper;
import com.riversoft.weixin.mp.poi.bean.Business;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @borball on 5/19/2016.
 */
public class PoiTest {

    @Test
    public void testList(){
        List<Business> list = Pois.defaultPois().list(0, 10);
        Assert.assertNotNull(list);
    }

    @Test
    public void testFormat(){
        Pois.BusinessListWrapper wrapper = new Pois.BusinessListWrapper();
        List<Pois.BusinessWrapper> businesses = new ArrayList<>();
        Business business = new Business();
        business.setAddress("add");
        business.setCategories(new String[]{"1", "2"});
        business.setProvince("pro");
        Business.Photo photo = new Business.Photo();
        photo.setUrl("http://ccc.com");
        List<Business.Photo> photos = new ArrayList<>();
        photos.add(photo);
        business.setPhotoList(photos);
        Pois.BusinessWrapper businessWrapper = new Pois.BusinessWrapper();
        businessWrapper.setBusiness(business);
        businesses.add(businessWrapper);
        wrapper.setBusinesses(businesses);

        System.out.println(JsonMapper.defaultMapper().toJson(wrapper));
    }
}

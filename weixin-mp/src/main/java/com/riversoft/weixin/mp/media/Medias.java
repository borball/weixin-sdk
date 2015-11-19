package com.riversoft.weixin.mp.media;

import com.riversoft.weixin.common.WxClient;
import com.riversoft.weixin.common.media.Counts;
import com.riversoft.weixin.common.media.MediaType;
import com.riversoft.weixin.common.exception.WxRuntimeException;
import com.riversoft.weixin.common.util.JsonMapper;
import com.riversoft.weixin.mp.MpWxClientFactory;
import com.riversoft.weixin.mp.base.AppSetting;
import com.riversoft.weixin.mp.base.WxEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Map;

/**
 * Created by exizhai on 11/12/2015.
 */
public class Medias {

    private static Logger logger = LoggerFactory.getLogger(Medias.class);

    private WxClient wxClient;

    public static Medias defaultMedias() {
        return with(AppSetting.defaultSettings());
    }

    public static Medias with(AppSetting appSetting) {
        Medias medias = new Medias();
        medias.setWxClient(MpWxClientFactory.getInstance().with(appSetting));
        return medias;
    }

    public void setWxClient(WxClient wxClient) {
        this.wxClient = wxClient;
    }

    public String upload(MediaType type, InputStream inputStream, String fileName) {
        String url = WxEndpoint.get("url.material.binary.upload");

        String response = wxClient.post(url, inputStream, fileName);

        Map<String, Object> result = JsonMapper.defaultMapper().json2Map(response);
        if (result.containsKey("errcode") && "0".equals(result.get("errcode").toString())) {
            return result.get("media_id").toString();
        } else {
            logger.warn("material upload failed: {}", response);
            throw new WxRuntimeException(998, response);
        }
    }

    public Counts count() {
        String response = wxClient.get(String.format(WxEndpoint.get("url.material.count")));
        return JsonMapper.defaultMapper().fromJson(response, Counts.class);
    }

}

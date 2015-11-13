package com.riversoft.weixin.qy.media;

import com.riversoft.weixin.common.WxClient;
import com.riversoft.weixin.qy.QyWxClientFactory;
import com.riversoft.weixin.qy.base.CorpSetting;
import com.riversoft.weixin.qy.base.DefaultSettings;
import com.riversoft.weixin.qy.exception.WxRuntimeException;
import com.riversoft.weixin.qy.media.bean.MediaType;
import com.riversoft.weixin.qy.util.JsonMapper;
import com.riversoft.weixin.qy.base.WxEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.InputStream;
import java.util.Map;

/**
 * Created by exizhai on 10/1/2015.
 */
public class Medias {

    private static Logger logger = LoggerFactory.getLogger(Medias.class);

    private WxClient wxClient;

    public void setWxClient(WxClient wxClient) {
        this.wxClient = wxClient;
    }

    public static Medias defaultMedias() {
        return with(DefaultSettings.defaultSettings().getCorpSetting());
    }

    public static Medias with(CorpSetting corpSetting) {
        Medias medias = new Medias();
        medias.setWxClient(QyWxClientFactory.getInstance().with(corpSetting));
        return medias;
    }

    public String upload(MediaType type, InputStream inputStream, String extName) {
        String url = WxEndpoint.get("url.media.upload");

        String response = wxClient.post(String.format(url, type.name()), inputStream, extName);

        //为什么这个成功返回的response没有error code，和其他的格格不入,临时工弄的?
        Map<String, Object> result = JsonMapper.defaultMapper().json2Map(response);
        if (result.containsKey("media_id")) {
            return result.get("media_id").toString();
        } else {
            logger.warn("media upload failed: {}", response);
            throw new WxRuntimeException(998, response);
        }
    }

    public File download(String mediaId) {
        return wxClient.download(String.format(WxEndpoint.get("url.media.get"), mediaId));
    }

}

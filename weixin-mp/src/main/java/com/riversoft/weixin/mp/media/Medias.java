package com.riversoft.weixin.mp.media;

import com.riversoft.weixin.common.WxClient;
import com.riversoft.weixin.common.exception.WxRuntimeException;
import com.riversoft.weixin.common.media.MediaType;
import com.riversoft.weixin.common.util.JsonMapper;
import com.riversoft.weixin.mp.MpWxClientFactory;
import com.riversoft.weixin.mp.base.AppSetting;
import com.riversoft.weixin.mp.base.WxEndpoint;
import com.riversoft.weixin.mp.media.bean.MediaUploadResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
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

    private MediaUploadResult upload(MediaType type, InputStream inputStream, String fileName) {
        String url = WxEndpoint.get("url.media.upload");
        String response = wxClient.post(String.format(url, type.name()), inputStream, fileName);
        logger.debug("upload media response: {}", response);

        Map<String, Object> result = JsonMapper.defaultMapper().json2Map(response);
        if (result.containsKey("media_id")) {
            return JsonMapper.defaultMapper().fromJson(response, MediaUploadResult.class);
        } else {
            logger.warn("media upload failed: {}", response);
            throw new WxRuntimeException(999, response);
        }
    }

    private MediaUploadResult addImage(InputStream inputStream, String fileName) {
        return upload(MediaType.image, inputStream, fileName);
    }

    private MediaUploadResult addVoice(InputStream inputStream, String fileName) {
        return upload(MediaType.voice, inputStream, fileName);
    }

    private MediaUploadResult addVideo(InputStream inputStream, String fileName) {
        return upload(MediaType.video, inputStream, fileName);
    }

    private MediaUploadResult addThumb(InputStream inputStream, String fileName) {
        return upload(MediaType.thumb, inputStream, fileName);
    }

    public File download(String mediaId) {
        return wxClient.download(String.format(WxEndpoint.get("url.media.get"), mediaId));
    }

}

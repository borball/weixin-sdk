package com.riversoft.weixin.app.media;

import com.riversoft.weixin.app.AppWxClientFactory;
import com.riversoft.weixin.app.base.AppSetting;
import com.riversoft.weixin.app.base.WxEndpoint;
import com.riversoft.weixin.common.WxClient;
import com.riversoft.weixin.common.exception.WxRuntimeException;
import com.riversoft.weixin.common.media.Media;
import com.riversoft.weixin.common.media.MediaType;
import com.riversoft.weixin.common.util.JsonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.InputStream;
import java.util.Map;

/**
 * 临时素材管理
 * @borball on 12/29/2016.
 */
public class Medias {

    private static Logger logger = LoggerFactory.getLogger(Medias.class);

    private WxClient wxClient;

    public static Medias defaultMedias() {
        return with(AppSetting.defaultSettings());
    }

    public static Medias with(AppSetting appSetting) {
        Medias medias = new Medias();
        medias.setWxClient(AppWxClientFactory.getInstance().with(appSetting));
        return medias;
    }

    public void setWxClient(WxClient wxClient) {
        this.wxClient = wxClient;
    }

    /**
     * 上传临时图片
     *
     * @param type        临时素材类型：只能是 图片
     * @param inputStream 临时素材流
     * @param fileName    临时素材返回临时素材meta信息文件名
     * @return
     */
    public Media upload(MediaType type, InputStream inputStream, String fileName) {
        if (type != MediaType.image) {
            throw new WxRuntimeException(999, "unsupported media type: " + type.name());
        }
        String url = WxEndpoint.get("url.media.upload");
        String response = wxClient.post(String.format(url, type.name()), inputStream, fileName);
        logger.debug("upload media response: {}", response);

        Map<String, Object> result = JsonMapper.defaultMapper().json2Map(response);
        if (result.containsKey("media_id")) {
            return JsonMapper.defaultMapper().fromJson(response, Media.class);
        } else {
            logger.warn("media upload failed: {}", response);
            throw new WxRuntimeException(999, response);
        }
    }

    /**
     * 下载media
     *
     * @param mediaId media id
     * @return 文件
     */
    public File download(String mediaId) {
        return wxClient.download(String.format(WxEndpoint.get("url.media.get"), mediaId));
    }

}

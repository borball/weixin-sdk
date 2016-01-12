package com.riversoft.weixin.qy.media;

import com.riversoft.weixin.common.WxClient;
import com.riversoft.weixin.common.exception.WxRuntimeException;
import com.riversoft.weixin.common.media.MediaType;
import com.riversoft.weixin.common.util.JsonMapper;
import com.riversoft.weixin.qy.QyWxClientFactory;
import com.riversoft.weixin.qy.base.CorpSetting;
import com.riversoft.weixin.qy.base.WxEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.InputStream;
import java.util.Map;

/**
 * 临时素材管理
 * Created by exizhai on 10/1/2015.
 */
public class Medias {

    private static Logger logger = LoggerFactory.getLogger(Medias.class);

    private WxClient wxClient;

    public static Medias defaultMedias() {
        return with(CorpSetting.defaultSettings());
    }

    public static Medias with(CorpSetting corpSetting) {
        Medias medias = new Medias();
        medias.setWxClient(QyWxClientFactory.getInstance().with(corpSetting));
        return medias;
    }

    public void setWxClient(WxClient wxClient) {
        this.wxClient = wxClient;
    }

    /**
     * 上传临时图片，语音，视频和普通文件
     *
     * @param type        临时素材类型：只能是 图片，语音，视频和普通文件
     * @param inputStream 临时素材流
     * @param fileName    临时素材文件名
     * @return 返回临时素材metaId
     */
    public String upload(MediaType type, InputStream inputStream, String fileName) {
        if (type == MediaType.mpnews) {
            throw new com.riversoft.weixin.common.exception.WxRuntimeException(999, "unsupported media type: " + type.name());
        }
        String url = WxEndpoint.get("url.media.upload");

        String response = wxClient.post(String.format(url, type.name()), inputStream, fileName);

        //为什么这个成功返回的response没有error code，和其他的格格不入,临时工弄的?
        Map<String, Object> result = JsonMapper.defaultMapper().json2Map(response);
        if (result.containsKey("media_id")) {
            return result.get("media_id").toString();
        } else {
            logger.warn("media upload failed: {}", response);
            throw new WxRuntimeException(998, response);
        }
    }

    /**
     * 下载图片，语音，视频和普通文件
     *
     * @param mediaId media id
     * @return 文件
     */
    public File download(String mediaId) {
        return wxClient.download(String.format(WxEndpoint.get("url.media.get"), mediaId));
    }

}

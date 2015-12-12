package com.riversoft.weixin.mp.media;

import com.riversoft.weixin.common.WxClient;
import com.riversoft.weixin.common.exception.WxRuntimeException;
import com.riversoft.weixin.common.media.Media;
import com.riversoft.weixin.common.media.MediaType;
import com.riversoft.weixin.common.util.JsonMapper;
import com.riversoft.weixin.mp.MpWxClientFactory;
import com.riversoft.weixin.mp.base.AppSetting;
import com.riversoft.weixin.mp.base.WxEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.InputStream;
import java.util.Map;

/**
 * 临时素材管理
 * 1、对于临时素材，每个素材（media_id）会在开发者上传或粉丝发送到微信服务器3天后自动删除（
 * 所以用户发送给开发者的素材，若开发者需要，应尽快下载到本地），以节省服务器资源。
 * 2、media_id是可复用的。
 * 3、素材的格式大小等要求与公众平台官网一致。
 * 具体是，图片大小不超过2M，支持bmp/png/jpeg/jpg/gif格式，
 * 语音大小不超过5M，长度不超过60秒，支持mp3/wma/wav/amr格式
 * <p/>
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

    /**
     * 上传临时图片，语音，视频和缩略图
     *
     * @param type        临时素材类型：只能是 图片，语音，视频和缩略图
     * @param inputStream 临时素材流
     * @param fileName    临时素材文件名
     * @return 返回临时素材meta信息
     */
    public Media upload(MediaType type, InputStream inputStream, String fileName) {
        if (type == MediaType.file || type == MediaType.mpnews) {
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
     * 下载图片，语音和缩略图，不支持视频文件的下载
     *
     * @param mediaId media id
     * @return 文件
     */
    public File download(String mediaId) {
        return wxClient.download(String.format(WxEndpoint.get("url.media.get"), mediaId));
    }

}

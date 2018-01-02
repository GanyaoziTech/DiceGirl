package tech.ganyaozi.bean.message;

import tech.ganyaozi.bean.BaseWeixinMessage;

/**
 * 图片类消息
 *
 * @author Derek.P.Dai[derek.dai@corp.netease.com]
 **/
public class PictureWeixinMessage extends BaseWeixinMessage {
    /**
     * 图片链接
     */
    private String PicUrl;
    private String MediaId;

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

}

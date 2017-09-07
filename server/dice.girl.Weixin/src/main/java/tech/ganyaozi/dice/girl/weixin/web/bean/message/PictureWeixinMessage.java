package tech.ganyaozi.dice.girl.weixin.web.bean.message;

import tech.ganyaozi.dice.girl.weixin.web.bean._BaseWeixinMessage;

/**
 * 图片类消息
 *
 * @author Derek.P.Dai[derek.dai@corp.netease.com]
 **/
public class PictureWeixinMessage extends _BaseWeixinMessage {
    // 图片链接
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

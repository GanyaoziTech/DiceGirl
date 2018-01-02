package tech.ganyaozi.bean.message;

import tech.ganyaozi.bean.BaseWeixinMessage;

/**
 * 类名: VoiceMessage </br>
 * 描述: 请求消息之语音消息 </br>
 *
 * @author Derek.P.Dai[derek.dai@corp.netease.com]
 **/
public class VoiceWeixinMessage extends BaseWeixinMessage {


    /**
     * 媒体ID
     */
    private String MediaId;
    /**
     * 语音格式
     */
    private String Format;

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

    public String getFormat() {
        return Format;
    }

    public void setFormat(String format) {
        Format = format;
    }
}

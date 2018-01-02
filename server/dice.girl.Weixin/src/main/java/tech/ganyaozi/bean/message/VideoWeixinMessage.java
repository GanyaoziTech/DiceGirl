package tech.ganyaozi.bean.message;

/**
 * 类名: VideoMessage </br>
 * 描述: 请求消息之视频消息 </br>
 *
 * @author Derek.P.Dai[derek.dai@corp.netease.com]
 **/
public class VideoWeixinMessage {

    /**
     * 媒体ID
     */
    private String MediaId;
    /**
     * 语音格式
     */
    private String ThumbMediaId;

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

    public String getThumbMediaId() {
        return ThumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        ThumbMediaId = thumbMediaId;
    }

}

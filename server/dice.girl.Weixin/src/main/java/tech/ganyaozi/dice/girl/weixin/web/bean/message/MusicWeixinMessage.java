package tech.ganyaozi.dice.girl.weixin.web.bean.message;

import tech.ganyaozi.dice.girl.weixin.web.bean._BaseWeixinMessage;

/**
 * @author Derek.P.Dai[derek.dai@corp.netease.com]
 **/
public class MusicWeixinMessage extends _BaseWeixinMessage {

    private Music Music;

    public Music getMusic() {
        return Music;
    }

    public void setMusic(Music music) {
        this.Music = music;
    }


    public class Music {
        // 音乐标题
        private String title;
        // 音乐描述
        private String description;
        // 音乐链接
        private String musicUrl;
        // 高质量音乐链接，WIFI环境优先使用该链接播放音乐
        private String hQMusicUrl;
        // 缩略图的媒体id，通过上传多媒体文件得到的id
        private String thumbMediaId;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getMusicUrl() {
            return musicUrl;
        }

        public void setMusicUrl(String musicUrl) {
            this.musicUrl = musicUrl;
        }

        public String gethQMusicUrl() {
            return hQMusicUrl;
        }

        public void sethQMusicUrl(String hQMusicUrl) {
            this.hQMusicUrl = hQMusicUrl;
        }

        public String getThumbMediaId() {
            return thumbMediaId;
        }

        public void setThumbMediaId(String thumbMediaId) {
            this.thumbMediaId = thumbMediaId;
        }
    }
}

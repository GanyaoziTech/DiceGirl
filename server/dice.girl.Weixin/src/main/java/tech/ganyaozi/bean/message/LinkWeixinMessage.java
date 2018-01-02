package tech.ganyaozi.bean.message;

/**
 * 类名: LinkMessage </br>
 * 描述: 请求消息之链接消息 </br>
 *
 * @author Derek.P.Dai[derek.dai@corp.netease.com]
 **/
public class LinkWeixinMessage {
    /**
     * 消息标题
     */
    private String Title;
    /**
     * 消息描述
     */
    private String Description;
    /**
     * 消息链接
     */
    private String Url;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        this.Description = description;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        this.Url = url;
    }
}

package tech.ganyaozi.bean.message;


import tech.ganyaozi.bean.BaseWeixinMessage;

/**
 * 文本消息
 *
 * @author Derek.P.Dai[dp419936514@gmail.com]
 **/
public class TextWeixinMessage extends BaseWeixinMessage {

    /**
     * 消息内容
     */
    private String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}

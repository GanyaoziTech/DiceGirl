package tech.ganyaozi.service.wechat;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.util.TextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tech.ganyaozi.utils.EmojiConverter;
import tech.ganyaozi.utils.HttpClientPool;

import javax.annotation.Resource;
import java.io.IOException;

import static tech.ganyaozi.bean.consts.WechatConsts.WX_MSG_URL;

/**
 * 提供公众号相关的API
 *
 * @author Derek.P.Dai[dp419936514@gmail.com] at 2018/5/22 11:01
 **/
@Service
public class WechatApiService {
    private static final Logger logger = LoggerFactory.getLogger(WechatApiService.class);
    private static final String TAG_TO_USER = "touser";
    private static final String TAG_MSG_TYPE = "msgtype";
    private static final int DEFAULT_RETRY_TIMES = 2;
    @Resource
    private AccessTokenService accessTokenService;
    @Resource
    private EmojiConverter emojiConverter;

    public void replyText(String openId, String text) {
        if (TextUtils.isEmpty(text)) {
            return;
        }
        sendText(openId, text);
    }

    private void sendText(String openId, String text) {
        JSONObject body = new JSONObject();
        body.put("content", emojiConverter.convertNim(text));

        JSONObject json = new JSONObject();
        json.put(TAG_TO_USER, openId);
        json.put(TAG_MSG_TYPE, "text");
        json.put("text", body);

        String sendStr = json.toJSONString();

        replyMessage(sendStr, "replyText");
    }

    private String msgUrl() {
        return WX_MSG_URL + "?access_token=" + accessTokenService.getAccessToken();
    }

    private void replyMessage(String sendStr, String func) {

        String msgUrl = msgUrl();
        try {
            String ret = null;
            for (int i = 0; i <= DEFAULT_RETRY_TIMES; i++) {
                try {
                    ret = HttpClientPool.getInstance().post(msgUrl, sendStr);
                } catch (IOException ex) {
                    ex.printStackTrace();
                    logger.warn("post request exception: " + ex);
                }
                if (ret != null) break;
            }

            if (ret == null) {
                logger.warn(String.format("[wx] failed and retry !! sendStr = %s", sendStr));
            }
            logger.debug(String.format("[%s] url=%s, send=%s, ret=%s", func, msgUrl, sendStr, ret));
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.warn("replyMessage error: " + ex.toString());
        }
    }
}

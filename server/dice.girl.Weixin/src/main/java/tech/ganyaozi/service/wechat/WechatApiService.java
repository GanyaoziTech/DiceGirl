package tech.ganyaozi.service.wechat;

import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import org.apache.http.util.TextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.ganyaozi.utils.EmojiConverter;
import tech.ganyaozi.utils.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import static tech.ganyaozi.bean.consts.WechatConsts.WX_MSG_URL;

/**
 * 提供公众号相关的API
 *
 * @author Derek.P.Dai[dp419936514@gmail.com] at 2018/5/22 11:01
 **/
@Service
public class WechatApiService {

    private static final Logger loggerException = LoggerFactory.getLogger(WechatApiService.class);
    private static final Logger loggerBusiness = LoggerFactory.getLogger("business");

    private static final String TAG_TO_USER = "touser";
    private static final String TAG_MSG_TYPE = "msgtype";


    private final AccessTokenService accessTokenService;
    private final EmojiConverter emojiConverter;

    @Autowired
    public WechatApiService(AccessTokenService accessTokenService, EmojiConverter emojiConverter) {
        this.accessTokenService = accessTokenService;
        this.emojiConverter = emojiConverter;
    }

    /**
     * 回复用户消息，当且仅当是服务号的时候可以使用
     *
     * @param openId open id
     * @param text   回复内容
     */
    public void replyText(String openId, String text) {
        loggerBusiness.info("reply text to user : {}, text : {} ", openId, text);
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

        replyMessage(sendStr);
    }

    private String msgUrl() {
        return WX_MSG_URL + "?access_token=" + accessTokenService.getAccessToken();
    }

    private void replyMessage(String sendStr) {
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), sendStr);

        Request request = new Request.Builder()
                .url(msgUrl())
                .post(body)
                .build();

        try (Response response = OkHttpUtils.getClient().newCall(request).execute()) {
            if (response != null) {
                loggerBusiness.debug(String.format("url=%s, send=%s, ret=%s", msgUrl(), sendStr, response.toString()));
            } else {
                loggerBusiness.warn(String.format("[wx] failed and retry !! sendStr = %s", sendStr));
            }
        } catch (Exception e) {
            loggerException.warn("", e);
        }

    }
}

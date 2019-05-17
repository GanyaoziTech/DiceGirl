package tech.ganyaozi.service.wechat;

import com.alibaba.fastjson.JSONObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tech.ganyaozi.bean.consts.WechatConsts;
import tech.ganyaozi.redis.RedisService;
import tech.ganyaozi.utils.OkHttpUtils;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * 获取公众号access token并在redis中存储90分钟
 *
 * @author Derek.P.Dai[dp419936514@gmail.com]
 * @date 2017/10/11 10:20
 **/
@Service
public class AccessTokenService {

    private static final Logger loggerException = LoggerFactory.getLogger(AccessTokenService.class);

    private static final String GET_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token";
    private static final String GET_JS_API_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket";

    private static final String REQUEST_PARAM_GRANT_TYPE = "grant_type";
    private static final String REQUEST_PARAM_APP_ID = "appid";
    private static final String REQUEST_PARAM_APP_SECRET = "secret";
    private static final String REQUEST_PARAM_GET_TICKET_TYPE = "type";

    private static final String GET_TICKET_TYPE_VALUE = "jsapi";
    private static final String GRANT_TYPE_VALUE = "client_credential";
    private static final String RESPONSE_ACCESS_TOKEN_NAME = "access_token";
    private static final String RESPONSE_JS_API_TOKEN_NAME = "ticket";
    private static final String RESPONSE_ERR_CODE_NAME = "errcode";

    /**
     * access的过期时间，微信端最大值为7200s，该公众号默认5400s（90min）取一次
     */
    private static final Integer ACCESS_TOKEN_EXPIRE_TIME = 5400;


    private static final String ACCESS_TOKEN_REDIS_KEY = "gs_wechat_access_token";
    private static final String JS_API_TICKET_REDIS_KEY = "gs_wechat_js_api_ticket";

    @Value("${wechat-app-id}")
    private String appId;
    @Value("${wechat-app-secret}")
    private String appSecret;

    @Resource
    private RedisService redisService;

    /**
     * 获取access token
     */
    public String getAccessToken() {
        String accessToken = redisService.forString().opsForValue().get(ACCESS_TOKEN_REDIS_KEY);
        if (accessToken == null) {
            if (updateAccessToken()) {
                return redisService.forString().opsForValue().get(ACCESS_TOKEN_REDIS_KEY);
            } else {
                throw new RuntimeException("cannot get new access token !! Please check your configuration !!\n " +
                        "app-id : " + appId);
            }
        }
        return accessToken;
    }

    /**
     * 调用微信API，获取新的Access Token
     * 注意，这会覆盖掉旧的token
     *
     * @return true if update success
     */
    private boolean updateAccessToken() {
        loggerException.warn("\n===================================================\n" +
                "||   Update ACCESS TOKEN                      !! ||\n" +
                "===================================================\n");

        String url = GET_ACCESS_TOKEN_URL +
                "?" + REQUEST_PARAM_GRANT_TYPE + "=" + GRANT_TYPE_VALUE +
                "&" + REQUEST_PARAM_APP_ID + "=" + appId +
                "&" + REQUEST_PARAM_APP_SECRET + "=" + appSecret;
        Request request = new Request.Builder()
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .url(url)
                .get()
                .build();
        String accessToken = null;
        try (Response response = OkHttpUtils.getClient().newCall(request).execute()) {
            ResponseBody responseBody = response.body();
            if (responseBody != null) {
                String resStr = responseBody.string();
                JSONObject json = JSONObject.parseObject(resStr);
                accessToken = json.getString(RESPONSE_ACCESS_TOKEN_NAME);
            }
        } catch (Exception e) {
            loggerException.error("refreshUserAccessToken exception!", e);
        }
        if (accessToken != null) {
            redisService.forString().opsForValue().set(ACCESS_TOKEN_REDIS_KEY, accessToken, ACCESS_TOKEN_EXPIRE_TIME, TimeUnit.SECONDS);
            return true;
        }
        return false;
    }


    /**
     * 获取access token
     */
   public String getJsApiTicket() {
        String ticket = redisService.forString().opsForValue().get(JS_API_TICKET_REDIS_KEY);
        if (ticket == null) {
            if (!updateJsApiTicket()) {
                throw new RuntimeException("cannot get new api ticket !! Please check your configuration !!\n " +
                        "app-id : " + appId);
            } else {
                return redisService.forString().opsForValue().get(JS_API_TICKET_REDIS_KEY);
            }
        }
        return ticket;
    }

    /**
     * 更新js api所需的ticket
     */
    private boolean updateJsApiTicket() {
        loggerException.warn("\n===================================================\n" +
                "||   Update JS API TICKET                     !! ||\n" +
                "===================================================\n");
        String accessToken = getAccessToken();
        String jsApiTicket = null;
        Integer expiresIn = null;
        String url = GET_JS_API_URL +
                "?" + REQUEST_PARAM_GET_TICKET_TYPE + "=" + GET_TICKET_TYPE_VALUE +
                "&" + RESPONSE_ACCESS_TOKEN_NAME + "=" + accessToken;
        Request request = new Request.Builder()
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .url(url)
                .get()
                .build();
        try (Response response = OkHttpUtils.getClient().newCall(request).execute()) {
            ResponseBody responseBody = response.body();
            if (responseBody != null) {
                String resStr = responseBody.string();
                JSONObject json = JSONObject.parseObject(resStr);
                if (json.getInteger(RESPONSE_ERR_CODE_NAME) != 0) {
                    int errCode = json.getIntValue(RESPONSE_ERR_CODE_NAME);
                    if (errCode == WechatConsts.WechatErrorCode.BAD_ACCESS_TOKEN.getValue()
                            || errCode == WechatConsts.WechatErrorCode.INVALID_ACCESS_TOKEN.getValue()) {
                        // access token 失效，刷新
                        updateAccessToken();
                    }
                    loggerException.error("{}", json);
                    return false;
                }
                jsApiTicket = json.getString(RESPONSE_JS_API_TOKEN_NAME);
                expiresIn = json.getInteger("expires_in");
            }
        } catch (Exception e) {
            loggerException.error("refreshUserAccessToken exception!", e);
        }
        if (expiresIn == null) {
            expiresIn = ACCESS_TOKEN_EXPIRE_TIME;
        }
        if (jsApiTicket != null) {
            redisService.forString().opsForValue().set(JS_API_TICKET_REDIS_KEY, jsApiTicket, expiresIn, TimeUnit.SECONDS);
            return true;
        }
        return false;
    }


    String getAppId() {
        return appId;
    }

    String getAppSecret() {
        return appSecret;
    }
}

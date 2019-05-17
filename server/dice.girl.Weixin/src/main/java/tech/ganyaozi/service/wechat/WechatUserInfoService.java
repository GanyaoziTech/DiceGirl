package tech.ganyaozi.service.wechat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.ganyaozi.bean.WechatUserInfo;
import tech.ganyaozi.redis.RedisService;
import tech.ganyaozi.utils.OkHttpUtils;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import static tech.ganyaozi.bean.consts.WechatConsts.RESPONSE_PARAM_NAME_ERROR_CODE;
import static tech.ganyaozi.bean.consts.WechatConsts.RESPONSE_PARAM_NAME_ERROR_MSG;


/**
 * <p>
 * 微信用户信息的相关服务，包括
 * <li>获取授权
 * <li>获取用户身份的access_token
 * <li>更新授权refresh_token
 * <p/>
 * <p>
 * 此处的access_token和AccessTokenService中的token不一样，此处的token专门用于用户授权获得其个人信息。
 * <p/>
 *
 * @author Derek.P.Dai[dp419936514@gmail.com]
 * @see AccessTokenService
 * @see <a href="https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140842">微信文档</a>
 **/
@Service
public class WechatUserInfoService {

    private static final Logger loggerException = LoggerFactory.getLogger(WechatUserInfoService.class);

    private static final Logger loggerBusiness = LoggerFactory.getLogger("business");

    /**
     * 使用用户授权code，兑换用户授权access token的接口
     */
    private static final String CONVERT_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token";
    /**
     * 根据refresh token，更新用户授权access token的接口
     */
    private static final String REFRESH_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/refresh_token";
    /**
     * 使用用户授权access token，获取用户个人信息的接口
     */
    private static final String GET_WECHAT_USER_INFO_AUTH_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/userinfo";

    /**
     * 使用公众号的access token，获取已关注的用户个人信息的接口
     */
    private static final String GET_WECHAT_USER_INFO_URL = "https://api.weixin.qq.com/cgi-bin/user/info";

    /**
     * 获取用户授权时，参数grant type的值
     */
    private static final String PARAM_NAME_GRANT_TYPE_CONVERT = "authorization_code";
    /**
     * 刷新用户授权时，参数grant type的值
     */
    private static final String PARAM_NAME_GRANT_TYPE_REFRESH = "refresh_token";


    /**
     * redis中，存放refresh token的key
     */
    private static final String REDIS_KEY_USER_REFRESH_TOKEN_PREFIX = "gs_wechat_user_refresh_token_";
    /**
     * redis中，存放用户信息scope的前缀
     */
    private static final String REDIS_KEY_USER_SCOPE_PREFIX = "gs_wechat_user_scope_";

    private final AccessTokenService accessTokenService;
    private final RedisService redisService;

    @Autowired
    public WechatUserInfoService(AccessTokenService accessTokenService, RedisService redisService) {
        this.accessTokenService = accessTokenService;
        this.redisService = redisService;
    }

    /**
     * 根据用户跳转的code，获取用户授权access token
     *
     * @param code 授权code
     */
    public JSONObject convertUserAccessToken(String code) {
        JSONObject result = new JSONObject();
        //检查必填字段
        if (StringUtils.isBlank(code)) {
            loggerException.warn("try to get user access token , but required code is missing");
            return null;
        }
        try {
            // send message
            String url = CONVERT_ACCESS_TOKEN_URL
                    + "?access_token=" + accessTokenService.getAccessToken()
                    + "&code=" + code
                    + "&appid=" + accessTokenService.getAppId()
                    + "&secret=" + accessTokenService.getAppSecret()
                    + "&grant_type=" + PARAM_NAME_GRANT_TYPE_CONVERT;

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
                    return JSONObject.parseObject(resStr);
                }
            }
        } catch (Exception e) {
            loggerException.error("redeem send item exception!", e);
        }
        return handleResult(result);
    }

    /**
     * 根据上次授权时留下的refresh token，刷新用户的授权 access token
     * 即，30天内，不需要用户再次手动授权
     *
     * @param openId 用户openId
     * @return access token
     */
    private JSONObject refreshUserAccessToken(String openId) {
        String refreshToken = redisService.forString().opsForValue().get(REDIS_KEY_USER_REFRESH_TOKEN_PREFIX + openId);
        if (StringUtils.isBlank(refreshToken)) {
            loggerException.error("cannot get user refresh token !! maybe expired !! user  : {}", openId);
            return null;
        }
        JSONObject result = new JSONObject();
        // send message
        String url = REFRESH_ACCESS_TOKEN_URL
                + "?access_token=" + accessTokenService.getAccessToken()
                + "&grant_type=" + PARAM_NAME_GRANT_TYPE_REFRESH
                + "&appid=" + accessTokenService.getAppId()
                + "&refresh_token=" + refreshToken;

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
                return JSONObject.parseObject(resStr);
            }
        } catch (Exception e) {
            loggerException.error("refreshUserAccessToken exception!", e);
        }

        if (result.getIntValue(RESPONSE_PARAM_NAME_ERROR_CODE) != 0 || result.getString(RESPONSE_PARAM_NAME_ERROR_MSG) != null) {
            loggerException.error("result : {}", result);
        }

        return handleResult(result);

    }

    /**
     * 根据用户的授权token，获取用户的基本信息
     *
     * @return 用户基本信息
     */
    public WechatUserInfo getWechatUserInfo(String accessToken, String openId) {
        String url = GET_WECHAT_USER_INFO_AUTH_ACCESS_TOKEN_URL;
        if (openId == null) {
            // 必须要有用户的openid
            return null;
        }
        if (accessToken == null) {
            // 不带授权token时，根据上次保存的refresh token进行获取。
            JSONObject result = refreshUserAccessToken(openId);
            if (result == null) {
                loggerBusiness.info("user {} , cannot find access token or refresh token !!", openId);
                // 此时，无法获得用户的授权ID，只能使用公众号自己的access token， 用另一个接口，获得已关注用户的信息
                accessToken = accessTokenService.getAccessToken();
                url = GET_WECHAT_USER_INFO_URL;
            } else {
                accessToken = result.getString("access_token");
            }
        }
        if (StringUtils.isBlank(accessToken) || StringUtils.isBlank(openId)) {
            loggerException.error("accessToken or openid is null!! accessToken:{},openId:{}", accessToken, openId);
            return null;
        }
        WechatUserInfo wechatUserInfo = new WechatUserInfo();
        // send message
        String urlStr = url
                + "?access_token=" + accessToken
                + "&openid=" + openId
                + "&lang=zh_CN";

        OkHttpClient client = new OkHttpClient.Builder().build();
        Request request = new Request.Builder()
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .url(urlStr)
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            ResponseBody responseBody = response.body();
            if (responseBody != null) {
                String resStr = responseBody.string();
                wechatUserInfo = JSON.parseObject(resStr, WechatUserInfo.class);

                // 微信过来的时间戳，是精确到秒的，要专门做转化
                long subscribeTime = JSONObject.parseObject(resStr).getLongValue("subscribe_time");
                if (subscribeTime != 0) {
                    wechatUserInfo.setSubscribeTime(new Date(subscribeTime * 1000));
                }
            }
        } catch (Exception e) {
            loggerException.error("getWechatUserInfo exception!", e);
        }

        if (wechatUserInfo.getErrCode() != null || wechatUserInfo.getErrMsg() != null) {
            loggerException.error("result : {}", wechatUserInfo);
            return null;
        }
        return wechatUserInfo;
    }


    /**
     * 处理获取用户授权access_token和刷新token的返回结果
     *
     * @param result 返回结果
     * @return user access token
     */
    private JSONObject handleResult(JSONObject result) {
        if (result == null) {
            return null;
        }
        String userAccessToken = result.getString("access_token");
        String userRefreshToken = result.getString("refresh_token");
        String expiresIn = result.getString("expires_in");
        String openid = result.getString("openid");
        String scope = result.getString("scope");
        if (StringUtils.isBlank(userAccessToken) || StringUtils.isBlank(openid)) {
            loggerException.error("fail to get user access token ");
            return null;
        }

        //保存refresh token，以获得下次授权
        if (StringUtils.isNotBlank(userRefreshToken) && StringUtils.isNumeric(expiresIn)) {
            loggerException.warn("\n===================================================\n" +
                    "||   Update User access token & refresh token !! ||\n" +
                    "===================================================\n");
            redisService.forString().opsForValue().set(REDIS_KEY_USER_REFRESH_TOKEN_PREFIX + openid, userRefreshToken, 30, TimeUnit.DAYS);
            redisService.forString().opsForValue().set(REDIS_KEY_USER_SCOPE_PREFIX + openid, scope, 30, TimeUnit.DAYS);
        }
        return result;
    }

}

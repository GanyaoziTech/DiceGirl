package tech.ganyaozi.warframe.stat.util;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.springframework.lang.Nullable;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * util to get a OkHttpClient
 *
 * @author derek.p.dai at 2019/5/17 15:51
 **/
public class OkHttpUtil {

    private static final boolean RETRY_ON_FAIL = true;

    private static final int READ_WRITE_TIME_OUT_IN_MILLISECONDS = 30000;

    private static OkHttpClient getClient() {
        return new OkHttpClient.Builder()
                .retryOnConnectionFailure(RETRY_ON_FAIL)
                .readTimeout(READ_WRITE_TIME_OUT_IN_MILLISECONDS, TimeUnit.MILLISECONDS)
                .writeTimeout(READ_WRITE_TIME_OUT_IN_MILLISECONDS, TimeUnit.MILLISECONDS)
                .build();
    }

    public static String get(String url) throws IOException {

        Request request = new Request.Builder()
                .header("content", "application/json")
                .url(url)
                .get().build();
        try (Response response = getClient().newCall(request).execute()) {
            ResponseBody body = response.body();
            if (body != null) {
                return body.string();
            }
        }
        throw new RuntimeException("call to " + url + " fail, response is null");
    }

    @Nullable
    public static JSONArray sendGetAsJsonArray(String url) throws IOException {
        return JSONArray.parseArray(get(url));
    }

    @Nullable
    public static JSONObject sendGetAsJsonObject(String url) throws IOException {
        return JSONObject.parseObject(get(url));
    }

}

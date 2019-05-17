package tech.ganyaozi.utils;

import okhttp3.OkHttpClient;

import java.util.concurrent.TimeUnit;

/**
 * @author derek.p.dai at 2019/5/17 15:05
 **/
public class OkHttpUtils {

    private static final int READ_WRITE_TIME_OUT = 10000;
    private static final boolean RETRY_ON_FAILURE = true;

    private static final OkHttpClient CLIENT = new OkHttpClient()
            .newBuilder()
            .retryOnConnectionFailure(RETRY_ON_FAILURE)
            .readTimeout(READ_WRITE_TIME_OUT, TimeUnit.MILLISECONDS)
            .writeTimeout(READ_WRITE_TIME_OUT, TimeUnit.MILLISECONDS)
            .build();

    public static OkHttpClient getClient() {
        return CLIENT;
    }
}

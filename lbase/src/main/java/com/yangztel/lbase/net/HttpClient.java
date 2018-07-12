package com.yangztel.lbase.net;

import com.yangztel.lbase.BuildConfig;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

/**
 * Created by yangzteL on 2018/7/11 0011.
 */

public class HttpClient {

    private static OkHttpClient sOkHttpClient = null;


    public static OkHttpClient getClient() {
        if (sOkHttpClient == null) {
            synchronized (OkHttpClient.class) {
                OkHttpClient.Builder builder = new OkHttpClient.Builder()
                        .addInterceptor(getHeaderInterceptor())
//                        .addInterceptor(getBodyInterceptor())
                        .addInterceptor(new LoggingInterceptor())
                        .connectTimeout(120, TimeUnit.SECONDS)
                        .readTimeout(120, TimeUnit.SECONDS)
                        .writeTimeout(200, TimeUnit.SECONDS).retryOnConnectionFailure(true);
                return builder.build();
            }
        }
        return sOkHttpClient;
    }

    private static Interceptor getHeaderInterceptor(){
        Map<String,String> headers = new HashMap<>();
        headers.put("Api-Appversion", "v" + BuildConfig.VERSION_NAME);
        headers.put("Api-Version", "v1");
//        headers.put("Api-Uuid", PhoneUtil.getIMEI(BaseApp.mContext));
//        headers.put("Api-Os", PhoneUtil.getSystemVersion());
//        headers.put("Api-Channel", AppUtil.getChannelValue(BaseApp.mContext));
        headers.put("Api-Platform","1");//1 安卓, 2 ios
        return new HeaderInterceptor(headers);
    }
}

package com.yangztel.lbase.net;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yangzteL on 2018/7/11 0011.
 */

public class ApiModule {
    static ApiModule mApiModule;
    private static Retrofit.Builder sBuilder;
    private ApiModule() {}

    public static ApiModule getInstance() {
        if (mApiModule == null) {
            synchronized (ApiModule.class) {
                if (mApiModule == null) {
                    mApiModule = new ApiModule();
                    sBuilder = new Retrofit.Builder()
                            .addConverterFactory(new NullOnEmptyConverterFactory())//body为空
                            .addConverterFactory(GsonConverterFactory.create())//json 解析
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create());//让Retrofit支持RxJava
                }
            }
        }
        return mApiModule;
    }

    public Object createService(Class tClass, String url) {
        Retrofit retrofit = sBuilder.client(HttpClient.getClient())
                .baseUrl(url).build();
        return retrofit.create(tClass);
    }
    public Object createService(Class tClass) {
        Retrofit retrofit = sBuilder.client(HttpClient.getClient())
                .baseUrl("http://wanandroid.com/tools/mockapi/").build();
        return retrofit.create(tClass);
    }

}

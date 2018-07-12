package com.yangztel.mylib;

import com.yangztel.lbase.net.Result;


import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by yangzteL on 2018/7/9 0009.
 */

public class api {
    public interface LoginServer {
        @GET("6385/login")
        Call<Result<LoginBean>> login();

        @GET("6385/login")
        Observable<Result<LoginBean>> loginRx();
    }
}

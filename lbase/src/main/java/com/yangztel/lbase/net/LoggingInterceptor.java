package com.yangztel.lbase.net;

import android.util.Log;
import java.io.IOException;
import java.net.URLDecoder;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by longwj on 2017/3/28.
 */

public class LoggingInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        long t1 = System.nanoTime();//请求发起的时间

        try {
            String method = request.method();
            StringBuilder sb = new StringBuilder();
            if ("POST".equals(method)) {
                if (request.body() instanceof FormBody) {
                    FormBody body = (FormBody) request.body();
                    for (int i = 0; i < body.size(); i++) {
                        sb.append(body.encodedName(i) + "=" + body.encodedValue(i) + ",");
                    }
                    if (sb.length() > 0) {
                        sb.delete(sb.length() - 1, sb.length());
                    }
                }
            }
            Log.e("HTTP请求：", String.format("发送请求 %s on %s%n headers:%s",
                    request.url(), URLDecoder.decode(sb.toString(), "utf-8"), request.headers()));
        } catch (Exception e) {
            e.printStackTrace();
        }


        Response response = chain.proceed(request);

        long t2 = System.nanoTime();//收到响应的时间

        ResponseBody responseBody = response.peekBody(1024 * 1024);
        try {
            Log.e("HTTP请求：",String.format("HTTP请求：接收响应: [%s] %n返回json:【%s】 %.1fms%n%s",
                    response.request().url(),
                    responseBody.string(),
                    (t2 - t1) / 1e6d,
                    response.headers()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}

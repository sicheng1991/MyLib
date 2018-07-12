package com.yangztel.lbase.net;

import android.text.TextUtils;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by yangzteL on 2018/7/11 0011.
 */

public class HttpObserver<T> implements Observer<Result<T>> {
    private HttpResult httpObserver;

    public HttpObserver(HttpResult<T> httpObserver){
        if(httpObserver == null){
            throw new IllegalArgumentException("need a httpObserver");
        }
        this.httpObserver = httpObserver;
    }
    public HttpObserver(){}

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(Result<T> result) {
        if(isContain(result.code,"200")){
            httpObserver.onSuccess(result);
        }
    }

    @Override
    public void onError(Throwable t) {
        Result<T> httpResult = new Result<>();
        httpResult.code = "500";
        httpResult.message= t.getMessage();
        httpObserver.onFailed(httpResult);
    }

    @Override
    public void onComplete() {

    }

    private boolean isContain(String code,String... template){
        for(String c1 : template){
            if(TextUtils.equals(code,c1)){
                return true;
            }
        }
        return false;
    }
}

package com.yangztel.lbase.net;

/**
 * Created by yangzteL on 2018/7/11 0011.
 */

public abstract class HttpResult<T> implements HttpResultInterface<T> {
    @Override
    public void onFailed(Result<T> result) {

    }

    @Override
    public void notData(Result<T> result) {

    }


}

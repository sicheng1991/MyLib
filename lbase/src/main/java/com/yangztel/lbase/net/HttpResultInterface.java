package com.yangztel.lbase.net;

/**
 * Created by yangzteL on 2018/7/11 0011.
 */

public interface HttpResultInterface<T> {
    void onSuccess(Result<T> result);

    void onFailed(Result<T> result);

    void notData(Result<T> result);
}

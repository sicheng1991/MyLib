package com.yangztel.lbase.base;

/**
 * Created by yangzteL on 2018/7/4 0004.
 */

public interface IPresenter<T> {
    void attachView(T view);



    void detachView();
}

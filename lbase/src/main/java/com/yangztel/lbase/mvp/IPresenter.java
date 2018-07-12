package com.yangztel.lbase.mvp;

/**
 * Created by yangzteL on 2018/7/4 0004.
 */

public interface IPresenter<T> {
    void attachView(T view);



    void detachView();
}

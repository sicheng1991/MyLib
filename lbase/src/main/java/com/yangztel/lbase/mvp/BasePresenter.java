package com.yangztel.lbase.mvp;

/**
 * Created by yangzteL on 2018/7/5 0005.
 */

public abstract class BasePresenter<T ,K> implements IPresenter<T>{
    public T mView;//
    public K mModel;
    public RxManager mRxManager = new RxManager();

    @Override
    public void attachView(T view) {
        this.mView = view;
        this.mModel = getModel();
    }

    protected abstract K getModel();



    @Override
    public void detachView() {
        this.mView = null;
        this.mModel = null;
        mRxManager.clear();

    }
}

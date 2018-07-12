package com.yangztel.lbase.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;

import com.yangztel.lbase.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by yangzteL on 2018/7/4 0004.
 */

public abstract class BaseActivity<T extends IPresenter> extends FragmentActivity implements View.OnClickListener{
    public T mPresenter;
    private Unbinder unbinder;
    public RxManager mRxManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(getLayoutId());
        unbinder = ButterKnife.bind(this);
        mPresenter = initPresent();
        if(mPresenter == null){
            throw new IllegalArgumentException("you must create a presenter");
        }
        mPresenter.attachView(this);
        initBack();
        initEventAndData();
    }

    private void initBack() {
        View view = findViewById(R.id.back);
        if (view != null) {
            view.setOnClickListener(this);
        }
    }

    /**
     * 布局
     * @return
     */
    protected abstract int getLayoutId();

    protected abstract T initPresent();
    /**
     * 初始化
     */
    protected abstract void initEventAndData();

    /**
     * 点击事件
     * @param v
     */
    protected abstract void processClick(View v);

    @Override
    public void onClick(View v) {
        processClick(v);
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) mPresenter.detachView();
        mRxManager.clear();
        unbinder.unbind();
        AppManager.getAppManager().finishActivity(this);
    }
}

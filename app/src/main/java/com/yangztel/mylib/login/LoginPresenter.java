package com.yangztel.mylib.login;

import android.util.Log;

import com.yangztel.lbase.base.BasePresenter;

/**
 * Created by yangzteL on 2018/7/5 0005.
 */

public class LoginPresenter extends BasePresenter<LoginContract.View,LoginContract.Model> implements LoginContract.Presenter {


    @Override
    protected LoginContract.Model getModel() {
        return new LoginModel(busBean -> {
            if(busBean.type == LoginModel.LOGIN){
                Log.e("msgggggg", "initBusData: " + busBean.data);
            }
        });
    }

    @Override
    public void login(String name, String pwd) {
        mModel.login(name,pwd);
    }
}

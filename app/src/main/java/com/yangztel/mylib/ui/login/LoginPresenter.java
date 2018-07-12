package com.yangztel.mylib.ui.login;

import android.util.Log;

import com.yangztel.lbase.mvp.BasePresenter;
import com.yangztel.mylib.LoginBean;

/**
 * Created by yangzteL on 2018/7/5 0005.
 */

public class LoginPresenter extends BasePresenter<LoginContract.View,LoginContract.Model> implements LoginContract.Presenter {


    @Override
    protected LoginContract.Model getModel() {
        return new LoginModel(busBean -> {
            if(busBean.type == LoginModel.LOGIN){
//                Log.e("msgggggg", "initBusData: " + busBean.data);
                LoginBean bean = (LoginBean) busBean.data;
                Log.e("msggggg","initBusData: " + bean.getToken());
            }
        });
    }

    @Override
    public void login(String name, String pwd) {
        mModel.login(name,pwd);
    }
}

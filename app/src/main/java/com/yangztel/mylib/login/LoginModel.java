package com.yangztel.mylib.login;

import android.util.Log;

import com.yangztel.lbase.base.BaseModel;
import com.yangztel.lbase.base.BasePresenter;
import com.yangztel.lbase.base.BusBean;

/**
 * Created by yangzteL on 2018/7/5 0005.
 */

public class LoginModel extends BaseModel implements LoginContract.Model{
    public final static int LOGIN = 1;

    public LoginModel(BusCallBack busCallBack) {
        super(busCallBack);
    }


    @Override
    public void login(String name, String pwd) {
        callBack.busCallBack(new BusBean(LOGIN,String.format("login: name:%1$s pwd:%2$s",name,pwd)));
    }
}

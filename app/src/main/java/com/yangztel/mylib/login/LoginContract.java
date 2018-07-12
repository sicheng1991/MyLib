package com.yangztel.mylib.login;

import com.yangztel.lbase.mvp.IModel;
import com.yangztel.lbase.mvp.IPresenter;
import com.yangztel.lbase.mvp.IView;

/**
 * Created by yangzteL on 2018/7/5 0005.
 */

public class LoginContract {
    interface View extends IView{

    }

    interface Presenter extends IPresenter<View>{
        void login(String name,String pwd);

    }

    interface Model extends IModel{
        void login(String name, String pwd);
    }
}

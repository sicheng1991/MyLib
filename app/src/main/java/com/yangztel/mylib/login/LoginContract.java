package com.yangztel.mylib.login;

import com.yangztel.lbase.base.IModel;
import com.yangztel.lbase.base.IPresenter;
import com.yangztel.lbase.base.IView;

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

package com.yangztel.mylib.ui.login;

import com.yangztel.lbase.mvp.BaseModel;
import com.yangztel.lbase.mvp.BusBean;
import com.yangztel.lbase.net.ApiModule;
import com.yangztel.lbase.net.HttpObserver;
import com.yangztel.lbase.net.Result;
import com.yangztel.lbase.net.RxSchedulers;
import com.yangztel.lbase.net.HttpResult;
import com.yangztel.mylib.LoginBean;
import com.yangztel.mylib.api;

import io.reactivex.functions.Consumer;

/**
 * Created by yangzteL on 2018/7/5 0005.
 */

public class LoginModel extends BaseModel implements LoginContract.Model{
    public final static int LOGIN = 1;
    api.LoginServer loginServer;
    private final String TAG = "msggggg";

    public LoginModel(BusCallBack busCallBack) {
        super(busCallBack);
        loginServer =  (api.LoginServer) ApiModule.getInstance().createService(api.LoginServer.class);
    }


    @Override
    public void login(String name, String pwd) {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://wanandroid.com/tools/mockapi/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .build();
//        api.LoginServer loginServer = retrofit.create(api.LoginServer.class);

//            loginServer.loginRx().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(loginBeanResult -> {
//                        callBack.busCallBack(new BusBean(LOGIN,loginBeanResult.data));
//                    });



        //还不能管理管理订阅（这个方法没有返回disposable）
        loginServer.loginRx().compose(RxSchedulers.io_main())
                .subscribe(new HttpObserver<>(new HttpResult<LoginBean>() {
                    @Override
                    public void onSuccess(Result<LoginBean> result) {
                        callBack.busCallBack(new BusBean(LOGIN,result.data));
                    }
                }));

        loginServer.loginRx().compose(RxSchedulers.io_main())
                .subscribe(new Consumer<Result<LoginBean>>() {
                    @Override
                    public void accept(Result<LoginBean> loginBeanResult) throws Exception {

                    }
                });


    }
}

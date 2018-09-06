package com.yangztel.mylib;

import android.app.Application;
import android.content.Intent;

import com.yangztel.myutils.utils.ToastUtil;

/**
 * Created by yangzteL on 2018/9/6 0006.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Intent intent = new Intent(this,CheckExitService.class);
        startService(intent);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        ToastUtil.showLong(this, "退出app");

    }
}

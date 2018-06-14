package com.yangztel.myutils;

import android.app.Application;


/**
 * Created by Longwj on 2017/7/13.
 */

public class LibApplication extends Application{
    public static LibApplication application;

    public static LibApplication getInstance() {
        if (application == null) {
            synchronized (LibApplication.class) {
                if (application == null) {
                    application = new LibApplication();
                }
            }
        }
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}

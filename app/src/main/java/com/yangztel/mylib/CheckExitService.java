package com.yangztel.mylib;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

/**
 * APP退出监听
 * https://blog.csdn.net/minwenping/article/details/64132369
 *
 * Created by yangzteL on 2018/9/6 0006.
 */

public class CheckExitService extends Service {
    private String packageName = "test.minwenping.com.appexitdemo";
    private String TAG = "CheckExitServiceTag";


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        Log.e(TAG, "onTaskRemoved: "+ "App要退出了" );
        Toast.makeText(CheckExitService.this, "App要退出了", Toast.LENGTH_SHORT).show();
    }

    //service异常停止的回调
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        ActivityManager activtyManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = activtyManager.getRunningAppProcesses();
        for (int i = 0; i < runningAppProcesses.size(); i++) {
            if (packageName.equals(runningAppProcesses.get(i).processName)) {
                Log.e(TAG, "onTaskRemoved: "+ "app还在运行中" );
                Toast.makeText(this, "app还在运行中", Toast.LENGTH_LONG).show();
            }
        }
        return START_NOT_STICKY;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "onTaskRemoved: "+ "App检测服务开启了" );
        Toast.makeText(CheckExitService.this, "App检测服务开启了", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "onTaskRemoved: "+ "onDestroy" );
        super.onDestroy();
    }
}

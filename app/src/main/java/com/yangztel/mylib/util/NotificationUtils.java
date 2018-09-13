package com.yangztel.mylib.util;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;

import com.yangztel.mylib.MainActivity;
import com.yangztel.mylib.R;


/**
 * NotificationUtils
 * Created by yangzteL on 2018/9/12 0012.
 */

public class NotificationUtils extends ContextWrapper {
    private NotificationManager manager;
    public static final String id = "channel_1";
    public static final String name = "channel_name_1";

    public NotificationUtils(Context context){
        super(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void createNotificationChannel(){
        NotificationChannel channel = null;
        channel = new NotificationChannel(id, name, NotificationManager.IMPORTANCE_HIGH);
        getManager().createNotificationChannel(channel);

    }
    private NotificationManager getManager(){
        if (manager == null){
            manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        }
        return manager;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public Notification.Builder getChannelNotification(String title, String content){
        return new Notification.Builder(getApplicationContext(), id)
                .setContentTitle(title)
                .setContentText(content)
                .setSmallIcon(android.R.drawable.stat_notify_more)
                .setAutoCancel(true);
    }
    public NotificationCompat.Builder getNotification_25(String title, String content){
        return new NotificationCompat.Builder(getApplicationContext())
                .setContentTitle(title)
                .setContentText(content)
                .setSmallIcon(android.R.drawable.stat_notify_more)
                .setAutoCancel(true);
    }
    public void sendNotification(String title, String content){
        if (Build.VERSION.SDK_INT>=26){
            createNotificationChannel();
            Notification notification = getChannelNotification
                    (title, content).build();
            getManager().notify(1,notification);
        }else{
            Notification notification = getNotification_25(title, content).build();
            getManager().notify(1,notification);
        }
    }

    public void sendRemoteNotification(){
        PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0x001,
                new Intent(getApplicationContext(), MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);
        RemoteViews remoteView = new RemoteViews(getPackageName(), R.layout.notification);
        remoteView.setTextViewText(R.id.remoteview_main_title, "Title");
        remoteView.setTextViewText(R.id.remoteview_main_content, "ContentContentContent");
        remoteView.setImageViewResource(R.id.remoteview_main_icon, R.drawable.a1);
        remoteView.setOnClickPendingIntent(R.id.main_view, pi);
        Notification.Builder nb = null;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            createNotificationChannel();
            nb = new Notification.Builder(getApplicationContext(),id);
        }else {
            nb = new Notification.Builder(getApplicationContext());
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            nb.setSmallIcon(R.mipmap.ic_launcher) // 小图标
            .setCustomContentView(remoteView) // 设置自定义的RemoteView，需要API最低为24
            .setWhen(System.currentTimeMillis()) // 设置通知发送的时间戳
            .setAutoCancel(true) // 点击通知后通知在通知栏上消失
            .setDefaults(Notification.DEFAULT_ALL); // 设置默认的提示音、振动方式、灯光等
        }
        ((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE)).notify(1, nb.build()); // build()方法需要的最低API为16
    }

}

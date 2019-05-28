package com.yyh.swipebackdemo;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.yyh.swipebackdemo.ui.Main2Activity;

import java.util.List;

/**
 *  @describe: 通知管理lei
 *  @author: yyh
 *  @createTime: 2018/8/28 16:28
 *  @className:  PushNotificationHelper
 */
public class PushNotificationHelper {
    private NotificationManager mNotificationManager;
    private NotificationCompat.Builder mBuilder;
    private Context context;
    public int id;

    //单例
    private PushNotificationHelper(int id, Context context) {
        super();
        this.id = id;
        this.context = context;
        mNotificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
    }

    private static PushNotificationHelper instance = null;

    public static PushNotificationHelper getInstance(int id, Context context) {
        if (instance == null) {
            instance = new PushNotificationHelper(id, context);
        }
        return instance;
    }


    public void showNotify() {
        PendingIntent contentIntent = null;
        if (isAppAlive(context, context.getPackageName())) {
            Log.e("----Notification", "the app process is isAppAlive");
            Intent notificationIntent = new Intent(context, Main2Activity.class);
            notificationIntent.putExtra("notification", "notification data");
            notificationIntent.putExtra("where", "notification flag");
            contentIntent = PendingIntent.getActivity(context, R.string.app_name,
                    notificationIntent, PendingIntent.FLAG_ONE_SHOT);
        } else {
            Log.e("----Notification", "the app process is dead");
            Intent launchIntent = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
            launchIntent.putExtra("notification", "notification data");
            launchIntent.putExtra("where", "notification flag");
            contentIntent = PendingIntent.getActivity(context, R.string.app_name,
                    launchIntent, PendingIntent.FLAG_ONE_SHOT);
        }

        Notification notification = new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setTicker("一大波新资讯正在涌来....")//顶部标题栏
                .setContentTitle("通知栏标题...")
                .setContentText("通知栏内容...通知栏内容...通知栏内容...")
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setContentIntent(contentIntent).build();

        notification.defaults = Notification.DEFAULT_ALL;
        postNotification(id, notification);
    }

    public void postNotification(int id, Notification notification) {
        mNotificationManager.notify(id, notification);
    }

    public void cancelNotification(int id) {
        mNotificationManager.cancel(id);
    }

    public void cancelAllNotifications() {
        mNotificationManager.cancelAll();
    }

    //判断App是否存活
    public static boolean isAppAlive(Context context, String packageName) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(100);
        for (ActivityManager.RunningTaskInfo info : list) {
            if (info.topActivity.getPackageName().equals(packageName) && info.baseActivity.getPackageName().equals(packageName)) {
                return true;
            }
        }
        return false;
    }
}

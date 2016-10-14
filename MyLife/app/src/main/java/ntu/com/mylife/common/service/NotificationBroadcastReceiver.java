package ntu.com.mylife.common.service;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;

/**
 * Created by LENOVO on 26/09/2016.
 */
public class NotificationBroadcastReceiver extends WakefulBroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = intent.getParcelableExtra(ServiceConfiguration.NOTIFICATION);
        int id = intent.getIntExtra(ServiceConfiguration.NOTIFICATION_ID, 0);
        notificationManager.notify(id, notification);
    }
}

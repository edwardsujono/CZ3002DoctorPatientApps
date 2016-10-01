package ntu.com.mylife.common.service;

import android.app.Activity;
import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.util.Random;

import ntu.com.mylife.R;

/**
 * Created by LENOVO on 26/09/2016.
 */
public class NotificationService extends IntentService {

    private NotificationManager alarmNotificationManager;

    public NotificationService()
    {
        super("Notification");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        sendMessage(intent.getStringExtra("notification"));
    }


    private void sendMessage(String msg){
        Log.d("AlarmService", "Preparing to send notification...: " + msg);
        alarmNotificationManager = (NotificationManager) this
                .getSystemService(Context.NOTIFICATION_SERVICE);
        //get pending Intent from the first activity
        Random generate = new Random();
        PendingIntent contentIntent = PendingIntent.getService(this,generate.nextInt(),new Intent(this,this.getClass()),0);
        NotificationCompat.Builder alamNotificationBuilder = new NotificationCompat.Builder(
                this).setContentTitle("Alarm").setSmallIcon(R.drawable.icon_doctor)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(msg))
                .setContentText(msg);

        alamNotificationBuilder.setContentIntent(contentIntent);
        alarmNotificationManager.notify(1, alamNotificationBuilder.build());
        Log.d("AlarmService", "Notification sent.");
    }
}

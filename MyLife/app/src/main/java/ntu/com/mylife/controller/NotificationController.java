package ntu.com.mylife.controller;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

import ntu.com.mylife.R;
import ntu.com.mylife.common.entity.databaseentity.DaySchedule;
import ntu.com.mylife.common.service.ServiceConfiguration;
import ntu.com.mylife.common.service.UserScheduleDaoImpl;
import ntu.com.mylife.common.service.BaseCallback;
import ntu.com.mylife.common.service.NotificationBroadcastReceiver;

/**
 * Created by LENOVO on 02/10/2016.
 */
public class NotificationController implements BaseCallback {

    private Activity myActivity;
    private UserScheduleDaoImpl dbSchedule;
    private long beforeTime  = 1;


    public NotificationController(Activity activity,String userId){
        this.myActivity = activity;
        dbSchedule = new UserScheduleDaoImpl(this,userId);
    }


    public void searchForNewNotification(ArrayList<DaySchedule> listDaySchedule){
        for(DaySchedule daySchedule: listDaySchedule){
            //give notification
            //daySchedule.getFutureTimeMillis()-1000*60*60*beforeTime
            scheduleNotification(getNotification(daySchedule.getDescription()),daySchedule.getFutureTimeMillis()-1000*60*60*beforeTime);
            Log.i("Future Time Millis",System.currentTimeMillis()-1000*60*60+30000+"");
        }
//        Toast.makeText(this.myActivity,"Notification is updated",Toast.LENGTH_LONG).show();
    }


    private void scheduleNotification(Notification notification, long timeFuture) {

        Intent notificationIntent = new Intent(this.myActivity, NotificationBroadcastReceiver.class);
        Random random = new Random();
        int nextInt = random.nextInt();
        notificationIntent.putExtra(ServiceConfiguration.NOTIFICATION_ID, nextInt);
        notificationIntent.putExtra(ServiceConfiguration.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this.myActivity, nextInt, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager)this.myActivity.getSystemService(this.myActivity.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP,timeFuture, pendingIntent);
    }

    private Notification getNotification(String content) {
        Notification.Builder builder = new Notification.Builder(this.myActivity);
        builder.setContentTitle("LifeMate");
        builder.setContentText(content);
        builder.setSmallIcon(R.drawable.icon_doctor);
        return builder.build();
    }

    //this is to handle new Notification
    @Override
    public void callbackFunction(Object object) {
        searchForNewNotification((ArrayList<DaySchedule>) object);
    }
}

package ntu.com.mylife.controller;

import android.app.Activity;
import android.widget.Toast;

import com.firebase.client.Firebase;

import ntu.com.mylife.common.service.UserScheduleDaoImpl;

/**
 * Created by LENOVO on 28/09/2016.
 */
public class CreateReminderController {

    private UserScheduleDaoImpl databaseDaoUserSchedule;
    private Activity activity;

    public CreateReminderController(Activity activity){
        Firebase.setAndroidContext(activity);
        databaseDaoUserSchedule  = new UserScheduleDaoImpl();
    }

    public void addToDatabaseReminder(Object object) throws Exception{
        databaseDaoUserSchedule.addData(object);
        Toast.makeText(activity, "Schedule is added", Toast.LENGTH_SHORT).show();
    }


}

package ntu.com.mylife.controller;

import android.app.Activity;
import android.widget.Toast;

import com.firebase.client.Firebase;

import ntu.com.mylife.common.service.DatabaseDaoReminder;
import ntu.com.mylife.common.service.DatabaseDaoReminderImpl;
import ntu.com.mylife.common.service.DatabaseDaoUserScheduleImpl;

/**
 * Created by LENOVO on 28/09/2016.
 */
public class CreateReminderController {

    private DatabaseDaoUserScheduleImpl databaseDaoUserSchedule;
    private Activity myActivity;

    public CreateReminderController(Activity activity){
        Firebase.setAndroidContext(activity);
        databaseDaoUserSchedule  = new DatabaseDaoUserScheduleImpl();
    }

    public void addToDatabaseReminder(Object object) throws Exception{
        databaseDaoUserSchedule.addData(object);
        Toast.makeText(myActivity, "Schedule is added", Toast.LENGTH_SHORT).show();
    }


}

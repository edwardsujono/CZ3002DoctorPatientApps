package ntu.com.mylife.controller;

import android.app.Activity;

import ntu.com.mylife.common.service.DatabaseDaoReminder;
import ntu.com.mylife.common.service.DatabaseDaoReminderImpl;

/**
 * Created by LENOVO on 28/09/2016.
 */
public class CreateReminderController {

    private DatabaseDaoReminder databaseDaoReminder;

    public CreateReminderController(Activity activity){
        databaseDaoReminder = new DatabaseDaoReminderImpl(activity);
    }

    public void addToDatabaseReminder(Object object) throws Exception{
        databaseDaoReminder.addData(object);
    }


}

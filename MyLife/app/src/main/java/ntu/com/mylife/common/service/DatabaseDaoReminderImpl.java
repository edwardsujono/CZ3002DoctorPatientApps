package ntu.com.mylife.common.service;

import android.app.Activity;
import android.util.Log;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.HashMap;

import ntu.com.mylife.common.entity.applicationentity.SharedPreferencesKey;
import ntu.com.mylife.common.entity.databaseentity.Reminder;
import ntu.com.mylife.common.entity.databaseentity.UserType;

/**
 * Created by LENOVO on 28/09/2016.
 */
public class DatabaseDaoReminderImpl implements  DatabaseDaoReminder{

    private static String REMINDERS = "Reminders";
    private Activity myActivity;
    private Firebase firebaseDb;
    private SharedPreferencesService sharedPreferencesService;
    private String username = null;
    private String type = null;

    public DatabaseDaoReminderImpl(Activity activity){
        this.myActivity = activity;
        this.firebaseDb = new Firebase("https://lifemate.firebaseio.com/");
        this.sharedPreferencesService = new SharedPreferencesService(activity.getBaseContext());
        username = sharedPreferencesService.getDataFromSharedPreferences(SharedPreferencesKey.NAME_SHARED_PREFERENCES,SharedPreferencesKey.KEY_USER);
        type = sharedPreferencesService.getDataFromSharedPreferences(SharedPreferencesKey.NAME_SHARED_PREFERENCES,"userType");
        //always listen to changes if there has a changes will start to broadcast the intent
        firebaseDb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.d("Read failed", firebaseError.getMessage());
            }
        });


    }


    @Override
    public Object getData(Object object) throws Exception {
        return null;
    }

    @Override
    public void addData(Object object) throws Exception {
        //add to Reminders
        Reminder reminder = (Reminder) object;
        Firebase baseReminder = firebaseDb.child(REMINDERS);
        baseReminder.setValue(reminder);
    }

    public void handleNotification(HashMap<String,Object> hashMapNotification){
            for(Object key: hashMapNotification.keySet()){
                HashMap<String,String> objectReminder = (HashMap<String,String>)hashMapNotification.get(key);
                if(type.equals(UserType.Type.DOCTOR.toString())){

                }

            }
    }

}

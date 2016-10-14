package ntu.com.mylife.common.service;

import android.content.Context;
import android.provider.ContactsContract;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

import ntu.com.mylife.common.entity.databaseentity.DatabaseConfiguration;
import ntu.com.mylife.common.entity.databaseentity.DaySchedule;

/**
 * Created by LENOVO on 03/09/2016.
 */

//this is an FireBase Accessor to Update,Create,etc database

public class UserScheduleDaoImpl implements UserScheduleDao {

    private Context context;
    private HashMap hashMapSaved;
    private Firebase firebaseDb;
    private String userId, timeSchedule;

    public UserScheduleDaoImpl(final Context context, final BaseCallback callback, final String userId, final String timeSchedule){
        this.context = context;
        this.firebaseDb = new Firebase(DatabaseConfiguration.DATABASE_URL);
        this.userId = userId;
        this.timeSchedule = timeSchedule;

        //always put the event listener at constructor
        //this below code will create separate thread so all this functionality will be
        //asynchronous

        firebaseDb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                hashMapSaved = (HashMap) snapshot.getValue();
                try {
                    HashMap hashReturned = (HashMap) searchData(userId, timeSchedule);
                    callback.callbackFunction(hashReturned);
                }catch(Exception e){
                    //do nothing for now
                }
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });
    }

    //this constructor needed because we just want to add some database
    public UserScheduleDaoImpl(){
        this.firebaseDb = new Firebase(DatabaseConfiguration.DATABASE_URL);
    }

    public UserScheduleDaoImpl(final BaseCallback callback, final String userName){
        this.firebaseDb = new Firebase(DatabaseConfiguration.DATABASE_URL);
        firebaseDb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                hashMapSaved = (HashMap) snapshot.getValue();
                try {
                    ArrayList<DaySchedule> listDaySchedules = searchNewNotification(userName);
                    callback.callbackFunction(listDaySchedules);
                }catch(Exception e){
                    //do nothing for now
                }
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });
    }


    @Override
    public void addData(Object object) {
        DaySchedule schedule = (DaySchedule) object;
        Firebase scheduleObject = firebaseDb.child(DatabaseConfiguration.USER_SCHEDULE);
        Firebase listSchedule = scheduleObject.push();
        listSchedule.setValue(schedule);
    }

    @Override
    public Object searchData(String userName,String dayInserted) {

        /* JSON Data Structure
        Under UserSchedule on FireBase
        {
            "date" : "12-30-2016",
            "time" : "12:20"
            "userName" : "edward"
            "description" : "Hey Edward This is the time for us to sleep"
            "futureTimeMillis" : 12671726176721
        }
       */
        HashMap<String,Object> returnHash = new HashMap<String,Object>();
        HashMap<String,Object> userSchedule =(HashMap<String,Object>) hashMapSaved.get(DatabaseConfiguration.USER_SCHEDULE);
        ArrayList<String> listTime = new ArrayList<String>();
        ArrayList<String> listDescription = new ArrayList<String>();
        for(String key:userSchedule.keySet()){
            HashMap<String,String> userData =  (HashMap<String,String>)userSchedule.get(key);
            String userId = userData.get(DatabaseConfiguration.DAYSCHEDULE_USERID);
            String date = userData.get(DatabaseConfiguration.DAYSCHEDULE_DATE);
            if(userName.equals(userId) && dayInserted.equals(date)){
                //get all the schedule of that particular person
                String time = userData.get(DatabaseConfiguration.DAYSCHEDULE_TIME);
                String description = userData.get(DatabaseConfiguration.DAYSCHEDULE_DESCRIPTION);
                listTime.add(time);
                listDescription.add(description);
            }
        }
        returnHash.put("listTime", listTime);
        returnHash.put("listMessage", listDescription);
        return returnHash;
    }

    @Override
    public void deleteData(String userName) {

    }

    public ArrayList<DaySchedule> searchNewNotification(String userName){
        ArrayList<DaySchedule> listDaySchedule = new ArrayList<DaySchedule>();
        HashMap<String,Object> userSchedule =(HashMap<String,Object>) hashMapSaved.get(DatabaseConfiguration.USER_SCHEDULE);
        for(String key:userSchedule.keySet()) {
            HashMap<String,Object> userData =  (HashMap<String,Object>)userSchedule.get(key);
            String name = (String)userData.get(DatabaseConfiguration.USERSCHEDULE_USERID);
            String date = (String)userData.get(DatabaseConfiguration.DAYSCHEDULE_DATE);
            if(userName.equals(name)){
                //get all the schedule of that particular person
                String time = (String)userData.get(DatabaseConfiguration.DAYSCHEDULE_TIME);
                String description = (String)userData.get(DatabaseConfiguration.DAYSCHEDULE_DESCRIPTION);
                long futureTimeMillis = (long)userData.get(DatabaseConfiguration.DAYSCHEDULE_FUTURETIMEMILLIS);
                DaySchedule daySchedule = new DaySchedule(date, time, userName, description,futureTimeMillis);
                listDaySchedule.add(daySchedule);
            }
        }
        return listDaySchedule;
    }

}

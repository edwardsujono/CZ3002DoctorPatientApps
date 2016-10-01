package ntu.com.mylife.common.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import ntu.com.mylife.common.entity.databaseentity.DaySchedule;
import ntu.com.mylife.common.entity.databaseentity.UserSchedule;

/**
 * Created by LENOVO on 03/09/2016.
 */

//this is an FireBase Accessor to Update,Create,etc database

public class DatabaseDaoUserScheduleImpl implements DatabaseUserScheduleDao{

    private Context myContext;
    private HashMap hashMapSaved;
    private Firebase firebaseDb;
    private String nameUser,timeSchedule;

    public DatabaseDaoUserScheduleImpl(final Context context, final MyCallback callback, final String nameUser, final String timeSchedule){
        this.myContext = context;
        this.firebaseDb = new Firebase("https://lifemate.firebaseio.com/");
        this.nameUser = nameUser;
        this.timeSchedule = timeSchedule;

        //always put the event listener at constructor
        //this below code will create separate thread so all this functionality will be
        //asynchronous

        firebaseDb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                hashMapSaved = (HashMap) snapshot.getValue();
                try {
                    HashMap hashReturned = (HashMap) searchData(nameUser, timeSchedule);
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
    public DatabaseDaoUserScheduleImpl(){
        this.firebaseDb = new Firebase("https://lifemate.firebaseio.com/");
    }

    public DatabaseDaoUserScheduleImpl(final MyCallback callback, final String userName){
        this.firebaseDb = new Firebase("https://lifemate.firebaseio.com/");
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
        Firebase scheduleObject = firebaseDb.child("UserSchedule");
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
        HashMap<String,Object> userSchedule =(HashMap<String,Object>) hashMapSaved.get("UserSchedule");
        ArrayList<String> listTime = new ArrayList<String>();
        ArrayList<String> listDescription = new ArrayList<String>();
        for(String key:userSchedule.keySet()){
            HashMap<String,String> userData =  (HashMap<String,String>)userSchedule.get(key);
            String name = userData.get("userName");
            String date = userData.get("date");
            if(userName.equals(name) && dayInserted.equals(date)){
                //get all the schedule of that particular person
                String time = userData.get("time");
                String description = userData.get("description");
                listTime.add(time);
                listDescription.add(description);
            }
        }
        returnHash.put("listTime",listTime);
        returnHash.put("listMessage",listDescription);
        return returnHash;
    }

    @Override
    public void deleteData(String userName) {

    }

    public ArrayList<DaySchedule> searchNewNotification(String userName){
        ArrayList<DaySchedule> listDaySchedule = new ArrayList<DaySchedule>();
        HashMap<String,Object> userSchedule =(HashMap<String,Object>) hashMapSaved.get("UserSchedule");
        for(String key:userSchedule.keySet()){
            HashMap<String,Object> userData =  (HashMap<String,Object>)userSchedule.get(key);
            String name = (String)userData.get("userName");
            String date = (String)userData.get("date");
            if(userName.equals(name)){
                //get all the schedule of that particular person
                String time = (String)userData.get("time");
                String description = (String)userData.get("description");
                long futureTimeMillis = (long)userData.get("futureTimeMillis");
                DaySchedule daySchedule = new DaySchedule(date,time,userName,description,futureTimeMillis);
                listDaySchedule.add(daySchedule);
            }
        }
        return listDaySchedule;
    }

}

package ntu.com.mylife.common.service;

import android.content.Context;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

import ntu.com.mylife.common.data.Doctor;
import ntu.com.mylife.common.data.Patient;
import ntu.com.mylife.common.data.UserSchedule;
import ntu.com.mylife.common.data.UserType;

/**
 * Created by LENOVO on 03/09/2016.
 */

//this is an FireBase Accessor to Update,Create,etc database

public class DatabaseDaoUserScheduleImpl implements DatabaseUserScheduleDao{

    private Context myContext;
    private HashMap hashMapSaved;
    private Firebase firebaseDb;
    private String nameUser,timeSchedule;

    public DatabaseDaoUserScheduleImpl(final Context context, final Callback callback, final String nameUser, final String timeSchedule){
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


    @Override
    public void addData() {

    }

    @Override
    public Object searchData(String userName,String dayInserted) {

        /* JSON Data Structure
        Under UserSchedule on FireBase
            {"userName": "edward454",
             "schedule" : [
                {"day" : "14-December-2016"
                 "timeSchedule" : [
                    {"time" : "12:30" , "message" : "take your medicine"}
                 ]
                }
             ]}
       */
        ArrayList<String> listMessage = new ArrayList<String>();
        ArrayList<String> listTime = new ArrayList<String>();
        final HashMap<String,Object> listReturned = new HashMap<String,Object>();


        ArrayList<HashMap> listUserSchedule = (ArrayList<HashMap>)hashMapSaved.get("UserSchedule");
        for(HashMap hashData: listUserSchedule){
            String nameCompared = (String) hashData.get("userName");
            if (nameCompared.equals(userName)) {
                ArrayList userSchedule = (ArrayList) hashData.get("schedule");
                for (HashMap hashSchedule : (ArrayList<HashMap>) userSchedule) {
                    String day = (String) hashSchedule.get("day");
                    if (day.equals(dayInserted)) {
                        ArrayList<HashMap> timeSchedules = (ArrayList<HashMap>) hashSchedule.get("timeSchedule");
                        for (HashMap hashTimeSchedule : timeSchedules) {
                            String timeTimeSchedule = (String) hashTimeSchedule.get("time");
                            String messageTimeSchedule = (String) hashTimeSchedule.get("message");
                            listMessage.add(messageTimeSchedule);
                            listTime.add(timeTimeSchedule);
                        }
                    }
                }

        }
        }

        listReturned.put("listMessage",listMessage);
        listReturned.put("listTime",listTime);
        return listReturned;
    }

    @Override
    public void deleteData(String userName) {

    }

}

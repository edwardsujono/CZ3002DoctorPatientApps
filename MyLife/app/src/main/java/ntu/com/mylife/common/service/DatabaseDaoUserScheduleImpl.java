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

    public DatabaseDaoUserScheduleImpl(Context context){
        this.myContext = context;
        this.firebaseDb = new Firebase("https://lifemate.firebaseio.com/");

        //always put the event listener at constructor
        //this below code will create separate thread so all this functionality will be
        //asynchronous

        firebaseDb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                hashMapSaved = (HashMap) snapshot.getValue();
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
    public Object searchData(String userName) {

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

        final ArrayList<Object> listReturned = new ArrayList<Object>();
        HashMap hashUsers = (HashMap)hashMapSaved.get("UserSchedule");
        for(Object key:hashUsers.keySet()){
            HashMap userMaps = (HashMap)hashUsers.get(key);
            String nameCompared = (String) userMaps.get("userName");

        }


        return listReturned;
    }

    @Override
    public void deleteData(String userName) {

    }
}

package ntu.com.mylife.common.service;
import android.util.Log;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

import ntu.com.mylife.common.entity.databaseentity.DatabaseConfiguration;
import ntu.com.mylife.common.entity.databaseentity.Doctor;
import ntu.com.mylife.common.entity.databaseentity.Patient;
import ntu.com.mylife.common.entity.databaseentity.UserType;

/**
 * Created by LENOVO on 01/09/2016.
 */


/*FireBase data will be saved in form of JSON file

   Structure data please defined here:
   User patient data dtructure
 {"patient" :  {
                    "name" : "Edward Sujono"
                    "email" : "wowdogs"
                    "password" : "hahaha"
                    "age" : 17
               }
 }


* */

public class UserDaoImpl implements UserDao {

    private Firebase firebaseDb;
    private HashMap hashMapSaved;


    public UserDaoImpl(){

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
                Log.d("Read failed", firebaseError.getMessage());
            }
        });

    }


    @Override
    public void addData(UserType.Type type, Object object) {

        if(type == UserType.Type.PATIENT){
            //save patient data record
            Patient patient = (Patient) object;
            Firebase basePatient =  firebaseDb.child(DatabaseConfiguration.PATIENTS);
            Firebase listPatients = basePatient.push();
            listPatients.setValue(patient);
        }else{
            //save doctor data record
            Doctor doctor =  (Doctor) object;
            Firebase baseDoctor =  firebaseDb.child(DatabaseConfiguration.DOCTORS);
            Firebase listDoctors = baseDoctor.push();
            listDoctors.setValue(doctor);
        }
    }

    @Override
    public void deleteData(UserType.Type type, Object object) {

    }

    @Override
    public Object findData(UserType.Type type) {
        final ArrayList<Object> listReturned = new ArrayList<Object>();
        Log.i("executed Find Data","yes");
        if(type == UserType.Type.PATIENT){
            HashMap hashPatients = (HashMap)hashMapSaved.get(DatabaseConfiguration.PATIENTS);
            for(Object key:hashPatients.keySet()){
                HashMap patientMaps = (HashMap)hashPatients.get(key);
                String email =(String) patientMaps.get(DatabaseConfiguration.USER_EMAIL);
                String password = (String) patientMaps.get(DatabaseConfiguration.USER_PASSWORD);
                String userId = (String) patientMaps.get(DatabaseConfiguration.USER_USERID);
                String fullName = (String) patientMaps.get(DatabaseConfiguration.USER_FULLNAME);
                String encodedImage = (String) patientMaps.get(DatabaseConfiguration.USER_IMAGE);
                Patient patient = new Patient(fullName,userId,email,password, encodedImage);
                //just for patient need to get the other info regarding with the user
                listReturned.add(patient);
            }
       }else{
            HashMap hashDoctor = (HashMap)hashMapSaved.get(DatabaseConfiguration.DOCTORS);
            for(Object key:hashDoctor.keySet()){
                HashMap doctorMaps = (HashMap)hashDoctor.get(key);
                String email =(String) doctorMaps.get(DatabaseConfiguration.USER_EMAIL);
                String password = (String) doctorMaps.get(DatabaseConfiguration.USER_PASSWORD);
                String userId = (String) doctorMaps.get(DatabaseConfiguration.USER_USERID);
                String fullName = (String) doctorMaps.get(DatabaseConfiguration.USER_FULLNAME);
                String encodedImage = (String) doctorMaps.get(DatabaseConfiguration.USER_IMAGE);
                Doctor doctor = new Doctor(fullName,userId,email,password, encodedImage);
                listReturned.add(doctor);
            }
       }
        return listReturned;
    }







}

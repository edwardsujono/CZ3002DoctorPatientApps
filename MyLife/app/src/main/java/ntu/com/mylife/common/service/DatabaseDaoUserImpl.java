package ntu.com.mylife.common.service;
import android.util.Log;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import ntu.com.mylife.common.data.Doctor;
import ntu.com.mylife.common.data.Patient;
import ntu.com.mylife.common.data.UserType;

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

public class DatabaseDaoUserImpl implements DatabaseDaoUser {

    private Firebase firebaseDb;
    private static String PATIENT = "patients";
    private static String DOCTOR = "doctors";
    private HashMap hashMapSaved ;


    public DatabaseDaoUserImpl(){

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
            Firebase basePatient =  firebaseDb.child(PATIENT);
            Firebase listPatients = basePatient.push();
            listPatients.setValue(patient);
        }else{
            //save doctor data record
            Doctor doctor =  (Doctor) object;
            Firebase baseDoctor =  firebaseDb.child(DOCTOR);
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
            HashMap hashPatients = (HashMap)hashMapSaved.get(PATIENT);
            for(Object key:hashPatients.keySet()){
                HashMap patientMaps = (HashMap)hashPatients.get(key);
                String email =(String) patientMaps.get("email");
                String password = (String) patientMaps.get("password");
                String userName = (String) patientMaps.get("userName");
                String fullName = (String) patientMaps.get("fullName");
                Patient patient = new Patient(fullName,userName,email,password);
                //just for patient need to get the other info regarding with the user
                listReturned.add(patient);
            }
       }else{
            HashMap hashDoctor = (HashMap)hashMapSaved.get(DOCTOR);
            for(Object key:hashDoctor.keySet()){
                HashMap doctorMaps = (HashMap)hashDoctor.get(key);
                String email =(String) doctorMaps.get("email");
                String password = (String) doctorMaps.get("password");
                String userName = (String) doctorMaps.get("userName");
                String fullName = (String) doctorMaps.get("fullName");
                Doctor doctor = new Doctor(fullName,userName,email,password);
                listReturned.add(doctor);
            }
       }
        return listReturned;
    }







}

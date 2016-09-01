package ntu.com.mylife.common.service;

import android.util.Log;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

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


public class DatabaseDaoUserImpl implements DatabaseDao {

    private Firebase firebaseDb;
    private static String PATIENT = "patient";
    private static String DOCTOR = "doctor";


    public DatabaseDaoUserImpl(){
        this.firebaseDb = new Firebase("https://lifemate.firebaseio.com/");
    }


    @Override
    public void addData(UserType.Type type, Object object) {

        if(type.equals(UserType.Type.PATIENT)){
            //save patient data record
            Patient patient = (Patient) object;
            Firebase basePatient =  firebaseDb.child(PATIENT);
            basePatient.setValue(patient);
        }else{
            //save doctor data record
            Doctor doctor =  (Doctor) object;
            Firebase baseDoctor =  firebaseDb.child(DOCTOR);
            baseDoctor.setValue(doctor);
        }
    }

    @Override
    public void deleteData(UserType.Type type, Object object) {

    }

    @Override
    public Object findData(UserType.Type type) {
        final ArrayList<Object> listReturned = new ArrayList<Object>();

        if(type.equals(UserType.Type.PATIENT)){
           firebaseDb.addValueEventListener(new ValueEventListener() {
               @Override
               public void onDataChange(DataSnapshot snapshot) {
                   for(DataSnapshot child:snapshot.getChildren()){
                       Patient patient = child.getValue(Patient.class);
                       listReturned.add(patient);
                       Log.i("email Patient",patient.getEmail());
                       Log.i("password Patient",patient.getPassword());
                   }
               }

               @Override
               public void onCancelled(FirebaseError firebaseError) {
                   System.out.println("The read failed: " + firebaseError.getMessage());
               }
           });
       }else{
            // Otherwise it is doctor type of user
            firebaseDb.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    for(DataSnapshot child:snapshot.getChildren()){
                        Doctor doctor = child.getValue(Doctor.class);
                        listReturned.add(doctor);
                        Log.i("email Doctor",doctor.getEmail());
                        Log.i("password Doctor",doctor.getPassword());
                    }
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {
                    System.out.println("The read failed: " + firebaseError.getMessage());
                }
            });
       }
        return listReturned;
    }





}

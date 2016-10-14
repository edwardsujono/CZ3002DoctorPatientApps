package ntu.com.mylife.controller;


import android.content.Context;

import com.firebase.client.Firebase;

import ntu.com.mylife.common.entity.databaseentity.Doctor;
import ntu.com.mylife.common.entity.databaseentity.Patient;
import ntu.com.mylife.common.entity.databaseentity.UserType;
import ntu.com.mylife.common.service.AlertDialogService;
import ntu.com.mylife.common.service.SharedPreferencesKey;
import ntu.com.mylife.common.service.UserDao;
import ntu.com.mylife.common.service.UserDaoImpl;
import ntu.com.mylife.common.service.SharedPreferencesService;

/**
 * Created by LENOVO on 03/09/2016.
 */
public class SignUpController {

    private UserDao db;
    private AlertDialogService alertDialog;
    private Context myContext;
    private SharedPreferencesService sharedPreferencesService;

    public SignUpController(Context myContext){
        Firebase.setAndroidContext(myContext);
        this.db =  new UserDaoImpl();
        alertDialog = new AlertDialogService(myContext);
        this.myContext = myContext;
        sharedPreferencesService = new SharedPreferencesService(myContext);
    }


    public boolean processSignUp(String userName,String fullName,String email,String password,String reenterPasaword,UserType.Type type){
        if(userName.equals("") || fullName.equals("") || email.equals("") || password.equals("") || reenterPasaword.equals("") ){
            alertDialog.showDialog("Please Fill all the field given");
            return false;
        }

        if(!password.equals(reenterPasaword)){
            alertDialog.showDialog("password and re-enter password did not match");
            return false;
        }
        try {
            if (type == UserType.Type.PATIENT) {
                Patient patient = new Patient(fullName, userName, email, password, "");
                db.addData(type, patient);
                sharedPreferencesService.saveToSharedPreferences(SharedPreferencesKey.NAME_SHARED_PREFERENCES, SharedPreferencesKey.KEY_USER, userName);
                sharedPreferencesService.saveToSharedPreferences(SharedPreferencesKey.NAME_SHARED_PREFERENCES, SharedPreferencesKey.KEY_USERTYPE,type.toString());
            } else {
                Doctor doctor = new Doctor(fullName, userName, email, password, "");
                db.addData(type, doctor);
                sharedPreferencesService.saveToSharedPreferences(SharedPreferencesKey.NAME_SHARED_PREFERENCES,SharedPreferencesKey.KEY_USER,userName);
                sharedPreferencesService.saveToSharedPreferences(SharedPreferencesKey.NAME_SHARED_PREFERENCES,SharedPreferencesKey.KEY_USERTYPE,type.toString());
            }
            alertDialog.showDialog("Sign In to your account");
            return true;
        }catch(Exception e){
            return false;
        }
    }





}
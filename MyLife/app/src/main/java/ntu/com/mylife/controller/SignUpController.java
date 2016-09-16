package ntu.com.mylife.controller;


import android.content.Context;

import com.firebase.client.Firebase;

import ntu.com.mylife.common.data.Doctor;
import ntu.com.mylife.common.data.Patient;
import ntu.com.mylife.common.data.UserType;
import ntu.com.mylife.common.service.AlertDialogService;
import ntu.com.mylife.common.service.DatabaseDaoUser;
import ntu.com.mylife.common.service.DatabaseDaoUserImpl;

/**
 * Created by LENOVO on 03/09/2016.
 */
public class SignUpController {

    private DatabaseDaoUser db;
    private AlertDialogService alertDialog;

    public SignUpController(Context myContext){
        Firebase.setAndroidContext(myContext);
        this.db =  new DatabaseDaoUserImpl();
        alertDialog = new AlertDialogService(myContext);
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
                Patient patient = new Patient(fullName, userName, email, password);
                db.addData(type, patient);
            } else {
                Doctor doctor = new Doctor(fullName, userName, email, password);
                db.addData(type, doctor);
            }
            alertDialog.showDialog("Sign In to your account");
            return true;
        }catch(Exception e){
            return false;
        }

    }


}

package ntu.com.mylife.controller;

import android.content.Context;
import android.widget.Toast;

import com.firebase.client.Firebase;

import java.util.ArrayList;

import ntu.com.mylife.common.entity.databaseentity.Doctor;
import ntu.com.mylife.common.entity.databaseentity.Patient;
import ntu.com.mylife.common.entity.databaseentity.UserType;
import ntu.com.mylife.common.service.SharedPreferencesKey;
import ntu.com.mylife.common.service.UserDao;
import ntu.com.mylife.common.service.UserDaoImpl;
import ntu.com.mylife.common.service.SharedPreferencesService;

/**
 * Created by LENOVO on 01/09/2016.
 */
public class SignInController {

    private UserDao db;
    private Context myContext;
    private SharedPreferencesService sharedPreferencesService;


    public SignInController(Context context){
        Firebase.setAndroidContext(context);
        this.db = new UserDaoImpl();
        this.myContext = context;
        sharedPreferencesService = new SharedPreferencesService(context);
    }

    public boolean processSignIn(UserType.Type type,String userName,String password){

        ArrayList listUserFromDb = new ArrayList();
        try {
            listUserFromDb = (ArrayList) db.findData(type);
            if(type == UserType.Type.PATIENT){
                for(Patient patient:(ArrayList<Patient>) listUserFromDb){
                    if(patient.getUserId().equals(userName) && patient.getPassword().equals(password)){
                        Toast.makeText(myContext,"Sign In To Your Account",Toast.LENGTH_LONG).show();
                        sharedPreferencesService.saveToSharedPreferences(SharedPreferencesKey.NAME_SHARED_PREFERENCES, SharedPreferencesKey.KEY_USER, userName);
                        sharedPreferencesService.saveToSharedPreferences(SharedPreferencesKey.NAME_SHARED_PREFERENCES, SharedPreferencesKey.KEY_USERTYPE, type.toString());
                        return true;
                    }
                }
            }else{
                for(Doctor doctor:(ArrayList<Doctor>) listUserFromDb){
                    if(doctor.getUserId().equals(userName) && doctor.getPassword().equals(password)){
                        Toast.makeText(myContext,"Sign In To Your Account",Toast.LENGTH_LONG).show();
                        sharedPreferencesService.saveToSharedPreferences(SharedPreferencesKey.NAME_SHARED_PREFERENCES,SharedPreferencesKey.KEY_USER,userName);
                        sharedPreferencesService.saveToSharedPreferences(SharedPreferencesKey.NAME_SHARED_PREFERENCES,SharedPreferencesKey.KEY_USERTYPE,type.toString());
                        return true;
                    }
                }
            }
            Toast.makeText(myContext,"User has not been registered yet",Toast.LENGTH_LONG).show();

        }catch(Exception e){
            return false;
        }

        return false;
    }





}

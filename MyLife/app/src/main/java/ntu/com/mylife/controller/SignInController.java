package ntu.com.mylife.controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.firebase.client.Firebase;

import java.util.ArrayList;

import ntu.com.mylife.common.data.Doctor;
import ntu.com.mylife.common.data.Patient;
import ntu.com.mylife.common.data.UserType;
import ntu.com.mylife.common.service.DatabaseDaoUser;
import ntu.com.mylife.common.service.DatabaseDaoUserImpl;
import ntu.com.mylife.common.service.SharedPreferencesService;

/**
 * Created by LENOVO on 01/09/2016.
 */
public class SignInController {

    private DatabaseDaoUser db;
    private Context myContext;
    private SharedPreferencesService sharedPreferencesService;
    private static String KEY_USER = "userName",NAME_SHARED_PREFERENCES = "UserSharedPreferences";


    public SignInController(Context context){
        Firebase.setAndroidContext(context);
        this.db = new DatabaseDaoUserImpl();
        this.myContext = context;
        sharedPreferencesService = new SharedPreferencesService(context);
    }

    public boolean processSignIn(UserType.Type type,String userName,String password){

        ArrayList listUserFromDb = new ArrayList();
        try {
            listUserFromDb = (ArrayList) db.findData(type);
            if(type == UserType.Type.PATIENT){
                for(Patient patient:(ArrayList<Patient>) listUserFromDb){
                    if(patient.getUserName().equals(userName) && patient.getPassword().equals(password)){
                        Toast.makeText(myContext,"Sign In To Your Account",Toast.LENGTH_LONG).show();
                        sharedPreferencesService.saveToSharedPreferences(NAME_SHARED_PREFERENCES,KEY_USER,userName);
                        return true;
                    }
                }
            }else{
                for(Doctor doctor:(ArrayList<Doctor>) listUserFromDb){
                    if(doctor.getUserName().equals(userName) && doctor.getPassword().equals(password)){
                        Toast.makeText(myContext,"Sign In To Your Account",Toast.LENGTH_LONG).show();
                        sharedPreferencesService.saveToSharedPreferences(NAME_SHARED_PREFERENCES,KEY_USER,userName);
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

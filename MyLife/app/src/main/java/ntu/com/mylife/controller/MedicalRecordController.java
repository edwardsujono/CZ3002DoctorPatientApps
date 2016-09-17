package ntu.com.mylife.controller;

import android.app.Activity;

import java.util.ArrayList;

import ntu.com.mylife.common.entity.applicationentity.SharedPreferencesKey;
import ntu.com.mylife.common.entity.databaseentity.MedicalRecord;
import ntu.com.mylife.common.service.DatabaseDaoMedicalRecord;
import ntu.com.mylife.common.service.DatabaseDaoMedicalRecordImpl;
import ntu.com.mylife.common.service.MyCallback;
import ntu.com.mylife.common.service.SharedPreferencesService;

/**
 * Created by LENOVO on 17/09/2016.
 */
public class MedicalRecordController {

    private Activity activity;
    private MyCallback callback;
    private DatabaseDaoMedicalRecord db;
    private SharedPreferencesService sharedPreferencesService;

    public MedicalRecordController(Activity activity,MyCallback callback){
        this.activity = activity;
        this.callback = callback;
        db = new DatabaseDaoMedicalRecordImpl(activity,callback);
        sharedPreferencesService = new SharedPreferencesService(activity.getBaseContext());
    }


    public ArrayList<MedicalRecord> getListMedicalRecord(){
       ArrayList<MedicalRecord> listReturned = (ArrayList<MedicalRecord>)db.getRecord(
               sharedPreferencesService.getDataFromSharedPreferences(SharedPreferencesKey.NAME_SHARED_PREFERENCES,SharedPreferencesKey.KEY_USER));
        return listReturned;
    }

}

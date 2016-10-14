package ntu.com.mylife.controller;

import android.app.Activity;

import java.util.ArrayList;

import ntu.com.mylife.common.service.SharedPreferencesKey;
import ntu.com.mylife.common.entity.databaseentity.MedicalRecord;
import ntu.com.mylife.common.service.MedicalRecordDao;
import ntu.com.mylife.common.service.MedicalRecordDaoImpl;
import ntu.com.mylife.common.service.BaseCallback;
import ntu.com.mylife.common.service.SharedPreferencesService;

/**
 * Created by LENOVO on 17/09/2016.
 */
public class MedicalRecordController {

    private Activity activity;
    private BaseCallback callback;
    private MedicalRecordDao db;
    private SharedPreferencesService sharedPreferencesService;

    public MedicalRecordController(Activity activity,BaseCallback callback){
        this.activity = activity;
        this.callback = callback;
        db = new MedicalRecordDaoImpl(activity,callback);
        sharedPreferencesService = new SharedPreferencesService(activity.getBaseContext());
    }


    public ArrayList<MedicalRecord> getListMedicalRecord(){
       ArrayList<MedicalRecord> listReturned = (ArrayList<MedicalRecord>)db.getRecord(
               sharedPreferencesService.getDataFromSharedPreferences(SharedPreferencesKey.NAME_SHARED_PREFERENCES,SharedPreferencesKey.KEY_USER));
        return listReturned;
    }

}

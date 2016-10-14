package ntu.com.mylife.common.service;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

import ntu.com.mylife.common.entity.databaseentity.DatabaseConfiguration;
import ntu.com.mylife.common.entity.databaseentity.UserMedicalRecordList;
import ntu.com.mylife.common.entity.databaseentity.MedicalRecord;

/**
 * Created by LENOVO on 17/09/2016.
 */
public class MedicalRecordDaoImpl implements MedicalRecordDao {

    private Activity activity;
    private Firebase firebaseDb;
    private HashMap<String,Object> hashMapSaved = new HashMap<String,Object>();
    private BaseCallback callback = null;
    private SharedPreferencesService sharedPreferencesService;


    public MedicalRecordDaoImpl(Activity activity, BaseCallback callback){
        this.activity = activity;
        this.callback = callback;
        this.sharedPreferencesService = new SharedPreferencesService(activity.getBaseContext());
        //initialize firebaseDB
        this.firebaseDb = new Firebase(DatabaseConfiguration.DATABASE_URL);

        //always put the event listener at constructor
        //this below code will create separate thread so all this functionality will be
        //asynchronous

        firebaseDb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                hashMapSaved = (HashMap) snapshot.getValue();
                getRecord(sharedPreferencesService.getDataFromSharedPreferences(SharedPreferencesKey.NAME_SHARED_PREFERENCES,SharedPreferencesKey.KEY_USER));
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.d("Read failed", firebaseError.getMessage());
            }
        });
    }


    //constructor useful for adding because it's process no need to have callback function
    public MedicalRecordDaoImpl(Activity activity){
        this.activity = activity;
        this.firebaseDb = new Firebase(DatabaseConfiguration.DATABASE_URL);
    }


    @Override
    public Object getRecord(Object object) {
        ArrayList<MedicalRecord> listMedicalRecord = new ArrayList<MedicalRecord>();
        HashMap<String,Object> mapMedicalRecords = (HashMap<String,Object>) hashMapSaved.get(DatabaseConfiguration.MEDICAL_RECORD);
        if(mapMedicalRecords == null) return null;
        for(String key: mapMedicalRecords.keySet()){
            //if userID asked same with the userId checked
            HashMap<String,Object> mapObjectRetrieved =(HashMap<String,Object>) mapMedicalRecords.get(key);
            String userId = (String) mapObjectRetrieved.get(DatabaseConfiguration.USERMEDICALRECORDLIST_USERID);
            if(userId.equals(object)){
                ArrayList<HashMap<String,Object>> mapListRecord  = (ArrayList<HashMap<String,Object>>) mapObjectRetrieved.get(DatabaseConfiguration.USERMEDICALRECORDLIST_MEDICALRECORDLIST);
                for(HashMap<String,Object> recordIter: mapListRecord){
                    String time =(String) recordIter.get(DatabaseConfiguration.MEDICALRECORD_TIME);
                    String medicalRecordDescription = (String) recordIter.get(DatabaseConfiguration.MEDICALRECORD_MEDICALRECORDDESCRIPTION);
                    String doctorId = (String) recordIter.get(DatabaseConfiguration.REMINDER_DOCTORID);
                    MedicalRecord medicalRecord = new MedicalRecord(time,medicalRecordDescription,doctorId);
                    listMedicalRecord.add(medicalRecord);
                }
            }
        }
        if(callback != null)callback.callbackFunction(listMedicalRecord);
        return listMedicalRecord;
    }

    @Override
    public void addNewMedicalRecord(Object object,String userName) {

        MedicalRecord medicalRecord = (MedicalRecord) object;
        Object objectToInsert;
        Firebase baseMedicalReport = firebaseDb.child(DatabaseConfiguration.MEDICAL_RECORD);
        ArrayList<MedicalRecord> listAddedObject = new ArrayList<MedicalRecord>();
        listAddedObject.add(medicalRecord);
        UserMedicalRecordList listMedicalRecordUser = new UserMedicalRecordList(userName, listAddedObject);
        objectToInsert = listMedicalRecordUser;
        Firebase addedMedicalRecords = baseMedicalReport.push();
        addedMedicalRecords.setValue(objectToInsert);
        Toast.makeText(activity, "Data is saved on our system", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void deleteMedicalRecord(Object object) {

    }





}

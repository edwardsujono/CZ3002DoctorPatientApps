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

import ntu.com.mylife.common.entity.applicationentity.SharedPreferencesKey;
import ntu.com.mylife.common.entity.databaseentity.FireBaseKey;
import ntu.com.mylife.common.entity.databaseentity.ListMedicalRecordUser;
import ntu.com.mylife.common.entity.databaseentity.MedicalRecord;

/**
 * Created by LENOVO on 17/09/2016.
 */
public class DatabaseDaoMedicalRecordImpl implements DatabaseDaoMedicalRecord{

    private Activity activity;
    private Firebase firebaseDb;
    private HashMap<String,Object> hashMapSaved = new HashMap<String,Object>();
    private MyCallback callback = null;
    private SharedPreferencesService sharedPreferencesService;


    public DatabaseDaoMedicalRecordImpl(Activity activity, MyCallback callback){
        this.activity = activity;
        this.callback = callback;
        this.sharedPreferencesService = new SharedPreferencesService(activity.getBaseContext());
        //initialize firebaseDB
        this.firebaseDb = new Firebase("https://lifemate.firebaseio.com/");

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
    public DatabaseDaoMedicalRecordImpl(Activity activity){
        this.activity = activity;
        this.firebaseDb = new Firebase("https://lifemate.firebaseio.com/");
    }


    @Override
    public Object getRecord(Object object) {
        ArrayList<MedicalRecord> listMedicalRecord = new ArrayList<MedicalRecord>();
        HashMap<String,Object> mapMedicalRecords = (HashMap<String,Object>) hashMapSaved.get("MedicalRecords");
        if(mapMedicalRecords == null) return null;
        for(String key: mapMedicalRecords.keySet()){
            //if userID asked same with the userId checked
            HashMap<String,Object> mapObjectRetrieved =(HashMap<String,Object>) mapMedicalRecords.get(key);
            String userId = (String) mapObjectRetrieved.get("userName");
            if(userId.equals(object)){
                ArrayList<HashMap<String,Object>> mapListRecord  = (ArrayList<HashMap<String,Object>>) mapObjectRetrieved.get("listmedicalRecord");
                for(HashMap<String,Object> recordIter: mapListRecord){
                    String time =(String) recordIter.get("time");
                    String medicalRecordDescription = (String) recordIter.get("medicalRecordDescription");
                    String fromDoctor = (String) recordIter.get("fromDoctor");
                    MedicalRecord medicalRecord = new MedicalRecord(time,medicalRecordDescription,fromDoctor);
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
        Object objectToInsert = medicalRecord;
        Firebase baseMedicalReport = firebaseDb.child(FireBaseKey.MEDICAL_RECORD);
        ArrayList<MedicalRecord> listAddedObject = new ArrayList<MedicalRecord>();
        listAddedObject.add(medicalRecord);
        ListMedicalRecordUser listMedicalRecordUser = new ListMedicalRecordUser(userName, listAddedObject);
        objectToInsert = listMedicalRecordUser;
        Firebase addedMedicalRecords = baseMedicalReport.push();
        addedMedicalRecords.setValue(objectToInsert);
        Toast.makeText(activity, "Data is saved on our system", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void deleteMedicalRecord(Object object) {

    }





}

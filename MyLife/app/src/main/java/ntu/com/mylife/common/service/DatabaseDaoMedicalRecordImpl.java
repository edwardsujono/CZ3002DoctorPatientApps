package ntu.com.mylife.common.service;

import android.app.Activity;
import android.util.Log;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

import ntu.com.mylife.common.entity.databaseentity.MedicalRecord;

/**
 * Created by LENOVO on 17/09/2016.
 */
public class DatabaseDaoMedicalRecordImpl implements DatabaseDaoMedicalRecord{

    private Activity activity;
    private Firebase firebaseDb;
    private HashMap<String,Object> hashMapSaved;


    public DatabaseDaoMedicalRecordImpl(Activity activity){
        this.activity = activity;


        //initialize firebaseDB
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
    public Object getRecord(Object object) {
        ArrayList<MedicalRecord> listMedicalRecord = new ArrayList<MedicalRecord>();
        ArrayList<HashMap<String,Object>> mapMedicalRecords = (ArrayList<HashMap<String,Object>>)hashMapSaved.get("MedicalRecords");
        for(HashMap<String,Object> mapIter: mapMedicalRecords){
            //if userID asked same with the userId checked
            if(mapIter.get("userId").equals(object)){
                ArrayList<HashMap<String,Object>> mapListRecord  = (ArrayList<HashMap<String,Object>>) mapIter.get("listRecord");
                for(HashMap<String,Object> recordIter: mapListRecord){
                    String time =(String) recordIter.get("time");
                    String medicalRecordDescription = (String) recordIter.get("medicalRecordDescription");
                    MedicalRecord medicalRecord = new MedicalRecord(time,medicalRecordDescription);
                    listMedicalRecord.add(medicalRecord);
                }
            }
        }
        return listMedicalRecord;
    }

    @Override
    public void addNewMedicalRecord(Object object) {

    }

    @Override
    public void deleteMedicalRecord(Object object) {

    }
}

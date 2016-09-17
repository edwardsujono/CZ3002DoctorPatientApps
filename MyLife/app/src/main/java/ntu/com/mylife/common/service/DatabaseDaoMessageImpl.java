package ntu.com.mylife.common.service;

import android.util.Log;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

import ntu.com.mylife.common.entity.databaseentity.Message;

/**
 * Created by MARTINUS on 07-Sep-16.
 */
public class DatabaseDaoMessageImpl implements DatabaseDaoMessage {

    private Firebase firebaseDb;
    private HashMap hashMapSaved;
    private static final String MESSAGE = "messages";

    public DatabaseDaoMessageImpl() {

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

    public void addData(Object object) throws Exception {
        Message message = (Message) object;
        Firebase baseMessage = firebaseDb.child(MESSAGE);
        Firebase listMessage = baseMessage.push();
        listMessage.setValue(message);
        return;
    }
    public void deleteData(Object object) throws Exception {
        return;
    }
    public Object findData() throws Exception {
        final ArrayList<Object> listReturned = new ArrayList<Object>();
        Log.i("executed Find Data","yes");

        HashMap hashMessage = (HashMap)hashMapSaved.get(MESSAGE);
        for(Object key:hashMessage.keySet()){
            HashMap doctorMaps = (HashMap) hashMessage.get(key);

            //For reference
            //String id =(String) doctorMaps.get("id");

            //Temporary
            String userId = "";

            String respondentName;
            if (userId.equals(doctorMaps.get("sender"))) {
                respondentName = (String) doctorMaps.get("receiverId");
            } else {
                respondentName = (String) doctorMaps.get("senderId");
            }

            String content = (String) doctorMaps.get("message");
            String date = (String) doctorMaps.get("date");

            Message message = new Message(respondentName, respondentName, content, date);
            listReturned.add(message);
        }
        return listReturned;
    }

}

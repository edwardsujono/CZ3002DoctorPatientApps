package ntu.com.mylife.common.service;

import android.util.Log;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

import ntu.com.mylife.common.entity.databaseentity.Chat;
import ntu.com.mylife.common.entity.databaseentity.DatabaseConfiguration;

/**
 * Created by MARTINUS on 17-Sep-16.
 */
public class ChatDaoImpl implements ChatDao {

    private Firebase firebaseDb;
    private HashMap hashMapSaved;
    private BaseCallback callback;

    public ChatDaoImpl(BaseCallback callback) {

        this.firebaseDb = new Firebase(DatabaseConfiguration.DATABASE_URL);
        this.callback = callback;

        //always put the event listener at constructor
        //this below code will create separate thread so all this functionality will be
        //asynchronous
    }

    public ChatDaoImpl() {

        this.firebaseDb = new Firebase(DatabaseConfiguration.DATABASE_URL);

        //always put the event listener at constructor
        //this below code will create separate thread so all this functionality will be
        //asynchronous
    }


    public void addData(Object object) throws Exception {
        Chat chat = (Chat) object;
        Firebase baseChat = firebaseDb.child(DatabaseConfiguration.CHAT);
        Firebase listChat = baseChat.push();
        listChat.setValue(chat);
        return;
    }
    public void deleteData(Object object) throws Exception {
        return;
    }
    public void findData() throws Exception {

        firebaseDb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                hashMapSaved = (HashMap) snapshot.getValue();
                processData();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.d("Read failed", firebaseError.getMessage());
            }
        });

    }

    private void processData() {
        final ArrayList<Object> listReturned = new ArrayList<Object>();

        HashMap hashMessage = (HashMap) hashMapSaved.get(DatabaseConfiguration.CHAT);

        if(hashMessage == null)
            return;

        Log.e("Inside process data", hashMessage.toString());
        for (Object key : hashMessage.keySet()) {
            HashMap chatMaps = (HashMap) hashMessage.get(key);

            String user1Id = (String) chatMaps.get(DatabaseConfiguration.CHAT_USER1ID);
            String user2Id = (String) chatMaps.get(DatabaseConfiguration.CHAT_USER2ID);
            String latestMessage = (String) chatMaps.get(DatabaseConfiguration.CHAT_LATESTMESSAGE);
            String latestMessageTime = (String) chatMaps.get(DatabaseConfiguration.CHAT_LATESTMESSAGETIME);

            Log.e("per key", user1Id + ' ' + user2Id);

            Chat chat = new Chat(user1Id, user2Id, latestMessage, latestMessageTime);
            listReturned.add(chat);
        }

        callback.callbackFunction(listReturned);


    }

}

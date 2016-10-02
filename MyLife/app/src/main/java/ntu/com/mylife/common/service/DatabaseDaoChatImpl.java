package ntu.com.mylife.common.service;

import android.util.Log;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

import ntu.com.mylife.common.entity.databaseentity.Chat;
import ntu.com.mylife.common.entity.databaseentity.UserType;

/**
 * Created by MARTINUS on 17-Sep-16.
 */
public class DatabaseDaoChatImpl implements DatabaseDaoChat {

    private Firebase firebaseDb;
    private HashMap hashMapSaved;
    private MyCallback callback;
    private static final String CHAT = "Chat";
    private static String KEY_USER = "userName";
    private static String NAME_SHARED_PREFERENCES = "UserSharedPreferences";
    private static String USER_TYPE = "userType";

    public DatabaseDaoChatImpl(MyCallback callback) {

        this.firebaseDb = new Firebase("https://lifemate.firebaseio.com/");
        this.callback = callback;

        //always put the event listener at constructor
        //this below code will create separate thread so all this functionality will be
        //asynchronous
    }

    public DatabaseDaoChatImpl() {

        this.firebaseDb = new Firebase("https://lifemate.firebaseio.com/");

        //always put the event listener at constructor
        //this below code will create separate thread so all this functionality will be
        //asynchronous
    }


    public void addData(Object object) throws Exception {
        Chat chat = (Chat) object;
        Firebase baseChat = firebaseDb.child(CHAT);
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

        HashMap hashMessage = (HashMap) hashMapSaved.get(CHAT);

        Log.e("Inside process data", hashMessage.toString());
        for (Object key : hashMessage.keySet()) {
            HashMap chatMaps = (HashMap) hashMessage.get(key);

            String username1 = (String) chatMaps.get("username1");
            String username2 = (String) chatMaps.get("username2");
            String latestMessage = (String) chatMaps.get("latestMessage");

            String latestMessageTime = (String) chatMaps.get("latestMessageTime");

            Log.e("per key", username1 + ' ' + username2);

            Chat chat = new Chat(username1, username2, latestMessage, latestMessageTime);
            listReturned.add(chat);
        }

        callback.callbackFunction(listReturned);


    }

}

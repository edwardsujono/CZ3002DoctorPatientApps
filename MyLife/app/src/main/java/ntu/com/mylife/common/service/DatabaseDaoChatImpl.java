package ntu.com.mylife.common.service;

import android.util.Log;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

import ntu.com.mylife.common.entity.databaseentity.Chat;

/**
 * Created by MARTINUS on 17-Sep-16.
 */
public class DatabaseDaoChatImpl implements DatabaseDaoChat {

    private Firebase firebaseDb;
    private HashMap hashMapSaved;
    private static final String CHAT = "chats";

    public DatabaseDaoChatImpl() {

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
        Chat chat = (Chat) object;
        Firebase baseChat = firebaseDb.child(CHAT);
        Firebase listChat = baseChat.push();
        listChat.setValue(chat);
        return;
    }
    public void deleteData(Object object) throws Exception {
        return;
    }
    public Object findData() throws Exception {
        final ArrayList<Object> listReturned = new ArrayList<Object>();
        Log.i("executed Find Data", "yes");

        HashMap hashMessage = (HashMap) hashMapSaved.get(CHAT);
        for (Object key : hashMessage.keySet()) {
            HashMap chatMaps = (HashMap) hashMessage.get(key);

            String username1 = (String) chatMaps.get("username1");
            String username2 = (String) chatMaps.get("username2");
            String latestMessage = (String) chatMaps.get("latestMessage");
            String latestMessageTime = (String) chatMaps.get("latestMessageTime");

            Chat chat = new Chat(username1, username2, latestMessage, latestMessageTime);
            listReturned.add(chat);
        }
        return listReturned;
    }
}

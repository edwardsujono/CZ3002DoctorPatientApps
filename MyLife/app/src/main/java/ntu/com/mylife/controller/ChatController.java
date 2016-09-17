package ntu.com.mylife.controller;

import android.content.Context;

import com.firebase.client.Firebase;

import ntu.com.mylife.common.service.AlertDialogService;
import ntu.com.mylife.common.service.DatabaseDaoChat;
import ntu.com.mylife.common.service.DatabaseDaoChatImpl;
import ntu.com.mylife.common.service.SharedPreferencesService;

/**
 * Created by MARTINUS on 17-Sep-16.
 */
public class ChatController {

    private DatabaseDaoChat db;
    private AlertDialogService alertDialog;
    private Context myContext;
    private SharedPreferencesService sharedPreferencesService;
    private static String KEY_USER = "userName";
    private static String NAME_SHARED_PREFERENCES = "UserSharedPreferences";
    private String userId;

    public ChatController(Context context){
        Firebase.setAndroidContext(context);
        this.db = new DatabaseDaoChatImpl();
        this.myContext = context;
        sharedPreferencesService = new SharedPreferencesService(context);
        userId = sharedPreferencesService.getDataFromSharedPreferences(NAME_SHARED_PREFERENCES,KEY_USER);

        //Load chats
        loadChat();

    }

    private void loadChat() {

    }


}

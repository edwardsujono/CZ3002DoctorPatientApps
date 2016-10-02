package ntu.com.mylife.controller;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

import com.firebase.client.Firebase;

import java.util.ArrayList;
import java.util.List;

import ntu.com.mylife.common.entity.databaseentity.Chat;
import ntu.com.mylife.common.entity.databaseentity.User;
import ntu.com.mylife.common.entity.databaseentity.UserType;
import ntu.com.mylife.common.service.AlertDialogService;
import ntu.com.mylife.common.service.DatabaseDaoChat;
import ntu.com.mylife.common.service.DatabaseDaoChatImpl;
import ntu.com.mylife.common.service.DatabaseDaoUser;
import ntu.com.mylife.common.service.DatabaseDaoUserImpl;
import ntu.com.mylife.common.service.MyCallback;
import ntu.com.mylife.common.service.SharedPreferencesService;

/**
 * Created by MARTINUS on 17-Sep-16.
 */
public class ChatController {

    private DatabaseDaoChat daoChat;
    private DatabaseDaoUser daoUser;
    private AlertDialogService alertDialog;
    private MyCallback callback;
    private Context context;
    private SharedPreferencesService sharedPreferencesService;
    private static String KEY_USER = "userName";
    private static String NAME_SHARED_PREFERENCES = "UserSharedPreferences";
    private static String USER_TYPE = "userType";
    private String userId;
    private String userType;

    private List<ntu.com.mylife.common.entity.applicationentity.Chat> chatList;

    public ChatController(ArrayList<ntu.com.mylife.common.entity.applicationentity.Chat> chatList, Context context, MyCallback callback){
        Firebase.setAndroidContext(context);
        this.chatList = chatList;
        this.daoChat = new DatabaseDaoChatImpl(callback);
        this.daoUser = new DatabaseDaoUserImpl();
        this.context = context;
        this.callback = callback;
        sharedPreferencesService = new SharedPreferencesService(context);
        userId = sharedPreferencesService.getDataFromSharedPreferences(NAME_SHARED_PREFERENCES,KEY_USER);
        userType = sharedPreferencesService.getDataFromSharedPreferences(NAME_SHARED_PREFERENCES, USER_TYPE);

        Log.e("Key User", userId);
        Log.e("User Type", userType);

        //Load chat
        loadChat();
    }

    private void loadChat() {
        ArrayList<Object> chatObjectList = new ArrayList<Object>();
        ArrayList<Object> userObjectList = new ArrayList<Object>();

        UserType.Type oppositeUserType;
        if (userType.equals(UserType.Type.PATIENT.toString())) {
            oppositeUserType = UserType.Type.DOCTOR;
        } else {
            oppositeUserType = UserType.Type.PATIENT;
        }

        //Query chat data
        try {
            daoChat.findData();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Query user data

        /*
        try {
            userObjectList = (ArrayList<Object>) daoUser.findData(oppositeUserType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        */
        Log.e("oppositeUserType", oppositeUserType +"");



        /*
        //For each user found, find the corresponding chat data
        for (Object userObject : userObjectList) {
            User user = (User) userObject;
            String encodedImage = user.getImage();
            String respondentFullname = user.getFullName();
            String respondentUsername = user.getUserName();

            //Decode the image data
            byte[] decodedString = Base64.decode(encodedImage, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

            for (Object chatObject : chatObjectList) {
                Chat chatDB = (Chat) chatObject;
                if ((chatDB.getUsername1().equals(userId) && chatDB.getUsername2().equals(respondentUsername)) ||
                        chatDB.getUsername1().equals(respondentUsername) && chatDB.getUsername2().equals(userId)) {
                    String latestMessage = chatDB.getLatestMessage();
                    String latestMessageTime = chatDB.getLatestMessageTime();
                    ntu.com.mylife.common.entity.applicationentity.Chat chat =
                            new ntu.com.mylife.common.entity.applicationentity.Chat(bitmap, respondentFullname, respondentUsername, latestMessage, latestMessageTime);
                    chatList.add(chat);
                }
            }

        }
        */

        //Done
        //chatCallback.chatCallback();
    }

}

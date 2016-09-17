package ntu.com.mylife.controller;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

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
import ntu.com.mylife.common.service.SharedPreferencesService;

/**
 * Created by MARTINUS on 17-Sep-16.
 */
public class ChatController {

    private DatabaseDaoChat daoChat;
    private DatabaseDaoUser daoUser;
    private AlertDialogService alertDialog;
    private ChatCallback chatCallback;
    private Context context;
    private SharedPreferencesService sharedPreferencesService;
    private static String KEY_USER = "userName";
    private static String NAME_SHARED_PREFERENCES = "UserSharedPreferences";
    private static String USER_TYPE = "userType";
    private String userId;
    private String userType;

    private List<ntu.com.mylife.common.entity.applicationentity.Chat> chatList;

    public ChatController(ArrayList<ntu.com.mylife.common.entity.applicationentity.Chat> chatList, Context context, ChatCallback callback){
        Firebase.setAndroidContext(context);
        this.chatList = chatList;
        this.daoChat = new DatabaseDaoChatImpl();
        this.daoUser = new DatabaseDaoUserImpl();
        this.context = context;
        this.chatCallback = chatCallback;
        sharedPreferencesService = new SharedPreferencesService(context);
        userId = sharedPreferencesService.getDataFromSharedPreferences(NAME_SHARED_PREFERENCES,KEY_USER);
        userType = sharedPreferencesService.getDataFromSharedPreferences(NAME_SHARED_PREFERENCES, USER_TYPE);

        //Load chat
        loadChat();
    }

    private void loadChat() {
        ArrayList<Object> chatObjectList = new ArrayList<Object>();
        ArrayList<Object> userObjectList = new ArrayList<Object>();

        //Query chat data
        try {
            chatObjectList = (ArrayList<Object>) daoChat.findData();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Query user data
        UserType.Type oppositeUserType;
        if (userType == UserType.Type.PATIENT.toString()) {
            oppositeUserType = UserType.Type.DOCTOR;
        } else {
            oppositeUserType = UserType.Type.PATIENT;
        }
        try {
            chatObjectList = (ArrayList<Object>) daoUser.findData(oppositeUserType);
        } catch (Exception e) {
            e.printStackTrace();
        }


        for (Object chatObject : chatObjectList) {
            Chat chatDB = (Chat) chatObject;
            String encodedImage = "";
            String respondentUsername = "";
            String respondentName = "";
            if (chatDB.getUsername1().equals(userId)) {
                respondentUsername = chatDB.getUsername2();
            } else {
                respondentUsername = chatDB.getUsername1();
            }
            //Find the corresponding user document
            for (Object userObject : userObjectList) {
                User user = (User) userObject;
                if (respondentUsername.equals(user.getUserName())) {
                    respondentName = user.getFullName();
                    encodedImage = user.getImage();
                    break;
                }
            }

            //
            byte[] decodedString = Base64.decode(encodedImage, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

            ntu.com.mylife.common.entity.applicationentity.Chat chat = new ntu.com.mylife.common.entity.applicationentity.Chat(bitmap, respondentName, chatDB.getLatestMessage(), chatDB.getLatestMessageTime());
            chatList.add(chat);
        }

        //Done
        chatCallback.chatCallback();
    }

}

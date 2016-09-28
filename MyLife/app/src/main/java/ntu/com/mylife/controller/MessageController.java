package ntu.com.mylife.controller;

import android.content.Context;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ntu.com.mylife.common.entity.applicationentity.Message;
import ntu.com.mylife.common.entity.databaseentity.User;
import ntu.com.mylife.common.entity.databaseentity.UserType;
import ntu.com.mylife.common.service.AlertDialogService;
import ntu.com.mylife.common.service.DatabaseDaoMessage;
import ntu.com.mylife.common.service.DatabaseDaoMessageImpl;
import ntu.com.mylife.common.service.DatabaseDaoUser;
import ntu.com.mylife.common.service.DatabaseDaoUserImpl;
import ntu.com.mylife.common.service.SharedPreferencesService;

/**
 * Created by martinus on 24/9/16.
 */
public class MessageController {

    private DatabaseDaoMessage daoMessage;
    private DatabaseDaoUser daoUser;
    private AlertDialogService alertDialog;
    private MessageCallback messageCallback;
    private Context context;
    private SharedPreferencesService sharedPreferencesService;
    private static String KEY_USER = "userName";
    private static String NAME_SHARED_PREFERENCES = "UserSharedPreferences";
    private static String USER_TYPE = "userType";
    private String userId;
    private String userType;
    private String respondentUsername;
    private String respondentFullname;
    private String userFullname;

    private List<Message> messageList;

    public MessageController (String respondentUsername, ArrayList<Message> messageList, Context context, MessageCallback callback) {
        this.respondentUsername = respondentUsername;
        this.messageList = messageList;
        this.context = context;
        this.messageCallback = callback;
        this.daoMessage = new DatabaseDaoMessageImpl();
        this.daoUser = new DatabaseDaoUserImpl();
        sharedPreferencesService = new SharedPreferencesService(context);
        userId = sharedPreferencesService.getDataFromSharedPreferences(NAME_SHARED_PREFERENCES,KEY_USER);
        userType = sharedPreferencesService.getDataFromSharedPreferences(NAME_SHARED_PREFERENCES, USER_TYPE);

        //Load message
        loadMessage();
    }

    private void loadMessage() {
        ArrayList<Object> messageObjectList = new ArrayList<Object>();
        ArrayList<Object> userObjectList = new ArrayList<Object>();

        UserType.Type respondentUserType;
        //Query userdata (for respondent)
        if (userType.equals(UserType.Type.DOCTOR.toString())) {
            respondentUserType = UserType.Type.PATIENT;
        } else {
            respondentUserType = UserType.Type.DOCTOR;
        }
        try {
            userObjectList = (ArrayList<Object>) daoUser.findData(respondentUserType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (Object userObject : userObjectList) {
            User user = (User) userObject;
            if (user.getUserName().equals(respondentUsername)) {
                respondentFullname = user.getFullName();
            }
            break;
        }

        //Query userdata (for user)
        UserType.Type currentUserType;
        if (userType.equals(UserType.Type.DOCTOR.toString())) {
            currentUserType = UserType.Type.DOCTOR;
        } else {
            currentUserType = UserType.Type.PATIENT;
        }
        try {
            userObjectList = (ArrayList<Object>) daoUser.findData(currentUserType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (Object userObject : userObjectList) {
            User user = (User) userObject;
            if (user.getUserName().equals(userId)) {
                userFullname = user.getFullName();
            }
            break;
        }

        //Query message data
        try {
            messageObjectList = (ArrayList<Object>) daoMessage.findData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (Object messageObject : messageObjectList) {
            ntu.com.mylife.common.entity.databaseentity.Message message = (ntu.com.mylife.common.entity.databaseentity.Message) messageObject;
            if (message.getReceiverUsername().equals(userId) && message.getSenderUsername().equals(respondentUsername)) {
                String sender = respondentFullname;
                String messageContent = message.getMessage();
                String messageTime = message.getDate();
                Message messageApp = new Message(sender, messageContent, messageTime);
                messageList.add(messageApp);
            } else if (message.getSenderUsername().equals(respondentUsername) && message.getReceiverUsername().equals(userId)) {
                String sender = userFullname;
                String messageContent = message.getMessage();
                String messageTime = message.getDate();
                Message messageApp = new Message(sender, messageContent, messageTime);
                messageList.add(messageApp);
            }
        }
        messageCallback.messageCallback();
    }

    public void addOutgoingMessage(String receiver, String messageContent) {
        String sendTime = new Date().toString();
        ntu.com.mylife.common.entity.databaseentity.Message message = new ntu.com.mylife.common.entity.databaseentity.Message(receiver, userId, messageContent, sendTime);
        try {
            daoMessage.addData(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

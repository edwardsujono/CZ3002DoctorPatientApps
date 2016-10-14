package ntu.com.mylife.controller;

import android.content.Context;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ntu.com.mylife.common.entity.applicationentity.Message;
import ntu.com.mylife.common.entity.databaseentity.User;
import ntu.com.mylife.common.entity.databaseentity.UserType;
import ntu.com.mylife.common.service.AlertDialogService;
import ntu.com.mylife.common.service.MessageDao;
import ntu.com.mylife.common.service.MessageDaoImpl;
import ntu.com.mylife.common.service.SharedPreferencesKey;
import ntu.com.mylife.common.service.UserDao;
import ntu.com.mylife.common.service.UserDaoImpl;
import ntu.com.mylife.common.service.SharedPreferencesService;

/**
 * Created by martinus on 24/9/16.
 */
public class MessageController {

    private MessageDao daoMessage;
    private UserDao daoUser;
    private AlertDialogService alertDialog;
    private MessageCallback messageCallback;
    private Context context;
    private SharedPreferencesService sharedPreferencesService;
    private String userId;
    private String userType;
    private String respondentUserId;
    private String respondentFullname;
    private String userFullname;

    private List<Message> messageList;

    public MessageController (String respondentUserId, ArrayList<Message> messageList, Context context, MessageCallback callback) {
        this.respondentUserId = respondentUserId;
        this.messageList = messageList;
        this.context = context;
        this.messageCallback = callback;
        this.daoMessage = new MessageDaoImpl();
        this.daoUser = new UserDaoImpl();
        sharedPreferencesService = new SharedPreferencesService(context);
        userId = sharedPreferencesService.getDataFromSharedPreferences(SharedPreferencesKey.NAME_SHARED_PREFERENCES, SharedPreferencesKey.KEY_USER);
        userType = sharedPreferencesService.getDataFromSharedPreferences(SharedPreferencesKey.NAME_SHARED_PREFERENCES, SharedPreferencesKey.KEY_USERTYPE);

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
            if (user.getFullName().equals(respondentUserId)) {
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
            if (user.getFullName().equals(userId)) {
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
            if (message.getReceiverUserId().equals(userId) && message.getSenderUserId().equals(respondentUserId)) {
                String sender = respondentFullname;
                String messageContent = message.getMessage();
                String messageTime = message.getDate();
                Message messageApp = new Message(sender, messageContent, messageTime);
                messageList.add(messageApp);
            } else if (message.getSenderUserId().equals(respondentUserId) && message.getReceiverUserId().equals(userId)) {
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

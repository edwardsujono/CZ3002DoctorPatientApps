package ntu.com.mylife.controller;

import android.content.Context;

import java.util.ArrayList;

import ntu.com.mylife.common.entity.applicationentity.Contact;
import ntu.com.mylife.common.service.ContactDao;
import ntu.com.mylife.common.service.ContactDaoImpl;
import ntu.com.mylife.common.service.BaseCallback;
import ntu.com.mylife.common.service.SharedPreferencesKey;
import ntu.com.mylife.common.service.SharedPreferencesService;

/**
 * Created by micha on 9/17/2016.
 */
public class ContactController {

    private ArrayList<Contact> contactList;
    private Context context;

    private SharedPreferencesService sharedPreferencesService;

    private String userId;
    private String userType;

    private ContactDao contactDao;

    private BaseCallback callback;

    public ContactController(ArrayList<Contact> contactList, Context context, BaseCallback callback) {
        this.contactList = contactList;
        this.context = context;
        this.callback = callback;

        sharedPreferencesService = new SharedPreferencesService(context);

        userId = sharedPreferencesService.getDataFromSharedPreferences(SharedPreferencesKey.NAME_SHARED_PREFERENCES, SharedPreferencesKey.KEY_USER);
        userType = sharedPreferencesService.getDataFromSharedPreferences(SharedPreferencesKey.NAME_SHARED_PREFERENCES, SharedPreferencesKey.KEY_USERTYPE);

        contactDao = new ContactDaoImpl(userType, callback);
    }

    public void fetchContact() {
        contactDao.findData();
    }


}

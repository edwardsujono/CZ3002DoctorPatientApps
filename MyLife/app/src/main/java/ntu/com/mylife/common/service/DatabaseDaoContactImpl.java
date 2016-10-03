package ntu.com.mylife.common.service;

import android.util.Log;

import com.firebase.client.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

import ntu.com.mylife.common.entity.applicationentity.Contact;
import ntu.com.mylife.common.entity.databaseentity.UserType;

/**
 * Created by micha on 9/17/2016.
 */


/*FireBase data will be saved in form of JSON file

   Structure data please defined here:
   Patient/Doctor data dtructure
 {"patient" :  {
                    "name" : "Edward Sujono"
                    "email" : "wowdogs"
                    "password" : "hahaha"
                    "age" : 17
               }
 }

 Extracted into Contact model

* */


public class DatabaseDaoContactImpl implements DatabaseDaoContact {

    private DatabaseReference databaseReference;
    private DatabaseReference contactDatabaseReference;
    private HashMap hashMapSaved;
    private static String DOCTOR = "doctors";
    private static String PATIENT = "patients";
    private static String FULLNAME = "fullName";
    private static String USERNAME = "userName";
    private String stringType;

    private MyCallback callback;

    public DatabaseDaoContactImpl(String type, MyCallback callback) {
        this.callback = callback;
        databaseReference = FirebaseDatabase.getInstance().getReference();

        if(type.equals(UserType.Type.DOCTOR+""))
            stringType = PATIENT;
        else
            stringType = DOCTOR;

        Log.e("stringType", type + " " + stringType);

        contactDatabaseReference = databaseReference.child(stringType);

    }

    @Override
    public void findData() {


        Log.e("DatabaseDaoContact", contactDatabaseReference.toString());

        contactDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                hashMapSaved = (HashMap) dataSnapshot.getValue();
                Log.e("Inside", String.valueOf(dataSnapshot.toString()));
                fromHashMapToArrayList();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("DatabaseDaoContactImpl", databaseError.getMessage());
            }
        });
    }

    private void fromHashMapToArrayList() {

        if(hashMapSaved == null) {
            Log.e("findData", "failed");
            return;
        }

        final ArrayList<Contact> contactList = new ArrayList<>();

        for(Object attr: hashMapSaved.values()) {

            HashMap hashMapAttr = (HashMap) attr;

            String contactName = (String)hashMapAttr.get(USERNAME);
            String imageName = "";

            Contact contact = new Contact(contactName, null);

            contactList.add(contact);
        }

        callback.callbackFunction(contactList);

    }
}

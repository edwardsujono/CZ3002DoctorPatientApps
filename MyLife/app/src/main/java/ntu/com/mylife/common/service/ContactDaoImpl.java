package ntu.com.mylife.common.service;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

import ntu.com.mylife.common.entity.applicationentity.Contact;
import ntu.com.mylife.common.entity.databaseentity.DatabaseConfiguration;
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


public class ContactDaoImpl implements ContactDao {

    private DatabaseReference databaseReference;
    private DatabaseReference contactDatabaseReference;
    private HashMap hashMapSaved;
    private String userTypeString;

    private BaseCallback callback;

    public ContactDaoImpl(String type, BaseCallback callback) {
        this.callback = callback;
        databaseReference = FirebaseDatabase.getInstance().getReference();

        if(type.equals(UserType.Type.DOCTOR+""))
            userTypeString = DatabaseConfiguration.PATIENTS;
        else
            userTypeString = DatabaseConfiguration.DOCTORS;

        Log.e("stringType", type + " " + userTypeString);

        contactDatabaseReference = databaseReference.child(userTypeString);

    }

    @Override
    public void findData() {


        Log.e("ContactDao", contactDatabaseReference.toString());

        contactDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                hashMapSaved = (HashMap) dataSnapshot.getValue();
                Log.e("Inside", String.valueOf(dataSnapshot.toString()));
                fromHashMapToArrayList();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("ContactDaoImpl", databaseError.getMessage());
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

            String contactName = (String)hashMapAttr.get(DatabaseConfiguration.USER_USERID);
            //Temporary
            String imageName = "";

            Contact contact = new Contact(contactName, null);

            contactList.add(contact);
        }

        callback.callbackFunction(contactList);

    }
}

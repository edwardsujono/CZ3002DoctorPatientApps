package ntu.com.mylife.common.service;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

import ntu.com.mylife.common.entity.applicationentity.Contact;
import ntu.com.mylife.common.entity.databaseentity.UserType;

/**
 * Created by micha on 9/17/2016.
 */
public class DatabaseDaoContactImpl implements DatabaseDaoContact {

    private DatabaseReference databaseReference;
    private DatabaseReference contactDatabaseReference;
    private HashMap hashMapSaved;
    private static String DOCTOR = "doctor";
    private static String PATIENT = "patient";

    public DatabaseDaoContactImpl(String type) {
        databaseReference = FirebaseDatabase.getInstance().getReference();

        String stringType;
        if(type.equals(DOCTOR))
            stringType = PATIENT;
        else
            stringType = DOCTOR;

        contactDatabaseReference = databaseReference.child(stringType);

        contactDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                hashMapSaved = (HashMap) dataSnapshot.getValue();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("DatabaseDaoContactImpl", databaseError.getMessage());
            }
        });

    }

    @Override
    public Object findData() {
        if(hashMapSaved == null)
            return null;

        final ArrayList<Contact> contactList = new ArrayList<>();

        for(Object key: hashMapSaved.keySet()) {
            String contactName = (String) hashMapSaved.get(key);
            String imageName = "";

            Contact contact = new Contact(contactName, null);

            contactList.add(contact);
        }

        return contactList;
    }
}

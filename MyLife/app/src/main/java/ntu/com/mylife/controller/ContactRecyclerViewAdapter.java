package ntu.com.mylife.controller;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import ntu.com.mylife.view.ContactOptionsView;
import ntu.com.mylife.R;
import ntu.com.mylife.common.entity.applicationentity.Contact;
import ntu.com.mylife.common.entity.applicationentity.SharedPreferencesKey;
import ntu.com.mylife.common.service.SharedPreferencesService;

/**
 * Created by micha on 9/17/2016.
 */
public class ContactRecyclerViewAdapter extends RecyclerView.Adapter<ContactRecyclerViewAdapter.ViewHolder> {
    private ArrayList<Contact> contactList;
    private Context context;
    private Activity myActivity;
    private SharedPreferencesService sharedPreferencesService;

    public ContactRecyclerViewAdapter(ArrayList<Contact> chatList, Context context,Activity activity) {
        this.contactList = chatList;
        this.context = context;
        this.myActivity = activity;
        sharedPreferencesService = new SharedPreferencesService(myActivity.getBaseContext());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Contact contact = contactList.get(position);
        if(contact.getContactBitmap()!=null)
            holder.contactImage.setImageBitmap(contact.getContactBitmap());
        holder.contactName.setText(contact.getContactName());
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{

        private CircleImageView contactImage;
        private TextView contactName;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setClickable(true);
            itemView.setOnClickListener(this);
            contactImage = (CircleImageView) itemView.findViewById(R.id.contact_image);
            contactName = (TextView) itemView.findViewById(R.id.contact_name);
        }

        @Override
        public void onClick(View v) {
            Log.i("position",getPosition()+"");
            final FragmentTransaction ft =myActivity.getFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_transition_main_page, new ContactOptionsView());
            sharedPreferencesService.saveToSharedPreferences(SharedPreferencesKey.NAME_SHARED_PREFERENCES,SharedPreferencesKey.CURRENT_CLICK_CONTACT,contactName.getText().toString());
            ft.commit();
        }
    }

}

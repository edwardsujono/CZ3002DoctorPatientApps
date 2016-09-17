package ntu.com.mylife.controller;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import ntu.com.mylife.R;
import ntu.com.mylife.common.entity.applicationentity.Chat;
import ntu.com.mylife.common.entity.applicationentity.Contact;
import ntu.com.mylife.common.entity.databaseentity.User;

/**
 * Created by micha on 9/17/2016.
 */
public class ContactRecyclerViewAdapter extends RecyclerView.Adapter<ContactRecyclerViewAdapter.ViewHolder> {
    private ArrayList<Contact> contactList;
    private Context context;

    public ContactRecyclerViewAdapter(ArrayList<Contact> chatList, Context context) {
        this.contactList = chatList;
        this.context = context;
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


    public class ViewHolder extends RecyclerView.ViewHolder {

        private CircleImageView contactImage;
        private TextView contactName;

        public ViewHolder(View itemView) {
            super(itemView);

            contactImage = (CircleImageView) itemView.findViewById(R.id.contact_image);
            contactName = (TextView) itemView.findViewById(R.id.contact_name);
        }

    }

}

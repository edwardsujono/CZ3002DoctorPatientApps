package ntu.com.mylife.controller;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import ntu.com.mylife.R;
import ntu.com.mylife.common.data.Message;

/**
 * Created by MARTINUS on 07-Sep-16.
 */
public class MessageRecyclerViewAdapter extends RecyclerView.Adapter<MessageRecyclerViewAdapter.ViewHolder> {

    private ArrayList<Message> messageList;

    public MessageRecyclerViewAdapter(ArrayList<Message> messageList) {
        this.messageList = messageList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.message,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Message message = messageList.get(position);
        holder.id.setText(message.getId());
        holder.name.setText(message.getName());
        holder.text.setText(message.getText());
        holder.photoUrl.setText(message.getPhotoUrl());
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView id;
        private TextView text;
        private TextView name;
        private TextView photoUrl;

        public ViewHolder(View itemView) {
            super(itemView);
            id = (TextView) itemView.findViewById(R.id.id_text_view);
            text = (TextView) itemView.findViewById(R.id.text_text_view);
            name = (TextView) itemView.findViewById(R.id.name_text_view);
            photoUrl = (TextView) itemView.findViewById(R.id.photo_url_text_view);
        }

    }

}

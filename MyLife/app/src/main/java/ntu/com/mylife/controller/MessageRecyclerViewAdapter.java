package ntu.com.mylife.controller;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import ntu.com.mylife.R;
import ntu.com.mylife.common.entity.applicationentity.Message;

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
        holder.respondentName.setText(message.getSenderUsername());
        holder.message.setText(message.getMessage());
        holder.date.setText(message.getDate());
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView respondentName;
        private TextView message;
        private TextView date;

        public ViewHolder(View itemView) {
            super(itemView);
            respondentName = (TextView) itemView.findViewById(R.id.respondentTextView);
            message = (TextView) itemView.findViewById(R.id.messageRespondentTextView);
            date = (TextView) itemView.findViewById(R.id.timeTextView);
        }

    }

}

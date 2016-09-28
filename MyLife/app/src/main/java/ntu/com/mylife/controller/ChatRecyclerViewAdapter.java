package ntu.com.mylife.controller;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import ntu.com.mylife.R;
import ntu.com.mylife.common.entity.applicationentity.Chat;
import ntu.com.mylife.view.MessageView;

/**
 * Created by MARTINUS on 16-Sep-16.
 */
public class ChatRecyclerViewAdapter extends RecyclerView.Adapter<ChatRecyclerViewAdapter.ViewHolder> {

    private ArrayList<Chat> chatList;
    private Context context;

    public ChatRecyclerViewAdapter(ArrayList<Chat> chatList, Context context) {
        this.chatList = chatList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public int getItemCount() {
        return chatList.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Chat chat = chatList.get(position);
        holder.respondentImage.setImageBitmap(chat.getRespondentBitmap());
        holder.respondentName.setText(chat.getRespondentName());
        holder.lastMessage.setText(chat.getLatestMessage());
        holder.lastMessageTime.setText(chat.getLatestMessageTime());
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView respondentImage;
        private TextView respondentName;
        private TextView lastMessage;
        private TextView lastMessageTime;

        public ViewHolder(View itemView) {
            super(itemView);

            respondentImage = (ImageView) itemView.findViewById(R.id.respondent_image);
            respondentName = (TextView) itemView.findViewById(R.id.respondent_name);
            lastMessage = (TextView) itemView.findViewById(R.id.latest_message);
            lastMessageTime = (TextView) itemView.findViewById(R.id.latest_message_time);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getLayoutPosition();
            Intent intent = new Intent(context, MessageView.class);
            intent.putExtra("respondentUsername", chatList.get(position).getRespondentId());
            context.startActivity(intent);

        }

    }

}

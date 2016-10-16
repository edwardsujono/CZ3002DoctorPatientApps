package ntu.com.mylife.controller;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import ntu.com.mylife.R;

/**
 * Created by LENOVO on 07/09/2016.
 */
public class CurrentScheduleRecyclerViewAdapter extends RecyclerView.Adapter<CurrentScheduleRecyclerViewAdapter.ViewHolder>{

    private ArrayList<String> timeList;
    private ArrayList<String> messageList;


    public CurrentScheduleRecyclerViewAdapter(ArrayList<String> listTime, ArrayList<String> listMessage){
        this.messageList = listMessage;
        this.timeList  = listTime;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.current_schedule_row,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String timeStamp = timeList.get(position);
        String message = messageList.get(position);
        holder.currentMessage.setTextColor(Color.parseColor("#009688"));
        holder.currentMessage.setText(message);
        holder.currentTime.setTextColor(Color.parseColor("#009688"));
        holder.currentTime.setText(timeStamp);
        holder.currentMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("click schedule","true");
            }
        });
    }

    @Override
    public int getItemCount() {
        return timeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView currentTime;
        private TextView currentMessage;


        public ViewHolder(View itemView) {
            super(itemView);
            currentTime = (TextView) itemView.findViewById(R.id.current_time_schedule);
            currentMessage = (TextView) itemView.findViewById(R.id.current_message_schedule);
        }
    }

}
package ntu.com.mylife.controller;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import ntu.com.mylife.R;

/**
 * Created by LENOVO on 07/09/2016.
 */
public class CurrentScheduleRecyclerViewAdaptor extends RecyclerView.Adapter<CurrentScheduleRecyclerViewAdaptor.ViewHolder>{

    private ArrayList<String> listTime;
    private ArrayList<String> listMessage;


    public CurrentScheduleRecyclerViewAdaptor(ArrayList<String> listTime,ArrayList<String> listMessage){
        this.listMessage = listMessage;
        this.listTime  = listTime;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.current_schedule_row,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String timeStamp = listTime.get(position);
        String message = listMessage.get(position);
        holder.currentMessage.setText(message);
        holder.currentTime.setText(timeStamp);
    }

    @Override
    public int getItemCount() {
        return listTime.size();
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

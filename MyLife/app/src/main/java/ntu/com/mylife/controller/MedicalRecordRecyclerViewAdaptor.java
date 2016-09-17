package ntu.com.mylife.controller;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ntu.com.mylife.R;

/**
 * Created by LENOVO on 17/09/2016.
 */
public class MedicalRecordRecyclerViewAdaptor extends RecyclerView.Adapter<MedicalRecordRecyclerViewAdaptor.ViewHolder>{


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.medical_record_row,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView medicalRecordTme,medicalRecordDescription,medicalRecordFromDoctor;

        public ViewHolder(View itemView) {
            super(itemView);
            medicalRecordTme = (TextView) itemView.findViewById(R.id.medical_record_time);
            medicalRecordDescription  = (TextView) itemView.findViewById(R.id.medical_record_time);
            medicalRecordFromDoctor = (TextView) itemView.findViewById(R.id.medical_record_from_doctor);
        }
    }

}

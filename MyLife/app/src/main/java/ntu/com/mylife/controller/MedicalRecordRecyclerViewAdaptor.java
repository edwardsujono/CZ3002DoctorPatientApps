package ntu.com.mylife.controller;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import ntu.com.mylife.R;
import ntu.com.mylife.common.entity.databaseentity.MedicalRecord;

/**
 * Created by LENOVO on 17/09/2016.
 */
public class MedicalRecordRecyclerViewAdaptor extends RecyclerView.Adapter<MedicalRecordRecyclerViewAdaptor.ViewHolder>{

    private ArrayList<MedicalRecord> listMedicalRecord;


    public MedicalRecordRecyclerViewAdaptor(ArrayList<MedicalRecord> listMedicalRecord){
        this.listMedicalRecord = listMedicalRecord;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.medical_record_row,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MedicalRecord medicalRecord = listMedicalRecord.get(position);
        holder.medicalRecordDescription.setTextColor(Color.parseColor("#009688"));
        holder.medicalRecordTme.setTextColor(Color.parseColor("#009688"));
        holder.medicalRecordFromDoctor.setTextColor(Color.parseColor("#009688"));

        holder.medicalRecordTme.setText(medicalRecord.getTime());
        holder.medicalRecordDescription.setText(medicalRecord.getMedicalRecordDescription());
        holder.medicalRecordFromDoctor.setText(medicalRecord.getFromDoctor());

    }

    @Override
    public int getItemCount() {
        return listMedicalRecord.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView medicalRecordTme,medicalRecordDescription,medicalRecordFromDoctor;

        public ViewHolder(View itemView) {
            super(itemView);
            medicalRecordTme = (TextView) itemView.findViewById(R.id.medical_record_time);
            medicalRecordDescription  = (TextView) itemView.findViewById(R.id.medical_record_time);
            medicalRecordFromDoctor = (TextView) itemView.findViewById(R.id.medical_record_from_doctor);
        }
    }

}

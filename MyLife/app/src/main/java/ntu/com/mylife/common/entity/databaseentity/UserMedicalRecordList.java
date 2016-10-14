package ntu.com.mylife.common.entity.databaseentity;

import java.util.ArrayList;

/**
 * Created by LENOVO on 23/09/2016.
 */
public class UserMedicalRecordList {

    private String userId;
    private ArrayList<MedicalRecord> medicalRecordList;

    public UserMedicalRecordList(String userId, ArrayList<MedicalRecord> medicalRecordList) {
        this.userId = userId;
        this.medicalRecordList = medicalRecordList;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public ArrayList<MedicalRecord> getMedicalRecordList() {
        return medicalRecordList;
    }

    public void setMedicalRecordList(ArrayList<MedicalRecord> medicalRecordList) {
        this.medicalRecordList = medicalRecordList;
    }
}

package ntu.com.mylife.common.entity.databaseentity;

import java.util.ArrayList;

/**
 * Created by LENOVO on 23/09/2016.
 */
public class ListMedicalRecordUser {

    private String userName;
    private ArrayList<MedicalRecord> listmedicalRecord;

    public ListMedicalRecordUser(String useerName,ArrayList<MedicalRecord> listmedicalRecord){
        this.userName = useerName;
        this.listmedicalRecord = listmedicalRecord;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public ArrayList<MedicalRecord> getListmedicalRecord() {
        return listmedicalRecord;
    }

    public void setListmedicalRecord(ArrayList<MedicalRecord> listmedicalRecord) {
        this.listmedicalRecord = listmedicalRecord;
    }
}

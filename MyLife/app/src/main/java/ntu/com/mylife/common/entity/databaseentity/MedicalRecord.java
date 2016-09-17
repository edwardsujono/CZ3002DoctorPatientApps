package ntu.com.mylife.common.entity.databaseentity;

/**
 * Created by LENOVO on 17/09/2016.
 */
public class MedicalRecord {

    private String time;
    private String medicalRecordDescription;

    public MedicalRecord(String time,String medicalRecordDescription){
        this.time = time;
        this.medicalRecordDescription = medicalRecordDescription;
    }


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMedicalRecordDescription() {
        return medicalRecordDescription;
    }

    public void setMedicalRecordDescription(String medicalRecordDescription) {
        this.medicalRecordDescription = medicalRecordDescription;
    }
}

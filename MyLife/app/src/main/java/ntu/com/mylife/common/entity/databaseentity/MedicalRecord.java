package ntu.com.mylife.common.entity.databaseentity;

/**
 * Created by LENOVO on 17/09/2016.
 */
public class MedicalRecord {

    private String time;
    private String medicalRecordDescription;
    private String doctorId;

    public MedicalRecord(String time,String medicalRecordDescription,String doctorId){
        this.time = time;
        this.medicalRecordDescription = medicalRecordDescription;
        this.doctorId = doctorId;
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

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }
}

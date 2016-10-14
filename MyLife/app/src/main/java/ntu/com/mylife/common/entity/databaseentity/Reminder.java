package ntu.com.mylife.common.entity.databaseentity;

import java.sql.Date;

/**
 * Created by LENOVO on 28/09/2016.
 */
public class Reminder {

    private Date date;
    private String notification;
    private String time;
    private String doctorId;
    private String patientId;

    public Reminder(Date date, String notification, String time, String doctorId, String patientId) {
        this.date = date;
        this.notification = notification;
        this.time = time;
        this.doctorId = doctorId;
        this.patientId = patientId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }
}

package ntu.com.mylife.common.entity.databaseentity;

import java.sql.Date;

/**
 * Created by LENOVO on 28/09/2016.
 */
public class Reminder {

    private Date date;
    private String notification;
    private String time;
    private String doctor;
    private String patient;


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

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }
}

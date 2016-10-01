package ntu.com.mylife.common.entity.databaseentity;

import java.util.ArrayList;

/**
 * Created by LENOVO on 03/09/2016.
 */
public class DaySchedule {

    /* JSON Data Structure
    Under UserSchedule on FireBase

{
	"day" : "12-30-2016",
	"time" : "12:20"
	"user" : "edward"
	"description" : "Hey Edward This is the time for us to sleep"
}

  */

    //inside DaySchedule have time Schedule

    private String date,time,userName,description;
    private long futureTimeMillis;

    public DaySchedule(String date,String time,String userName,String description,long futureTimeMillis){
        this.date = date;
        this.time = time;
        this.userName = userName;
        this.description = description;
        this.futureTimeMillis = futureTimeMillis;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getFutureTimeMillis() {
        return futureTimeMillis;
    }

    public void setFutureTimeMillis(long futureTimeMillis) {
        this.futureTimeMillis = futureTimeMillis;
    }
}

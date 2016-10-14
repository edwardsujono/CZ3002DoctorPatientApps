package ntu.com.mylife.common.entity.databaseentity;

import java.util.ArrayList;

/**
 * Created by LENOVO on 03/09/2016.
 */
public class DaySchedule extends Schedule {

    /* JSON Data Structure
    Under UserSchedule on FireBase

{
	"day" : "12-30-2016",
	"time" : "12:20"
	"user" : "edward"
	"description" : "Hey Edward This is the time for us to sleep"
}

  */

    //NOT USED

    //inside DaySchedule have time Schedule

    private String date;
    private String time;
    private String userId;
    private String description;
    private long futureTimeMillis;

    public DaySchedule(String date,String time,String userId,String description,long futureTimeMillis){
        this.date = date;
        this.time = time;
        this.userId = userId;
        this.description = description;
        this.futureTimeMillis = futureTimeMillis;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getFutureTimeMillis() {
        return futureTimeMillis;
    }

    public void setFutureTimeMillis(long futureTimeMillis) {
        this.futureTimeMillis = futureTimeMillis;
    }
}

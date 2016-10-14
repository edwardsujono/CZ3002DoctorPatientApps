package ntu.com.mylife.common.entity.databaseentity;

import java.util.ArrayList;

/**
 * Created by LENOVO on 03/09/2016.
 */

/* JSON Data Structure
    Under UserSchedule on FireBase

    {"userName": "edward454",
     "schedule" : [
        {"day" : "14-December-2016"
         "timeSchedule" : [
            {"time" : "12:30" , "message" : "take your medicine"}
         ]
        }
     ]}

* */


public class UserSchedule extends Schedule {
    private String userId;
    private ArrayList<DaySchedule> dayScheduleList;

    public UserSchedule() {

    }

    public UserSchedule(String userId, ArrayList<DaySchedule> dayScheduleList) {
        this.userId = userId;
        this.dayScheduleList = dayScheduleList;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public ArrayList<DaySchedule> getDayScheduleList() {
        return dayScheduleList;
    }

    public void setDayScheduleList(ArrayList<DaySchedule> dayScheduleList) {
        this.dayScheduleList = dayScheduleList;
    }
}

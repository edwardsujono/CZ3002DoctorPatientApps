package ntu.com.mylife.common.data;

import java.util.ArrayList;

/**
 * Created by LENOVO on 03/09/2016.
 */
public class DaySchedule {

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

  */

    //inside DaySchedule have time Schedule

    private ArrayList<TimeSchedule> timeSchedules;


    public ArrayList<TimeSchedule> getTimeSchedules() {
        return timeSchedules;
    }

    public void setTimeSchedules(ArrayList<TimeSchedule> timeSchedules) {
        this.timeSchedules = timeSchedules;
    }




}

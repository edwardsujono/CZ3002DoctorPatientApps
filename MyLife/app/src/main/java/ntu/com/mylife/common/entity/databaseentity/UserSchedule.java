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


public class UserSchedule {
    private String userName;
    private ArrayList<DaySchedule> listDaySchedule;


    public ArrayList<DaySchedule> getListDaySchedule() {
        return listDaySchedule;
    }

    public void setListDaySchedule(ArrayList<DaySchedule> listDaySchedule) {
        this.listDaySchedule = listDaySchedule;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


}

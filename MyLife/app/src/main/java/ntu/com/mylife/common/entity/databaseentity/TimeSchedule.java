package ntu.com.mylife.common.entity.databaseentity;

/**
 * Created by LENOVO on 03/09/2016.
 */
public class TimeSchedule extends Schedule {

    private String message;
    private String time;

    public TimeSchedule(String message,String time){
        this.message = message;
        this.time = time;
    }


}

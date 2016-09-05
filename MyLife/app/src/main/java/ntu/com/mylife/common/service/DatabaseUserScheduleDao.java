package ntu.com.mylife.common.service;

/**
 * Created by LENOVO on 05/09/2016.
 */
public interface DatabaseUserScheduleDao {

    public void addData();
    public Object searchData(String userName);
    public void deleteData(String userName);

}

package ntu.com.mylife.common.service;

/**
 * Created by LENOVO on 05/09/2016.
 */
public interface DatabaseUserScheduleDao {

    public void addData(Object object) throws Exception;
    public Object searchData(String userName,String dayInserted) throws Exception;
    public void deleteData(String userName) throws Exception;

}

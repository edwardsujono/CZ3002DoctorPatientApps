package ntu.com.mylife.common.service;

import java.util.ArrayList;

import ntu.com.mylife.common.data.UserType;

/**
 * Created by LENOVO on 01/09/2016.
 */
public interface DatabaseDao {
    //please be careful when using FireBase, their API is really unique that
    //eventListener just executed once at the start time and when there has data changes
    //so always put EventListener constructor level, and cache it to HashMap variable(since FireBase records save in JSON format)
    //refer to DatabaseDaoUserImpl

    public void addData(UserType.Type type, Object object);
    public void deleteData(UserType.Type type, Object object);
    public Object findData(UserType.Type type);

}

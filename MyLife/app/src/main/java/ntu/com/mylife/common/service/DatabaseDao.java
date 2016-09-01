package ntu.com.mylife.common.service;

import java.util.ArrayList;

import ntu.com.mylife.common.data.UserType;

/**
 * Created by LENOVO on 01/09/2016.
 */
public interface DatabaseDao {

    public void addData(UserType.Type type, Object object);
    public void deleteData(UserType.Type type, Object object);
    public Object findData(UserType.Type type);

}

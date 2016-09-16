package ntu.com.mylife.common.service;

import ntu.com.mylife.common.data.UserType;

/**
 * Created by MARTINUS on 07-Sep-16.
 */
public interface DatabaseDaoMessage {

    public void addData(Object object) throws Exception;
    public void deleteData(Object object) throws Exception;
    public Object findData() throws Exception;

}

package ntu.com.mylife.common.service;

/**
 * Created by MARTINUS on 17-Sep-16.
 */
public interface DatabaseDaoChat {

    public void addData(Object object) throws Exception;
    public void deleteData(Object object) throws Exception;
    public Object findData() throws Exception;

}

package ntu.com.mylife.common.service;

/**
 * Created by MARTINUS on 17-Sep-16.
 */
public interface ChatDao {

    public void addData(Object object) throws Exception;
    public void deleteData(Object object) throws Exception;
    public void findData() throws Exception;

}

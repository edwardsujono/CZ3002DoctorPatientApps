package ntu.com.mylife.common.service;

/**
 * Created by LENOVO on 17/09/2016.
 */
public interface DatabaseDaoMedicalRecord {

    public Object getRecord(Object object);
    public void addNewMedicalRecord(Object object,String userName);
    public void deleteMedicalRecord(Object object);
}

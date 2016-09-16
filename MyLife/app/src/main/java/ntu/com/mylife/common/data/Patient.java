package ntu.com.mylife.common.data;

import java.util.Date;

/**
 * Created by LENOVO on 01/09/2016.
 */
public class Patient extends User {
    private int age;
    private String bloodType,allergy,medicalCondition;
    private Date dob;

    public Patient(String fullName, String userName, String email, String password) {
        super(fullName, userName, email, password);
    }

    public Patient(String fullName,String userName,String email,String password,int age,String bloodType,String allergy,String medicalCondition,Date dob){
        super(fullName, userName, email, password);
        this.age = age;
        this.dob = dob;
        this.bloodType = bloodType;
        this.allergy = allergy;
        this.medicalCondition = medicalCondition;
        this.dob = dob;
    }



    public void setAge(int age) {
        this.age = age;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public void setAllergy(String allergy) {
        this.allergy = allergy;
    }

    public void setMedicalCondition(String medicalCondition) {
        this.medicalCondition = medicalCondition;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }


    public Date getDob() {
        return dob;
    }

    public String getMedicalCondition() {
        return medicalCondition;
    }

    public String getAllergy() {
        return allergy;
    }

    public String getBloodType() {
        return bloodType;
    }

    public int getAge() {
        return age;
    }
}

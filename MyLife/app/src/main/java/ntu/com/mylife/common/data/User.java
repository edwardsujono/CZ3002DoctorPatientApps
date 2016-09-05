package ntu.com.mylife.common.data;

/**
 * Created by LENOVO on 01/09/2016.
 */
public class User {

    private String fullName;
    private String userName;
    private String email;
    private String password;

    public User(String fullName,String userName,String email,String password){
        this.fullName = fullName;
        this.userName = userName;
        this.email = email;
        this.password = password;
    }


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



}

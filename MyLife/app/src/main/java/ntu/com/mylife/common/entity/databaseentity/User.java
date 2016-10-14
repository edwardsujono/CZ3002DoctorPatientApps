package ntu.com.mylife.common.entity.databaseentity;

/**
 * Created by LENOVO on 01/09/2016.
 */
public class User {

    private String fullName;
    private String userId;
    private String email;
    private String password;
    private String image;

    public User(String fullName, String userId, String email, String password, String image) {
        this.fullName = fullName;
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.image = image;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

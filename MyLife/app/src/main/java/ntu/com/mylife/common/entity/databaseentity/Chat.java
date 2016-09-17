package ntu.com.mylife.common.entity.databaseentity;

/**
 * Created by MARTINUS on 17-Sep-16.
 */
public class Chat {

    private String username1;
    private String username2;
    private String latestMessage;
    private String latestMessageTime;

    public Chat(String username1, String username2, String latestMessage, String latestMessageTime) {
        this.username1 = username1;
        this.username2 = username2;
        this.latestMessage = latestMessage;
        this.latestMessageTime = latestMessageTime;
    }

    public void setUsername1(String username) {
        this.username1 = username;
    }

    public void setUsername2(String username2) {
        this.username2 = username2;
    }

    public void setLatestMessage (String message) {
        this.latestMessage = message;
    }

    public void setLatestMessageTime(String time) {
        this.latestMessageTime = time;
    }

    public String getUsername1() {
        return username1;
    }

    public String getUsername2() {
        return username2;
    }

    public String getLatestMessage() {
        return latestMessage;
    }

    public String getLatestMessageTime() {
        return latestMessageTime;
    }
}

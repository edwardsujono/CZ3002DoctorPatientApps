package ntu.com.mylife.common.entity.applicationentity;

/**
 * Created by MARTINUS on 17-Sep-16.
 */
public class Message {

    private String senderName;
    private String message;
    private String date;

    public Message(String sender, String message, String date) {
        this.senderName = sender;
        this.message = message;
        this.date = date;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

package ntu.com.mylife.common.data;

/**
 * Created by MARTINUS on 07-Sep-16.
 */
public class Message {

    private String senderUsername;
    private String receiverUsername;
    private String message;
    private String date;

    public Message(String receiverUsername, String senderUsername, String message, String date) {
        this.senderUsername = senderUsername;
        this.receiverUsername = receiverUsername;
        this.message = message;
        this.date = date;
    }

    public String getSenderUsername() {
        return senderUsername;
    }

    public String getReceiverUsername() {
        return receiverUsername;
    }

    public String getMessage() {
        return message;
    }

    public String getDate() {
        return date;
    }

    public void setSenderUsername(String username) {
        this.senderUsername = username;
    }

    public void setReceiverUsername(String username) {
        this.receiverUsername = username;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDate(String date) {
        this.date = date;
    }






}
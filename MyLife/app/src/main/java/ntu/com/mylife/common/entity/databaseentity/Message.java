package ntu.com.mylife.common.entity.databaseentity;

/**
 * Created by MARTINUS on 07-Sep-16.
 */
public class Message {

    private String senderUserId;
    private String receiverUserId;
    private String message;
    private String date;

    public Message() {

    }

    public Message(String receiverUsername, String senderUserId, String message, String date) {
        this.senderUserId = senderUserId;
        this.receiverUserId = receiverUsername;
        this.message = message;
        this.date = date;
    }

    public String getSenderUserId() {
        return senderUserId;
    }

    public void setSenderUserId(String senderUserId) {
        this.senderUserId = senderUserId;
    }

    public String getReceiverUserId() {
        return receiverUserId;
    }

    public void setReceiverUserId(String receiverUserId) {
        this.receiverUserId = receiverUserId;
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

package ntu.com.mylife.common.data;

/**
 * Created by MARTINUS on 07-Sep-16.
 */
public class Message {

    private String receiverId;
    private String senderId;
    private String message;
    private String date;

    public Message(String receiverId, String senderId, String message, String date) {
        this.receiverId = receiverId;
        this.senderId = senderId;
        this.message = message;
        this.date = date;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getMessage() {
        return message;
    }

    public String getDate() {
        return date;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDate(String date) {
        this.date = date;
    }






}

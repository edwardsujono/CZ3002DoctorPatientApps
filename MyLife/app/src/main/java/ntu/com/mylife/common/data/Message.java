package ntu.com.mylife.common.data;

/**
 * Created by MARTINUS on 07-Sep-16.
 */
public class Message {

    private String respondentName;
    private String message;
    private String date;

    public Message(String respondentName, String message, String date) {
        this.respondentName = respondentName;
        this.message = message;
        this.date = date;
    }

    public String getRespondentName() {
        return respondentName;
    }

    public String getMessage() {
        return message;
    }

    public String getDate() {
        return date;
    }

    public void setRespondentName(String name) {
        this.respondentName = name;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDate(String date) {
        this.date = date;
    }






}

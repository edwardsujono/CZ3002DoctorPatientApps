package ntu.com.mylife.common.entity.applicationentity;

/**
 * Created by MARTINUS on 17-Sep-16.
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

    public void setRespondentName(String respondentName) {
        this.respondentName = respondentName;
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

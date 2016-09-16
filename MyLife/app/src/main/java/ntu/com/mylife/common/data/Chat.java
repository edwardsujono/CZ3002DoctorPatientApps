package ntu.com.mylife.common.data;

import android.graphics.Bitmap;

/**
 * Created by MARTINUS on 17-Sep-16.
 */
public class Chat {

    private Bitmap respondentBitmap;
    private String respondentName;
    private String latestMessage;
    private String latestMessageTime;

    public Chat(Bitmap respondentBitmap, String respondentName, String latestMessage, String latestMessageTime) {
        this.respondentBitmap = respondentBitmap;
        this.respondentName = respondentName;
        this.latestMessage = latestMessage;
        this.latestMessageTime = latestMessageTime;
    }

    public void setRespondentBitmap(Bitmap bitmap) {
        this.respondentBitmap = bitmap;
    }

    public void setRespondentName(String name) {
        this.respondentName = name;
    }

    public void setLatestMessage (String message) {
        this.latestMessage = message;
    }

    public void setLatestMessageTime(String time) {
        this.latestMessageTime = time;
    }

    public Bitmap getRespondentBitmap() {
        return respondentBitmap;
    }

    public String getRespondentName() {
        return respondentName;
    }

    public String getLatestMessage() {
        return latestMessage;
    }

    public String getLatestMessageTime() {
        return latestMessageTime;
    }
}

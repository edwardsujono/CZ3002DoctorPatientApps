package ntu.com.mylife.common.entity.applicationentity;

import android.graphics.Bitmap;

/**
 * Created by MARTINUS on 17-Sep-16.
 */
public class Chat {

    private Bitmap respondentBitmap;
    private String respondentName;
    private String respondentId;
    private String latestMessage;
    private String latestMessageTime;

    public Chat(Bitmap respondentBitmap, String respondentName, String respondentId, String latestMessage, String latestMessageTime) {
        this.respondentBitmap = respondentBitmap;
        this.respondentName = respondentName;
        this.respondentId = respondentId;
        this.latestMessage = latestMessage;
        this.latestMessageTime = latestMessageTime;
    }

    public String getLatestMessage() {
        return latestMessage;
    }

    public void setLatestMessage(String latestMessage) {
        this.latestMessage = latestMessage;
    }

    public Bitmap getRespondentBitmap() {
        return respondentBitmap;
    }

    public void setRespondentBitmap(Bitmap respondentBitmap) {
        this.respondentBitmap = respondentBitmap;
    }

    public String getRespondentName() {
        return respondentName;
    }

    public void setRespondentName(String respondentName) {
        this.respondentName = respondentName;
    }

    public String getLatestMessageTime() {
        return latestMessageTime;
    }

    public void setLatestMessageTime(String latestMessageTime) {
        this.latestMessageTime = latestMessageTime;
    }

    public String getRespondentId() {
        return respondentId;
    }

    public void setRespondentId(String respondentId) {
        this.respondentId = respondentId;
    }
}

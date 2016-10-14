package ntu.com.mylife.common.entity.databaseentity;

/**
 * Created by MARTINUS on 17-Sep-16.
 */
public class Chat {

    private String user1Id;
    private String user2Id;
    private String latestMessage;
    private String latestMessageTime;

    public Chat(String user1Id, String user2Id, String latestMessage, String latestMessageTime) {
        this.user1Id = user1Id;
        this.user2Id = user2Id;
        this.latestMessage = latestMessage;
        this.latestMessageTime = latestMessageTime;
    }

    public String getUser1Id() {
        return user1Id;
    }

    public void setUser1Id(String user1Id) {
        this.user1Id = user1Id;
    }

    public String getUser2Id() {
        return user2Id;
    }

    public void setUser2Id(String user2Id) {
        this.user2Id = user2Id;
    }

    public String getLatestMessage() {
        return latestMessage;
    }

    public void setLatestMessage(String latestMessage) {
        this.latestMessage = latestMessage;
    }

    public String getLatestMessageTime() {
        return latestMessageTime;
    }

    public void setLatestMessageTime(String latestMessageTime) {
        this.latestMessageTime = latestMessageTime;
    }
}

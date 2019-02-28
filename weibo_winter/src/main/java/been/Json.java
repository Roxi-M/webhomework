package been;

import java.util.List;

public class Json {
    private String state;
    private String info;
    private List<Message> messageList;
    public Json(){
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }

    public List<Message> getMessageList() {
        return messageList;
    }

    public String getState() {
        return state;
    }
}

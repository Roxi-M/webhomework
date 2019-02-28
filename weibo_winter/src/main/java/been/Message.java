package been;

import java.util.List;

public class Message {
    private String info;
    private int num;
    private int father;
    private int id;
    private String who;
    private String photo;
    private int great;
    private String category;
    private String nick_name;
    private String photo_head;
    private List<Message> messageList;

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }

    public List<Message> getMessageList() {
        return messageList;
    }

    public void setGreat(int great) {
        this.great = great;
    }

    public int getFather() {
        return father;
    }

    public int getGreat() {
        return great;
    }

    public void setFather(int father) {
        this.father = father;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPhoto() {
        return photo;
    }

    public Message(){

    }
    public Message(int id,int num,String info){
        this.setId(id);
        this.setNum(num);
        this.setInfo(info);
    }
    public void setId(int id) {
        this.id = id;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public int getId() {
        return id;
    }

    public int getNum() {
        return num;
    }

    public String getWho() {
        return who;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getPhoto_head() {
        return photo_head;
    }

    public void setPhoto_head(String photo_head) {
        this.photo_head = photo_head;
    }
}

package been;

import java.util.List;

public class User {
    private String true_name;
    private String password;
    private String nick_name;
    private String account;
   private  String city;
   private  String hobby;
    private String sex;
    private int follows;
    private int attention;
    private int messagenum;
    private String Acode;
    private int id;
    private int state;
    private String head;
    private String profile;
    private int house;
    private int status;
    private List<Message> messageList;
    public void setHouse(int house) {
        this.house = house;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSex() {
        return sex;
    }

    public String getCity() {
        return city;
    }

    public String getHobby() {
        return hobby;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getProfile() {
        return profile;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }

    //account 是唯一的 用来认证用户 id 的话 是数据自己来排的 首先这里就要验证一个东西
    public User(String true_name,String account,String password){
        this.true_name=true_name;
        this.account=account;
        this.password=password;
    }
    public User(String account){
        this.account=account;
    }
    public  User(){

    }

    public void setTrue_name(String true_name) {
        this.true_name = true_name;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }


    public void setAcode(String acode) {
        Acode = acode;
    }

    public String getAcode() {
        return Acode;
    }

    public String getAccount() {
        return account;
    }

    public int getMessagenum() {
        return messagenum;
    }

    public void setMessagenum(int messagenum) {
        this.messagenum = messagenum;
    }

    public int getAttention() {
        return attention;
    }

    public void setAttention(int attention) {
        this.attention = attention;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTrue_name() {
        return true_name;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setFollows(int follows) {
        this.follows = follows;
    }

    public int getFollows() {
        return follows;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public int getHouse() {
        return house;
    }

    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

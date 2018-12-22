package team.redrock.messageBoard.been;

public class User {
    private int id;
    private String username;
    private String nickname;
    private String password;

    public String getUsername() {
        return username;
    }


    public int getId() {
        return id;
    }


    public String getNickname() {
        return nickname;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public User(){

    }
    public User(String nickname,String username,String password){
        this.nickname=nickname;
        this.username=username;
        this.password=password;
    }
}

package dao;

import been.Message;
import been.User;

import java.util.List;

public interface Userdao {
    String insert(User user);
    void update_all_user(User user);
    String update(User user);
    User select_nickname(int id);
    User select_all(User user);
    String select_acode(User user);
    int select_account(String account);
    User select_login(String account,String password);
    List select_fans(User user);
    int select_great(User user, int msnum);
    List<Integer> select_attention(User user);
    void insert_fans(User user,int id);
    void delete_fans(User user,int id);
    boolean select_solo_fans(User user,int id);
    boolean update_head(User user);
    int insert_home(User user, int msnum);
    int delete_home(User user,int msnum);
    List<Message> select_home(User user);
    int view_home(User user,int msnum);
    void update_profile(User user);
    void update_sex(User user);
    void update_hobby(User user);
    void update_city(User user);
    String select_profile(User user);
    List<User> select_user(User user);
    boolean update_nick(User user);
    int update_messagenum(User user);
}

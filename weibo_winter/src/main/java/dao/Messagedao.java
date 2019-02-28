package dao;

import been.Message;
import been.User;

import java.util.List;

public interface Messagedao {
    void insert(Message message); //发微博
    List<Message> select(User user); //搜索这个用户所有发的微博
    boolean insert_great(User user,Message message); //点赞 此用户对哪条微博的点赞
    boolean delete_great(User user,Message message); //取消点赞
    Message select_message(Message message); //搜索这条微博
    Message select_message(String info,String user_id);
    List<Message> select_list(Message message);//搜索回复
    void insert_reply(User user,Message message); //回复
    List<Message> select_list_reply(Message message);//搜索回复的回复
    List<Message> select_list_message(Message message); //Search  给出去的接口 搜索某条微博内容
    void insert_reply_reply(User user,Message message); //回复的回复
    void delete_message(User user,Message message);
    void delete_reply(Message message);
    List<Message> select_class(Message message); //搜索种类微博
    List<Message> select_alloff();
}

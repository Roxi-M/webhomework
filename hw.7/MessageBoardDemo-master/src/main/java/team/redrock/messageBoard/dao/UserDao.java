package team.redrock.messageBoard.dao;

import team.redrock.messageBoard.been.User;

public interface UserDao {
    void insert_user(User user);
    User select_user(User user);
}

package service;

import been.User;

import java.util.List;

public interface UserService {
    String tojson(User user);
    String select_user(List list);
    String select_message(User user);
}

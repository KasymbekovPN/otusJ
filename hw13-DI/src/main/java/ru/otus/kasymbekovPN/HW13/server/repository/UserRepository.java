package ru.otus.kasymbekovPN.HW13.server.repository;

import ru.otus.kasymbekovPN.HW13.db.api.model.OnlineUser;

import java.util.List;

public interface UserRepository {
    OnlineUser findByLogin(String login);
    List<OnlineUser> findAll();
    long create(String login, String password);
    boolean delete(String login);
}


//<
//import ru.otus.kasymbekovPN.HW13.server.domain.User;
//
//import java.util.List;
//
//public interface UserRepository {
//    List<User> findAll();
//    long create(String name);
//}

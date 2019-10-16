package ru.otus.kasymbekovPN.HW12.user;

import java.util.Optional;

public class UserService {

    private final UserDao dao;

    public UserService(UserDao dao) {
        this.dao = dao;
    }

    public boolean authenticate(String name, String password){
        return Optional.ofNullable(dao.findByName(name))
                .map(user -> user.getPassword().equals(password))
                .orElse(false);
    }
}

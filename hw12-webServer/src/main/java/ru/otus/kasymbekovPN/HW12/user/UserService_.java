package ru.otus.kasymbekovPN.HW12.user;

import java.util.Optional;

public class UserService_ {

    private final UserDao_ dao;

    public UserService_(UserDao_ dao) {
        this.dao = dao;
    }

    public boolean authenticate(String name, String password){
        return Optional.ofNullable(dao.findByName(name))
                .map(user -> user.getPassword().equals(password))
                .orElse(false);
    }
}

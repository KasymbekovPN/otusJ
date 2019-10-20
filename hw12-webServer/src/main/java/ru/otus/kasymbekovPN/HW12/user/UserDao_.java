package ru.otus.kasymbekovPN.HW12.user;

public interface UserDao_ {
    User_ save(User_ user);
    User_ findByName(String name);
}

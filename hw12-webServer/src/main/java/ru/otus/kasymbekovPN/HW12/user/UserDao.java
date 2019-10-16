package ru.otus.kasymbekovPN.HW12.user;

public interface UserDao {
    User save(User user);
    User findByName(String name);
}

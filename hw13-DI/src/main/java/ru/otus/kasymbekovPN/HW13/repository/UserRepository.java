package ru.otus.kasymbekovPN.HW13.repository;

import ru.otus.kasymbekovPN.HW13.domain.User;

import java.util.List;

public interface UserRepository {
    List<User> findAll();
    long create(String name);
}

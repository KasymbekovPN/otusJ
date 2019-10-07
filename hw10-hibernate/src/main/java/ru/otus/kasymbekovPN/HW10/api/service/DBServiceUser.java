package ru.otus.kasymbekovPN.HW10.api.service;

import ru.otus.kasymbekovPN.HW10.api.model.DBUser;

import java.util.Optional;

public interface DBServiceUser {
    long saveUser(DBUser DBUser);
    Optional<DBUser> getUser(long id);
}

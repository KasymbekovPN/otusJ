package ru.otus.kasymbekovPN.HW10.api.dao;

import ru.otus.kasymbekovPN.HW10.api.model.DBUser;
import ru.otus.kasymbekovPN.HW10.api.sessionManager.SessionManager;

import java.util.Optional;

public interface UserDao {
    Optional<DBUser> findById(long id);
    long saveUser(DBUser DBUser);
    SessionManager getSessionManager();
}

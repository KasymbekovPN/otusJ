package ru.otus.kasymbekovPN.HW09.api.dao;

import ru.otus.kasymbekovPN.HW09.api.sessionManager.SessionManager;

import java.util.Optional;

public interface InstanceDao<T> {
    Optional<T> loadRecord(long id);
    void createRecord(T t);
    void updateRecord(T t);
    SessionManager getSessionManager();
}

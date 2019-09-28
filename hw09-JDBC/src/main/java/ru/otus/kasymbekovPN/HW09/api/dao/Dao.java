package ru.otus.kasymbekovPN.HW09.api.dao;

import ru.otus.kasymbekovPN.HW09.api.sessionManager.SessionManager;

import java.util.Optional;

public interface Dao<T> {
    Optional<T> loadRecord(long id, T dummy);
    Optional<T> createRecord(T t);
    Optional<T> updateRecord(T t);
    SessionManager getSessionManager();
}

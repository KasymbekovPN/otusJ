package ru.otus.kasymbekovPN.HW09.api.sessionManager;

public interface SessionManager extends AutoCloseable{
    void beginSession();
    void commitSession();
    void rollbackSession();
    void close();
    DataBaseSession getCurrentSession();
}

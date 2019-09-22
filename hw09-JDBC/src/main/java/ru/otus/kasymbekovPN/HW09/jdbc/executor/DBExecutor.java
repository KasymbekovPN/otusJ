package ru.otus.kasymbekovPN.HW09.jdbc.executor;

import java.sql.Connection;

public interface DBExecutor<T> {
    void createRecord(T instance, Connection connection);
    void updateRecord(T instance, Connection connection);
    T loadRecord(long id, Class clazz, Connection connection);
}

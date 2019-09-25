package ru.otus.kasymbekovPN.HW09.jdbc.executor;

import java.sql.Connection;
import java.sql.SQLException;

public interface DBExecutor<T> {
    void createRecord(T instance, Connection connection) throws IllegalAccessException, SQLException, NoSuchFieldException;
    void updateRecord(T instance, Connection connection);
    T loadRecord(long id, Class clazz, Connection connection);
}

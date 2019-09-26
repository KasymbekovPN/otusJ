package ru.otus.kasymbekovPN.HW09.jdbc.executor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public interface DBExecutor<T> {
    void createRecord(T instance, Connection connection) throws IllegalAccessException, SQLException, NoSuchFieldException;
    void updateRecord(T instance, Connection connection);
    Optional<T> loadRecord(long id, T dummy, Connection connection) throws SQLException, IllegalAccessException, NoSuchFieldException;
}

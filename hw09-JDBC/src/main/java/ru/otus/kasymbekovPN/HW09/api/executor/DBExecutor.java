package ru.otus.kasymbekovPN.HW09.api.executor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public interface DBExecutor<T> {
    Optional<T> createRecord(T instance, Connection connection) throws IllegalAccessException, SQLException, NoSuchFieldException;
    Optional<T> updateRecord(T instance, Connection connection) throws SQLException, IllegalAccessException, NoSuchFieldException;
    Optional<T> loadRecord(long id, T dummy, Connection connection) throws SQLException, IllegalAccessException, NoSuchFieldException;
}

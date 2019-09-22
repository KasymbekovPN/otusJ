package ru.otus.kasymbekovPN.HW09.jdbc.sessionManager;

import ru.otus.kasymbekovPN.HW09.api.sessionManager.DataBaseSession;

import java.sql.Connection;

public class DataBaseSessionJDBCImpl implements DataBaseSession {

    private final Connection conn;

    public DataBaseSessionJDBCImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Connection getConnection() {
        return conn;
    }
}

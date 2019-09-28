package ru.otus.kasymbekovPN.HW09.jdbc.sessionManager;

import ru.otus.kasymbekovPN.HW09.api.sessionManager.DBSession;

import java.sql.Connection;

public class DBSessionJDBC implements DBSession {

    private final Connection conn;

    public DBSessionJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Connection getConnection() {
        return conn;
    }
}

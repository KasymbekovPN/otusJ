package ru.otus.kasymbekovPN.HW09.jdbc.sessionManager;

import ru.otus.kasymbekovPN.HW09.api.sessionManager.DBSession;

import java.sql.Connection;

/**
 * Реализация сесии работы с БД
 */
public class DBSessionJDBC implements DBSession {

    /**
     * Соединение
     */
    private final Connection conn;

    /**
     * Конструктор
     * @param conn соедение
     */
    public DBSessionJDBC(Connection conn) {
        this.conn = conn;
    }

    /**
     * @return Соединение
     */
    @Override
    public Connection getConnection() {
        return conn;
    }
}

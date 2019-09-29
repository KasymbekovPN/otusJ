package ru.otus.kasymbekovPN.HW09.api.sessionManager;

import java.sql.Connection;

/**
 * Интерфейс для реализации сессии работы с БД
 */
public interface DBSession {

    /**
     * @return Соединение
     */
    Connection getConnection();
}

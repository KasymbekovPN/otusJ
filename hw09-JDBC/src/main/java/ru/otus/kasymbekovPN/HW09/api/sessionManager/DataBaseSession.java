package ru.otus.kasymbekovPN.HW09.api.sessionManager;

import java.sql.Connection;

public interface DataBaseSession {
    Connection getConnection();
}

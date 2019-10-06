package ru.otus.kasymbekovPN.HW10.api.sessionManager;

import org.hibernate.Session;
import org.hibernate.Transaction;

public interface DataBaseSession {
    Session getSession();
    Transaction getTransaction();
    void close();
}

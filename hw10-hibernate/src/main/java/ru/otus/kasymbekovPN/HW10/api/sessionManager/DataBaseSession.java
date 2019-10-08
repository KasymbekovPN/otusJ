package ru.otus.kasymbekovPN.HW10.api.sessionManager;

import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Интерфейс для реализации сессии работы с БД
 */
public interface DataBaseSession {

    /**
     * Геттер сессии
     * @return Сессия
     */
    Session getSession();

    /**
     * Геттер транзакции
     * @return Транзакция
     */
    Transaction getTransaction();

    /**
     * Закрыть сессию
     */
    void close();
}

package ru.otus.kasymbekovPN.HW13.db.hibernate.sessionManager;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;
import ru.otus.kasymbekovPN.HW13.db.api.sessionManager.DataBaseSession;

/**
 * Реализация сессии работы с БД
 */
public class DataBaseSessionHibernate implements DataBaseSession {

    /**
     * Сессия
     */
    private final Session session;

    /**
     * Транзакция
     */
    private final Transaction transaction;

    /**
     * Конструктор
     * @param session Сессия
     */
    DataBaseSessionHibernate(Session session) {
        this.session = session;
        this.transaction = session.beginTransaction();
    }

    /**
     * Геттер сессии
     * @return Сессия
     */
    public Session getSession() {
        return session;
    }

    /**
     * Геттер транзакции
     * @return Транзакция
     */
    Transaction getTransaction() {
        return transaction;
    }

    /**
     * Закрыть сессию
     */
    void close() {
        if (transaction.isActive())
            transaction.commit();
        session.close();
    }
}
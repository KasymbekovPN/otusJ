package ru.otus.kasymbekovPN.HW10.hibernate.sessionManager;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.otus.kasymbekovPN.HW10.api.sessionManager.DataBaseSession;

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
    @Override
    public Session getSession() {
        return session;
    }

    /**
     * Геттер транзакции
     * @return Транзакция
     */
    @Override
    public Transaction getTransaction() {
        return transaction;
    }

    /**
     * Закрыть сессию
     */
    @Override
    public void close() {
        if (transaction.isActive())
            transaction.commit();
        session.close();
    }
}

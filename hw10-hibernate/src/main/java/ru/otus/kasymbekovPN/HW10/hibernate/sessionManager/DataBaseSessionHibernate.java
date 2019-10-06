package ru.otus.kasymbekovPN.HW10.hibernate.sessionManager;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.otus.kasymbekovPN.HW10.api.sessionManager.DataBaseSession;

public class DataBaseSessionHibernate implements DataBaseSession {

    private final Session session;
    private final Transaction transaction;

    public DataBaseSessionHibernate(Session session) {
        this.session = session;
        this.transaction = session.beginTransaction();
    }

    @Override
    public Session getSession() {
        return session;
    }

    @Override
    public Transaction getTransaction() {
        return transaction;
    }

    @Override
    public void close() {
        if (transaction.isActive())
            transaction.commit();
        session.close();
    }
}

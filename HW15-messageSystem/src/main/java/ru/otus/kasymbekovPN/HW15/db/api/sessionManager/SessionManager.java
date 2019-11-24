package ru.otus.kasymbekovPN.HW15.db.api.sessionManager;

import ru.otus.kasymbekovPN.HW15.db.hibernate.sessionManager.DataBaseSessionHibernate;

/**
 * Интерфейс для реализации менеджера сессий
 */
public interface SessionManager extends AutoCloseable{

    /**
     * Начать сессию
     */
    void beginSession();

    /**
     * Фиксировать изменения
     */
    void commitSession();

    /**
     * Откатить изменения
     */
    void rollbackSession();

    /**
     * Закрыить сессию
     */
    void close();

    /**
     * Геттер текущий сессии
     * @return Текущая сессия
     */
    DataBaseSessionHibernate getCurrentSession();
}

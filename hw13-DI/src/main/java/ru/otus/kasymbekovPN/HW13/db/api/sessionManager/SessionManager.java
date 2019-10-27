package ru.otus.kasymbekovPN.HW13.db.api.sessionManager;

import ru.otus.kasymbekovPN.HW13.db.hibernate.sessionManager.DataBaseSessionHibernate;

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
    //< change with interface ???
    DataBaseSessionHibernate getCurrentSession();
}

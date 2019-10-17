package ru.otus.kasymbekovPN.HW10.api.sessionManager;

import ru.otus.kasymbekovPN.HW10.hibernate.sessionManager.DataBaseSessionHibernate;

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

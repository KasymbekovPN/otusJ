package ru.otus.kasymbekovPN.HW09.api.sessionManager;

/**
 * Интерфейс для реализации менеджера сессии
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
     * Закруть сессию
     */
    void close();

    /**
     * Геттер текущий сесси
     * @return Текущая сессия
     */
    DBSession getCurrentSession();
}

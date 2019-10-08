package ru.otus.kasymbekovPN.HW10.api.dao;

import ru.otus.kasymbekovPN.HW10.api.model.DBUser;
import ru.otus.kasymbekovPN.HW10.api.sessionManager.SessionManager;

import java.util.Optional;

/**
 * Интерфейс для реализации DAO для класса UВUser
 */
public interface DBUserDao {

    /**
     * Поиск по идентификатору
     * @param id идентификатор
     * @return Результат поиска, как инстанс.
     */
    Optional<DBUser> findById(long id);

    /**
     * Сохранение инстанса класс User в БД
     * @param DBUser Инстанс объекта
     * @return Идентификатор записи
     */
    long saveUser(DBUser DBUser);

    /**
     * Геттер менеджера сессий
     * @return менеджер сессий
     */
    SessionManager getSessionManager();
}

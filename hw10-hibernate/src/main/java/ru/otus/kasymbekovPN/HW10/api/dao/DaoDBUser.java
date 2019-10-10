package ru.otus.kasymbekovPN.HW10.api.dao;

import ru.otus.kasymbekovPN.HW10.api.model.DBUser;
import ru.otus.kasymbekovPN.HW10.api.sessionManager.SessionManager;

import java.util.Optional;

/**
 * Интерфейс для реализации DAO для DBUser
 */
public interface DaoDBUser {
    /**
     * Выгрузка записи по ключю
     * @param id ключ
     * @return полученный объект
     */
    Optional<DBUser> loadRecord(long id);

    /**
     * Сохранение объекта
     * @param user объект
     * @return Сохраненный объект
     */
    Optional<DBUser> createRecord(DBUser user);

    /**
     * Обновление объекта
     * @param user объект
     * @return обновленный объект
     */
    Optional<DBUser> updateRecord(DBUser user);

    /**
     * @return Текущий менеджер сессий
     */
    SessionManager getSessionManager();
}

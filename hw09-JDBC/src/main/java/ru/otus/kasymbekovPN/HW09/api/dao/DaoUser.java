package ru.otus.kasymbekovPN.HW09.api.dao;

import ru.otus.kasymbekovPN.HW09.api.sessionManager.SessionManager;
import ru.otus.kasymbekovPN.HW09.dataClass.User;

import java.util.Optional;

/**
 * Интерфейс для реализации DAO для User
 */
public interface DaoUser {
    /**
     * Выгрузка записи по ключю
     * @param id ключ
     * @return полученный объект
     */
    Optional<User> loadRecord(long id);

    /**
     * Сохранение объекта
     * @param user объект
     * @return Сохраненный объект
     */
    Optional<User> createRecord(User user);

    /**
     * Обновление объекта
     * @param user объект
     * @return обновленный объект
     */
    Optional<User> updateRecord(User user);

    /**
     * @return Текущий менеджер сессий
     */
    SessionManager getSessionManager();
}

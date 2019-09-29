package ru.otus.kasymbekovPN.HW09.api.dao;

import ru.otus.kasymbekovPN.HW09.api.sessionManager.SessionManager;

import java.util.Optional;

/**
 * Интерфейс для реализации DAO
 * @param <T> тип класса, с которым работает DAO
 */
public interface Dao<T> {

    /**
     * Выгрузка записи по ключю
     * @param id ключ
     * @param dummy болванка для формирования объекта с полученными данными
     * @return полученный объект
     */
    Optional<T> loadRecord(long id, T dummy);

    /**
     * Сохранение объекта
     * @param instance объект
     * @return Сохраненный объект
     */
    Optional<T> createRecord(T instance);

    /**
     * Обновление объекта
     * @param instance объект
     * @return обновленный объект
     */
    Optional<T> updateRecord(T instance);

    /**
     * @return Текущий менеджер сессий
     */
    SessionManager getSessionManager();
}

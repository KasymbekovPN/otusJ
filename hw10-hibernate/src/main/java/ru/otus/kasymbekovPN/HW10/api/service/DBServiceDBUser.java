package ru.otus.kasymbekovPN.HW10.api.service;

import ru.otus.kasymbekovPN.HW10.api.model.DBUser;

import java.util.Optional;

/**
 * Интерфейс для реализации сервиса работы класса
 * DBUser с БД.
 */
public interface DBServiceDBUser {
    /**
     * Создание записи в БД
     * @param user Записываемый инстанс
     * @return Записанный инстанс
     */
    Optional<DBUser> createRecord(DBUser user);

    /**
     * Обновление записи в БД
     * @param user Инстанс, запись которого должна быль обновленв
     * @return Инстанс
     */
    Optional<DBUser> updateRecord(DBUser user);

    /**
     * Выгрузка данных по ключу
     * @param id значение ключа
     * @return Инстанс, с выгруженными данными.
     */
    Optional<DBUser> loadRecord(long id);
}

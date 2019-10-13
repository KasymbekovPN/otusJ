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
     * @param user записываемый объект
     * @return успешность
     */
    boolean createRecord(DBUser user);

    /**
     * Обновление записи в БД
     * @param user Объект для записи
     * @return Успешность
     */
    boolean updateRecord(DBUser user);

    /**
     * Выгрузка данных по ключу
     * @param id значение ключа
     * @return Инстанс, с выгруженными данными.
     */
    Optional<DBUser> loadRecord(long id);
}

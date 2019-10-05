package ru.otus.kasymbekovPN.HW09.api.service;

import ru.otus.kasymbekovPN.HW09.dataClass.User;

import java.util.Optional;

/**
 * Интерфейс для реализации сервиса работы класса
 * User с БД.
 */
public interface DBServiceUser {
    /**
     * Создание записи в БД
     * @param user Записываемый инстанс
     * @return Записанный инстанс
     */
    Optional<User> createRecord(User user);

    /**
    * Обновление записи в БД
    * @param user Инстанс, запись которого должна быль обновленв
    * @return Инстанс
    */
    Optional<User> updateRecord(User user);

    /**
     * Выгрузка данных по ключу
     * @param id значение ключа
     * @return Инстанс, с выгруженными данными.
     */
    Optional<User> loadRecord(long id);
}

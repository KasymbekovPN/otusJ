package ru.otus.kasymbekovPN.HW13.db.api.service;

import ru.otus.kasymbekovPN.HW13.db.api.model.OnlineUser;

import java.util.List;
import java.util.Optional;

/**
 * Интерфейс для реализации сервиса работы класса
 * OnlineUser с БД.
 */
public interface DBServiceOnlineUser {
    /**
     * Создание записи в БД
     * @param user записываемый объект
     * @return успешность
     */
    boolean createRecord(OnlineUser user);

    /**
     * Обновление записи в БД
     * @param user Объект для записи
     * @return Успешность
     */
    boolean updateRecord(OnlineUser user);

    /**
     * Выгрузка данных по ключу
     * @param id значение ключа
     * @return Инстанс, с выгруженными данными.
     */
    Optional<OnlineUser> loadRecord(long id);

    /**
     * Выгрузка записей по значению поля login
     * @param login значение поля login
     * @return Список записей
     */
    List<OnlineUser> loadRecord(String login);

    /**
     * Выгрузка всех записей
     * @return Все записи таблицы
     */
    List<OnlineUser> loadAll();

    /**
     * Удаление поля по значению поля login
     * @param login значение поля login
     * @return Успешность
     */
    boolean deleteRecord(String login);
}

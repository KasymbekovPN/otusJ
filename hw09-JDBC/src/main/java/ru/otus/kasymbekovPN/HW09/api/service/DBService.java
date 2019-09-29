package ru.otus.kasymbekovPN.HW09.api.service;

import java.util.Optional;

/**
 * Сервис для работы с БД
 * @param <T> класс. с которым работает сервис
 */
public interface DBService<T> {

    /**
     * Создание записи в БД
     * @param instance Записываемый инстанс
     * @return Записанный инстанс
     */
    Optional<T> createRecord(T instance);

    /**
     * Обновление записи в БД
     * @param instance Инстанс, запись которого должна быль обновленв
     * @return Инстанс
     */
    Optional<T> updateRecord(T instance);

    /**
     * Выгрузка данных по ключу
     * @param id значение ключа
     * @param dummy цель для выгрузки
     * @return Инстанс, с выгруженными данными.
     */
    Optional<T> loadRecord(long id, T dummy);
}

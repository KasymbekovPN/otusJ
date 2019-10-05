package ru.otus.kasymbekovPN.HW09.api.service;

import ru.otus.kasymbekovPN.HW09.dataClass.Account;

import java.util.Optional;

/**
 * Интерфейс для реализации сервиса работы класса
 * Account с БД
 */
public interface DBServiceAccount {
    /**
     * Создание записи в БД
     * @param account Записываемый инстанс
     * @return Записанный инстанс
     */
    Optional<Account> createRecord(Account account);

    /**
     * Обновление записи в БД
     * @param account Инстанс, запись которого должна быль обновленв
     * @return Инстанс
     */
    Optional<Account> updateRecord(Account account);

    /**
     * Выгрузка данных по ключу
     * @param id значение ключа
     * @return Инстанс, с выгруженными данными.
     */
    Optional<Account> loadRecord(long id);
}

package ru.otus.kasymbekovPN.HW10.api.service;

import ru.otus.kasymbekovPN.HW10.api.model.DBUser;

import java.util.Optional;

/**
 * Интерфейс для реализации серсиса работы
 * инстансов класса DBUser
 */
public interface DBServiceDBUser {

    /**
     * Сохраняем инстанс в БД
     * @param DBUser инстанс
     * @return Идентификатор записи
     */
    long saveUser(DBUser DBUser);

    /**
     * Получаем запись с данными по идентификатору
     * @param id идентификатор
     * @return Результат запроса.
     */
    Optional<DBUser> getUser(long id);
}

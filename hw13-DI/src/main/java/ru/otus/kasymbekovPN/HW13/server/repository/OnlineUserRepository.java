package ru.otus.kasymbekovPN.HW13.server.repository;

import ru.otus.kasymbekovPN.HW13.db.api.model.OnlineUser;

import java.util.List;

/**
 * Интерфейс для реализации доступа к хранилищу записей OnlineUser
 */
public interface OnlineUserRepository {

    /**
     * Поиск запис OnlineUser по login.
     * Возвращает либо инстанс объекта OnlineUser, если запись найдена,
     * либо null, если запись не найдена.
     * @param login Значение поля для поиска
     * @return Результат поиска.
     */
    OnlineUser findByLogin(String login);

    /**
     * Выгрузка всех записей из таблицы
     * @return Список инстансов OnlineUser с данными из таблицы
     */
    List<OnlineUser> loadAll();

    /**
     * Удаление запис по значению столбца login
     * @param login Значение столбца login
     * @return Успешность
     */
    long create(String login, String password);

    /**
     * Удаление запис по значению столбца login
     * @param login Значение столбца login
     * @return Успешность
     */
    boolean delete(String login);
}

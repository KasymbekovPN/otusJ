package ru.otus.kasymbekovPN.HW09.api.dao;

import ru.otus.kasymbekovPN.HW09.api.sessionManager.SessionManager;
import ru.otus.kasymbekovPN.HW09.dataClass.Account;

import java.util.Optional;

/**
 * Интерфейс для реализации DAO для Account
 */
public interface DaoAccount {
    /**
     * Выгрузка записи по ключю
     * @param id ключ
     * @return полученный объект
     */
    Optional<Account> loadRecord(long id);

    /**
     * Сохранение объекта
     * @param account объект
     * @return Сохраненный объект
     */
    Optional<Account> createRecord(Account account);

    /**
     * Обновление объекта
     * @param account объект
     * @return обновленный объект
     */
    Optional<Account> updateRecord(Account account);

    /**
     * @return Текущий менеджер сессий
     */
    SessionManager getSessionManager();
}

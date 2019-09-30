package ru.otus.kasymbekovPN.HW09.jdbc.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.kasymbekovPN.HW09.api.dao.DaoAccount;
import ru.otus.kasymbekovPN.HW09.api.dao.DaoException;
import ru.otus.kasymbekovPN.HW09.api.sessionManager.SessionManager;
import ru.otus.kasymbekovPN.HW09.dataClass.Account;
import ru.otus.kasymbekovPN.HW09.jdbc.executor.DBExecutorJDBC;

import java.util.Optional;

/**
 * Реализация DOA для класса Account работы с JDBC
 */
public class DaoJDBCAccount implements DaoAccount {

    private static Logger logger = LoggerFactory.getLogger(DaoJDBCAccount.class);

    /**
     * Менеджер сессий
     */
    private final SessionManager sessionManager;

    /**
     * Экзекутор БД
     */
    private final DBExecutorJDBC<Account> dbExecutor;

    /**
     * Контсруктор
     * @param sessionManager менеджер сессий
     * @param dbExecutor экзекутор БД
     */
    public DaoJDBCAccount(SessionManager sessionManager, DBExecutorJDBC<Account> dbExecutor) {
        this.sessionManager = sessionManager;
        this.dbExecutor = dbExecutor;
    }

    /**
     * Выгрузка записи по ключю
     * @param id ключ
     * @return полученный объект
     */
    @Override
    public Optional<Account> loadRecord(long id) {
        try{
            return dbExecutor.loadRecord(1, new Account(), sessionManager.getCurrentSession().getConnection());
        } catch (Exception ex){
            logger.error(ex.getMessage(), ex);
            throw new DaoException(ex);
        }
    }

    /**
     * Сохранение объекта
     * @param account объект
     * @return Сохраненный объект
     */
    @Override
    public Optional<Account> createRecord(Account account) {
        try{
            return dbExecutor.createRecord(account, sessionManager.getCurrentSession().getConnection());
        } catch (Exception ex){
            logger.error(ex.getMessage(), ex);
            throw new DaoException(ex);
        }
    }

    /**
     * Обновление объекта
     * @param account объект
     * @return обновленный объект
     */
    @Override
    public Optional<Account> updateRecord(Account account) {
        try{
            return dbExecutor.updateRecord(account, sessionManager.getCurrentSession().getConnection());
        } catch (Exception ex){
            logger.error(ex.getMessage(), ex);
            throw new DaoException(ex);
        }
    }

    /**
     * @return Текущий менеджер сессий
     */
    @Override
    public SessionManager getSessionManager() {
        return sessionManager;
    }
}

package ru.otus.kasymbekovPN.HW09.jdbc.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.kasymbekovPN.HW09.api.dao.Dao;
import ru.otus.kasymbekovPN.HW09.api.dao.DaoException;
import ru.otus.kasymbekovPN.HW09.api.sessionManager.SessionManager;
import ru.otus.kasymbekovPN.HW09.jdbc.executor.DBExecutorJDBC;

import java.util.Optional;

/**
 * Реализация DOA для работы с JDBC
 * @param <T> тип класса, с которым работает DAO
 */
public class DaoJDBC<T> implements Dao<T> {

    private static Logger logger = LoggerFactory.getLogger(DaoJDBC.class);

    /**
     * Менеджер сессий
     */
    private final SessionManager sessionManager;

    /**
     * Экзекутор БД
     */
    private final DBExecutorJDBC<T> dbExecutor;

    /**
     * Контсруктор
     * @param sessionManager менеджер сессий
     * @param dbExecutor экзекутор БД
     */
    public DaoJDBC(SessionManager sessionManager, DBExecutorJDBC<T> dbExecutor) {
        this.sessionManager = sessionManager;
        this.dbExecutor = dbExecutor;
    }

    /**
     * Выгрузка записи по ключю
     * @param id ключ
     * @param dummy болванка для формирования объекта с полученными данными
     * @return полученный объект
     */
    @Override
    public Optional<T> loadRecord(long id, T dummy) {
        try{
            return dbExecutor.loadRecord(1, dummy, sessionManager.getCurrentSession().getConnection());
        } catch (Exception ex){
            logger.error(ex.getMessage(), ex);
            throw new DaoException(ex);
        }
    }

    /**
     * Сохранение объекта
     * @param instance объект
     * @return Сохраненный объект
     */
    @Override
    public Optional<T> createRecord(T instance) {
        try{
            return dbExecutor.createRecord(instance, sessionManager.getCurrentSession().getConnection());
        } catch (Exception ex){
            logger.error(ex.getMessage(), ex);
            throw new DaoException(ex);
        }
    }

    /**
     * Обновление объекта
     * @param instance объект
     * @return обновленный объект
     */
    @Override
    public Optional<T> updateRecord(T instance) {
        try{
            return dbExecutor.updateRecord(instance, sessionManager.getCurrentSession().getConnection());
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

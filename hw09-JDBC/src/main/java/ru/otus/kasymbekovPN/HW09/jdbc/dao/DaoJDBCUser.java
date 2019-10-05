package ru.otus.kasymbekovPN.HW09.jdbc.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.kasymbekovPN.HW09.api.dao.DaoException;
import ru.otus.kasymbekovPN.HW09.api.dao.DaoUser;
import ru.otus.kasymbekovPN.HW09.api.sessionManager.SessionManager;
import ru.otus.kasymbekovPN.HW09.dataClass.User;
import ru.otus.kasymbekovPN.HW09.jdbc.executor.DBExecutorJDBC;

import java.util.Optional;

/**
 * Реализация DOA для класса User работы с JDBC
 */
public class DaoJDBCUser implements DaoUser {

    private static Logger logger = LoggerFactory.getLogger(DaoJDBCUser.class);

    /**
     * Менеджер сессий
     */
    private final SessionManager sessionManager;

    /**
     * Экзекутор БД
     */
    private final DBExecutorJDBC<User> dbExecutor;

    /**
     * Контсруктор
     * @param sessionManager менеджер сессий
     * @param dbExecutor экзекутор БД
     */
    public DaoJDBCUser(SessionManager sessionManager, DBExecutorJDBC<User> dbExecutor) {
        this.sessionManager = sessionManager;
        this.dbExecutor = dbExecutor;
    }

    /**
     * Выгрузка записи по ключю
     * @param id ключ
     * @return полученный объект
     */
    @Override
    public Optional<User> loadRecord(long id) {
        try{
            return dbExecutor.loadRecord(1, User.class, sessionManager.getCurrentSession().getConnection());
        } catch (Exception ex){
            logger.error(ex.getMessage(), ex);
            throw new DaoException(ex);
        }
    }

    /**
     * Сохранение объекта
     * @param user объект
     * @return Сохраненный объект
     */
    @Override
    public Optional<User> createRecord(User user) {
        try{
            return dbExecutor.createRecord(user, sessionManager.getCurrentSession().getConnection());
        } catch (Exception ex){
            logger.error(ex.getMessage(), ex);
            throw new DaoException(ex);
        }
    }

    /**
     * Обновление объекта
     * @param user объект
     * @return обновленный объект
     */
    @Override
    public Optional<User> updateRecord(User user) {
        try{
            return dbExecutor.updateRecord(user, sessionManager.getCurrentSession().getConnection());
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

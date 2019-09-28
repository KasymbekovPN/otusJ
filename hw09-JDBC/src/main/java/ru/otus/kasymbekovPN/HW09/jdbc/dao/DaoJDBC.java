package ru.otus.kasymbekovPN.HW09.jdbc.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.kasymbekovPN.HW09.api.dao.Dao;
import ru.otus.kasymbekovPN.HW09.api.dao.DaoException;
import ru.otus.kasymbekovPN.HW09.api.sessionManager.SessionManager;
import ru.otus.kasymbekovPN.HW09.jdbc.executor.DBExecutorJDBC;

import java.util.Optional;

//< rename
public class DaoJDBC<T> implements Dao<T> {

    private static Logger logger = LoggerFactory.getLogger(DaoJDBC.class);

    private final SessionManager sessionManager;

    //< change with interface ???
    private final DBExecutorJDBC<T> dbExecutor;

    public DaoJDBC(SessionManager sessionManager, DBExecutorJDBC<T> dbExecutor) {
        this.sessionManager = sessionManager;
        this.dbExecutor = dbExecutor;
    }

    @Override
    public Optional<T> loadRecord(long id, T dummy) {
        try{
            return dbExecutor.loadRecord(1, dummy, sessionManager.getCurrentSession().getConnection());
        } catch (Exception ex){
            logger.error(ex.getMessage(), ex);
            throw new DaoException(ex);
        }
    }

    @Override
    public Optional<T> createRecord(T instance) {
        try{
            return dbExecutor.createRecord(instance, sessionManager.getCurrentSession().getConnection());
        } catch (Exception ex){
            logger.error(ex.getMessage(), ex);
            throw new DaoException(ex);
        }
    }

    @Override
    public Optional<T> updateRecord(T instance) {
        try{
            return dbExecutor.updateRecord(instance, sessionManager.getCurrentSession().getConnection());
        } catch (Exception ex){
            logger.error(ex.getMessage(), ex);
            throw new DaoException(ex);
        }
    }

    @Override
    public SessionManager getSessionManager() {
        return sessionManager;
    }

    //<
//    private Connection getConnection(){
//        return sessionManager.getCurrentSession().getConnection();
//    }
}

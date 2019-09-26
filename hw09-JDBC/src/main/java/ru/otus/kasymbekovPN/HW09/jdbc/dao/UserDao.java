package ru.otus.kasymbekovPN.HW09.jdbc.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.kasymbekovPN.HW09.User;
import ru.otus.kasymbekovPN.HW09.api.dao.InstanceDao;
import ru.otus.kasymbekovPN.HW09.api.dao.UserDaoException;
import ru.otus.kasymbekovPN.HW09.api.sessionManager.SessionManager;
import ru.otus.kasymbekovPN.HW09.jdbc.executor.DBExecutorImpl;
import ru.otus.kasymbekovPN.HW09.jdbc.sessionManager.SessionManagerJDBCImpl;

import java.sql.Connection;
import java.util.Optional;

//< rename
public class UserDao<T> implements InstanceDao<T> {

    private static Logger logger = LoggerFactory.getLogger(UserDao.class);

    private final SessionManager sessionManager;
    private final DBExecutorImpl<T> dbExecutor;

    public UserDao(SessionManager sessionManager, DBExecutorImpl<T> dbExecutor) {
        this.sessionManager = sessionManager;
        this.dbExecutor = dbExecutor;
    }

    @Override
    public Optional<T> loadRecord(long id, T dummy) {

        try{
            return dbExecutor.loadRecord(1, dummy, sessionManager.getCurrentSession().getConnection());
        } catch (Exception ex){
            logger.error(ex.getMessage(), ex);
            throw new UserDaoException(ex);
        }

//            try{
//                return dbExecutor.selectRecord(getConnection(), "SELECT id, name from user WHERE id = ?",id, rs ->{
//                    try{
//                        if (rs.next()){
//                            return new User_(rs.getLong("id"), rs.getString("name"));
//                        }
//                    } catch (SQLException ex){
//                        logger.error(ex.getMessage(), ex);
//                    }
//                    return null;
//                });
//            } catch(Exception ex){
//                logger.error(ex.getMessage(), ex);
//            }
//
//            return Optional.empty();
    }

    @Override
    public void createRecord(T instance) {

        //<
        logger.info("UserDao createRecord");
        //<

        try{
            dbExecutor.createRecord(instance, sessionManager.getCurrentSession().getConnection());
        } catch (Exception ex){
            logger.error(ex.getMessage(), ex);
            throw new UserDaoException(ex);
        }
    }

    @Override
    public void updateRecord(T instance) {
        //< ???????
    }

    @Override
    public SessionManager getSessionManager() {
        return sessionManager;
    }

    private Connection getConnection(){
        return sessionManager.getCurrentSession().getConnection();
    }
}

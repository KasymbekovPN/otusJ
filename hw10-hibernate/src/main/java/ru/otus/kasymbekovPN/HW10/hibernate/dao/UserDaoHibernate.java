package ru.otus.kasymbekovPN.HW10.hibernate.dao;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.kasymbekovPN.HW10.api.dao.UserDao;
import ru.otus.kasymbekovPN.HW10.api.dao.UserDaoException;
import ru.otus.kasymbekovPN.HW10.api.model.DBUser;
import ru.otus.kasymbekovPN.HW10.api.sessionManager.DataBaseSession;
import ru.otus.kasymbekovPN.HW10.api.sessionManager.SessionManager;

import java.util.Optional;

public class UserDaoHibernate implements UserDao {

    private static Logger logger = LoggerFactory.getLogger(UserDaoHibernate.class);
    private final SessionManager sessionManager;

    public UserDaoHibernate(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public Optional<DBUser> findById(long id) {
        DataBaseSession currentSession = sessionManager.getCurrentSession();
        try{
            return Optional.ofNullable(
                    currentSession.getSession().find(DBUser.class, id)
            );
        } catch (Exception ex){
            logger.error(ex.getMessage(), ex);
        }

        return Optional.empty();
    }

    @Override
    public long saveUser(DBUser DBUser) {
        DataBaseSession currentSession = sessionManager.getCurrentSession();
        try{
            Session session = currentSession.getSession();
            if (DBUser.getId() > 0){
                session.merge(DBUser);
            } else {
                session.persist(DBUser);
            }

            return DBUser.getId();
        } catch (Exception ex){
            logger.error(ex.getMessage(), ex);
            throw new UserDaoException(ex);
        }
    }

    @Override
    public SessionManager getSessionManager() {
        return sessionManager;
    }
}

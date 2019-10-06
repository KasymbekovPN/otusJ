package ru.otus.kasymbekovPN.HW10.api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.kasymbekovPN.HW10.api.dao.UserDao;
import ru.otus.kasymbekovPN.HW10.api.model.User;
import ru.otus.kasymbekovPN.HW10.api.sessionManager.SessionManager;

import java.util.Optional;

public class DBServiceUserImpl implements DBServiceUser {

    private static Logger logger = LoggerFactory.getLogger(DBServiceUserImpl.class);

    private final UserDao dao;

    public DBServiceUserImpl(UserDao dao) {
        this.dao = dao;
    }

    @Override
    public long saveUser(User user) {
        try(SessionManager sessionManager = dao.getSessionManager()){
            sessionManager.beginSession();
            try{
                long userId = dao.saveUser(user);
                sessionManager.commitSession();

                logger.info("created user : {}", userId);
                return userId;
            } catch (Exception ex){
                logger.error(ex.getMessage(), ex);
                sessionManager.rollbackSession();
                throw new DBServiceException(ex);
            }
        }
    }

    @Override
    public Optional<User> getUser(long id) {
        try(SessionManager sessionManager = dao.getSessionManager()){
            sessionManager.beginSession();
            try{
                Optional<User> user = dao.findById(id);

                logger.info("user : {}", user.orElse(null));
                return user;
            } catch(Exception ex){
                logger.error(ex.getMessage(), ex);
                sessionManager.rollbackSession();
            }
            return Optional.empty();
        }
    }
}

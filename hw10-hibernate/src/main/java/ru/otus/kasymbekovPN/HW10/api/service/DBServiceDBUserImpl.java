package ru.otus.kasymbekovPN.HW10.api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.kasymbekovPN.HW10.api.dao.DBUserDao;
import ru.otus.kasymbekovPN.HW10.api.model.DBUser;
import ru.otus.kasymbekovPN.HW10.api.sessionManager.SessionManager;

import java.util.Optional;

/**
 * Реализация серсиса работы инстансов класса DBUser
 */
public class DBServiceDBUserImpl implements DBServiceDBUser {

    private static Logger logger = LoggerFactory.getLogger(DBServiceDBUserImpl.class);

    /**
     * DAO
     */
    private final DBUserDao dao;

    /**
     * Конструктор
     * @param dao DAO
     */
    public DBServiceDBUserImpl(DBUserDao dao) {
        this.dao = dao;
    }

    /**
     * Сохраняем инстанс в БД
     * @param DBUser инстанс
     * @return Идентификатор записи
     */
    @Override
    public long saveUser(DBUser DBUser) {
        try(SessionManager sessionManager = dao.getSessionManager()){
            sessionManager.beginSession();
            try{
                long userId = dao.saveUser(DBUser);
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

    /**
     * Получаем запись с данными по идентификатору
     * @param id идентификатор
     * @return Результат запроса.
     */
    @Override
    public Optional<DBUser> getUser(long id) {
        try(SessionManager sessionManager = dao.getSessionManager()){
            sessionManager.beginSession();
            try{
                Optional<DBUser> user = dao.findById(id);

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

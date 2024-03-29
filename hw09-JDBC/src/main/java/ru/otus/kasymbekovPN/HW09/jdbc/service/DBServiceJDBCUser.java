package ru.otus.kasymbekovPN.HW09.jdbc.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.kasymbekovPN.HW09.api.dao.DaoUser;
import ru.otus.kasymbekovPN.HW09.api.service.DBServiceUser;
import ru.otus.kasymbekovPN.HW09.api.service.DbServiceException;
import ru.otus.kasymbekovPN.HW09.api.sessionManager.SessionManager;
import ru.otus.kasymbekovPN.HW09.dataClass.User;

import java.util.Optional;

/**
 * Реализация сервиса для работы класса User с БД
 */
public class DBServiceJDBCUser implements DBServiceUser {

    private static Logger logger = LoggerFactory.getLogger(DBServiceJDBCUser.class);

    /**
     * DAO
     */
    private final DaoUser dao;

    /**
     * Конструктор
     * @param dao DAO
     */
    public DBServiceJDBCUser(DaoUser dao) {
        this.dao = dao;
    }

    /**
     * Создание записи в БД
     * @param user Записываемый инстанс
     * @return Записанный инстанс
     */
    @Override
    public Optional<User> createRecord(User user) {
        try(SessionManager sessionManager = dao.getSessionManager()){
            sessionManager.beginSession();
            try{
                Optional<User> record = dao.createRecord(user);
                if (record.isPresent()){
                    sessionManager.commitSession();
                    logger.info("record was create");
                } else {
                    sessionManager.rollbackSession();
                    logger.info("record wasn't create");
                }
                return record;
            } catch(Exception ex){
                logger.error(ex.getMessage(), ex);
                sessionManager.rollbackSession();
                throw new DbServiceException(ex);
            }
        }
    }

    /**
     * Обновление записи в БД
     * @param user Инстанс, запись которого должна быль обновленв
     * @return Инстанс
     */
    @Override
    public Optional<User> updateRecord(User user) {
        try(SessionManager sessionManager = dao.getSessionManager()){
            sessionManager.beginSession();
            try{
                Optional<User> record = dao.updateRecord(user);
                if (record.isPresent()){
                    sessionManager.commitSession();
                    logger.info("record was update");
                } else {
                    sessionManager.rollbackSession();
                    logger.info("record wasn't update");
                }
                return record;
            } catch(Exception ex){
                logger.error(ex.getMessage(), ex);
                sessionManager.rollbackSession();
                throw new DbServiceException(ex);
            }
        }
    }

    /**
     * Выгрузка данных по ключу
     * @param id значение ключа
     * @return Инстанс, с выгруженными данными.
     */
    @Override
    public Optional<User> loadRecord(long id) {
        try(SessionManager sessionManager = dao.getSessionManager()){
            sessionManager.beginSession();
            try{
                Optional<User> record = dao.loadRecord(id);
                logger.info("loaded record : {}", record.orElse(null));
                return record;
            } catch (Exception ex){
                logger.error(ex.getMessage(), ex);
                sessionManager.rollbackSession();
            }

            return Optional.empty();
        }
    }
}

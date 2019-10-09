package ru.otus.kasymbekovPN.HW10.api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.kasymbekovPN.HW10.api.dao.DaoDBUser_;
import ru.otus.kasymbekovPN.HW10.api.model.DBUser;
import ru.otus.kasymbekovPN.HW10.api.sessionManager.SessionManager;

import java.util.Optional;

/**
 * Реализации сервиса работы класса
 * DBUser с БД.
 */
public class DBServiceDBUserImpl_ implements DBServiceDBUser_ {

    private static Logger logger = LoggerFactory.getLogger(DBServiceDBUserImpl_.class);

    /**
     * DAO
     */
    private final DaoDBUser_ dao;

    /**
     * Конструктор
     * @param dao DAO
     */
    public DBServiceDBUserImpl_(DaoDBUser_ dao) {
        this.dao = dao;
    }

    /**
     * Создание записи в БД
     * @param user Записываемый инстанс
     * @return Записанный инстанс
     */
    @Override
    public Optional<DBUser> createRecord(DBUser user) {
        try(SessionManager sessionManager = dao.getSessionManager()){
            sessionManager.beginSession();
            try{
                Optional<DBUser> record = dao.createRecord(user);
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
                throw new DBServiceException(ex);
            }
        }
    }

    /**
     * Обновление записи в БД
     * @param user Инстанс, запись которого должна быль обновленв
     * @return Инстанс
     */
    @Override
    public Optional<DBUser> updateRecord(DBUser user) {
        try(SessionManager sessionManager = dao.getSessionManager()){
            sessionManager.beginSession();
            try{
                Optional<DBUser> record = dao.updateRecord(user);
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
                throw new DBServiceException(ex);
            }
        }
    }

    /**
     * Выгрузка данных по ключу
     * @param id значение ключа
     * @return Инстанс, с выгруженными данными.
     */
    @Override
    public Optional<DBUser> loadRecord(long id) {
        try(SessionManager sessionManager = dao.getSessionManager()){
            sessionManager.beginSession();
            try{
                Optional<DBUser> record = dao.loadRecord(id);
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

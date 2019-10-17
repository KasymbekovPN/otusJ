package ru.otus.kasymbekovPN.HW10.api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.kasymbekovPN.HW10.api.dao.DaoDBUser;
import ru.otus.kasymbekovPN.HW10.api.model.DBUser;
import ru.otus.kasymbekovPN.HW10.api.sessionManager.SessionManager;

import java.util.Optional;

/**
 * Реализации сервиса работы класса
 * DBUser с БД.
 */
public class DBServiceDBUserImpl implements DBServiceDBUser {

    private static Logger logger = LoggerFactory.getLogger(DBServiceDBUserImpl.class);

    /**
     * DAO
     */
    private final DaoDBUser dao;

    /**
     * Конструктор
     * @param dao DAO
     */
    public DBServiceDBUserImpl(DaoDBUser dao) {
        this.dao = dao;
    }

    /**
     * Создание записи в БД
     * @param user записываемый объект
     * @return Успешность
     */
    @Override
    public boolean createRecord(DBUser user) {
        try(SessionManager sessionManager = dao.getSessionManager()){
            sessionManager.beginSession();
            try{
                boolean success = dao.createRecord(user);
                if (success){
                    sessionManager.commitSession();
                    logger.info("The record was create");
                } else {
                    sessionManager.rollbackSession();
                    logger.info("The record wasn't create");
                }

                return success;
            } catch(Exception ex){
                logger.error(ex.getMessage(), ex);
                sessionManager.rollbackSession();
                throw new DBServiceException(ex);
            }
        }
    }

    /**
     * Обновление записи в БД
     * @param user Объект для записи
     * @return Успешность
     */
    @Override
    public boolean updateRecord(DBUser user) {
        try(SessionManager sessionManager = dao.getSessionManager()){
            sessionManager.beginSession();
            try{
                boolean success = dao.updateRecord(user);
                if (success){
                    sessionManager.commitSession();
                    logger.info("The record was update");
                } else {
                    sessionManager.rollbackSession();
                    logger.info("The record wasn't update");
                }
                return success;
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

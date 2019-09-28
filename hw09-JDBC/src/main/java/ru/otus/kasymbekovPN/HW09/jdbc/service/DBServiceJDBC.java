package ru.otus.kasymbekovPN.HW09.jdbc.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.kasymbekovPN.HW09.api.dao.Dao;
import ru.otus.kasymbekovPN.HW09.api.service.DBService;
import ru.otus.kasymbekovPN.HW09.api.service.DbServiceException;
import ru.otus.kasymbekovPN.HW09.api.sessionManager.SessionManager;

import java.util.Optional;

public class DBServiceJDBC<T> implements DBService<T> {

    private static Logger logger = LoggerFactory.getLogger(DBServiceJDBC.class);

    private final Dao<T> dao;

    public DBServiceJDBC(Dao<T> dao) {
        this.dao = dao;
    }

    @Override
    public Optional<T> createRecord(T instance) {
        try(SessionManager sessionManager = dao.getSessionManager()){
            sessionManager.beginSession();
            try{
                Optional<T> record = dao.createRecord(instance);
                if (record.isPresent()){
                    sessionManager.commitSession();
                    logger.info("record was create");
                } else {
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

    @Override
    public Optional<T> updateRecord(T instance) {
        try(SessionManager sessionManager = dao.getSessionManager()){
            sessionManager.beginSession();
            try{
                Optional<T> record = dao.updateRecord(instance);
                if (record.isPresent()){
                    sessionManager.commitSession();
                    logger.info("record was update");
                } else {
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

    @Override
    public Optional<T> loadRecord(long id, T dummy) {
        try(SessionManager sessionManager = dao.getSessionManager()){
            sessionManager.beginSession();
            try{
                Optional<T> record = dao.loadRecord(id, dummy);
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

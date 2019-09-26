package ru.otus.kasymbekovPN.HW09.api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.kasymbekovPN.HW09.api.dao.InstanceDao;
import ru.otus.kasymbekovPN.HW09.api.sessionManager.SessionManager;

import java.util.Optional;

public class DBServiceImpl<T> implements DBService<T> {

    private static Logger logger = LoggerFactory.getLogger(DBServiceImpl.class);

    private final InstanceDao<T> dao;

    public DBServiceImpl(InstanceDao<T> dao) {
        this.dao = dao;
    }

    @Override
    public void createRecord(T instance) {
        try(SessionManager sessionManager = dao.getSessionManager()){
            sessionManager.beginSession();
            try{
                dao.createRecord(instance);
                sessionManager.commitSession();

                logger.info("created");
            } catch(Exception ex){
                logger.error(ex.getMessage(), ex);
                sessionManager.rollbackSession();
                throw new DbServiceException(ex);
            }
        }
    }

    @Override
    public void updateRecord(T instance) {
        try(SessionManager sessionManager = dao.getSessionManager()){
            sessionManager.beginSession();
            try{
                dao.updateRecord(instance);
                sessionManager.commitSession();

                logger.info("updated");
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
                Optional<T> opt = dao.loadRecord(id, dummy);

                logger.info("record : {}", opt.orElse(null));
                return opt;
            } catch (Exception ex){
                logger.error(ex.getMessage(), ex);
                sessionManager.rollbackSession();
            }

            return Optional.empty();
        }
    }
}

package ru.otus.kasymbekovPN.HW09.jdbc.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.kasymbekovPN.HW09.api.dao.DaoAccount;
import ru.otus.kasymbekovPN.HW09.api.service.DBServiceAccount;
import ru.otus.kasymbekovPN.HW09.api.service.DbServiceException;
import ru.otus.kasymbekovPN.HW09.api.sessionManager.SessionManager;
import ru.otus.kasymbekovPN.HW09.dataClass.Account;

import java.util.Optional;

/**
 * Реализация сервиса для работы класса Account с БД
 */
public class DBServiceJDBCAccount implements DBServiceAccount {

    private static Logger logger = LoggerFactory.getLogger(DBServiceJDBCAccount.class);

    /**
     * DAO
     */
    private final DaoAccount dao;

    /**
     * Конструктор
     * @param dao DAO
     */
    public DBServiceJDBCAccount(DaoAccount dao) {
        this.dao = dao;
    }

    /**
     * Создание записи в БД
     * @param account Записываемый инстанс
     * @return Записанный инстанс
     */
    @Override
    public Optional<Account> createRecord(Account account) {
        try(SessionManager sessionManager = dao.getSessionManager()){
            sessionManager.beginSession();
            try{
                Optional<Account> record = dao.createRecord(account);
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
     * @param account Инстанс, запись которого должна быль обновленв
     * @return Инстанс
     */
    @Override
    public Optional<Account> updateRecord(Account account) {
        try(SessionManager sessionManager = dao.getSessionManager()){
            sessionManager.beginSession();
            try{
                Optional<Account> record = dao.updateRecord(account);
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
    public Optional<Account> loadRecord(long id) {
        try(SessionManager sessionManager = dao.getSessionManager()){
            sessionManager.beginSession();
            try{
                Optional<Account> record = dao.loadRecord(id);
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

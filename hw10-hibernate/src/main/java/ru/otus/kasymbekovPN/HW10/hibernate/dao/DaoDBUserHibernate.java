package ru.otus.kasymbekovPN.HW10.hibernate.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.kasymbekovPN.HW10.api.dao.DBUserDaoException;
import ru.otus.kasymbekovPN.HW10.api.dao.DaoDBUser;
import ru.otus.kasymbekovPN.HW10.api.model.DBUser;
import ru.otus.kasymbekovPN.HW10.api.sessionManager.SessionManager;
import ru.otus.kasymbekovPN.HW10.hibernate.sessionManager.DataBaseSessionHibernate;
import ru.otus.kasymbekovPN.HW10.hibernate.sessionManager.SessionManagerHibernate;

import java.util.Optional;

/**
 * Реализации DAO для DBUser
 */
public class DaoDBUserHibernate implements DaoDBUser {

    private static Logger logger = LoggerFactory.getLogger(DaoDBUserHibernate.class);

    /**
     * Менеджер сессий
     */
    private final SessionManagerHibernate sessionManager;

    /**
     * Конструктор
     * @param sessionManager менеджер сессий
     */
    public DaoDBUserHibernate(SessionManagerHibernate sessionManager) {
        this.sessionManager = sessionManager;
    }

    /**
     * Выгрузка записи по ключю
     * @param id ключ
     * @return полученный объект
     */
    @Override
    public Optional<DBUser> loadRecord(long id) {
        DataBaseSessionHibernate currentSession = sessionManager.getCurrentSession();
        try{
            return Optional.ofNullable(
                    currentSession.getSession().find(DBUser.class, id)
            );
        } catch (Exception ex){
            logger.error(ex.getMessage(), ex);
        }

        return Optional.empty();
    }

    /**
     * Сохрание объекта
     * @param user объекта
     * @return Успешность сохранения
     */
    @Override
    public boolean createRecord(DBUser user) {
        DataBaseSessionHibernate currentSession = sessionManager.getCurrentSession();
        try{
            if (user.getId() == 0){
                currentSession.getSession().persist(user);
                return true;
            }

            logger.error("This record already exist");
            return false;
        } catch (Exception ex){
            logger.error(ex.getMessage(), ex);
            throw new DBUserDaoException(ex);
        }
    }

    /**
     * Обновление записи объекта
     * @param user записываем объект
     * @return успешность
     */
    @Override
    public boolean updateRecord(DBUser user) {
        DataBaseSessionHibernate currentSession = sessionManager.getCurrentSession();
        try{
            if (user.getId() > 0){
                currentSession.getSession().merge(user);
                return true;
            }

            logger.error("The record doesn't exist");
            return false;
        } catch (Exception ex){
            logger.error(ex.getMessage(), ex);
            throw new DBUserDaoException(ex);
        }
    }

    /**
     * @return Текущий менеджер сессий
     */
    @Override
    public SessionManager getSessionManager() {
        return sessionManager;
    }
}

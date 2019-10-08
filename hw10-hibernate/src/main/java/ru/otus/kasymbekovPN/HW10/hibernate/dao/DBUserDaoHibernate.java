package ru.otus.kasymbekovPN.HW10.hibernate.dao;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.kasymbekovPN.HW10.api.dao.DBUserDao;
import ru.otus.kasymbekovPN.HW10.api.dao.DBUserDaoException;
import ru.otus.kasymbekovPN.HW10.api.model.DBUser;
import ru.otus.kasymbekovPN.HW10.api.sessionManager.DataBaseSession;
import ru.otus.kasymbekovPN.HW10.api.sessionManager.SessionManager;

import java.util.Optional;

/**
 * Реализация DAO для класса User
 */
public class DBUserDaoHibernate implements DBUserDao {

    private static Logger logger = LoggerFactory.getLogger(DBUserDaoHibernate.class);

    /**
     * Менеджер сессий
     */
    private final SessionManager sessionManager;

    /**
     * Конструктор
     * @param sessionManager менеджер сессий
     */
    public DBUserDaoHibernate(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    /**
     * Поиск по идентификатору
     * @param id идентификатор
     * @return Результат поиска, как инстанс.
     */
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

    /**
     * Сохранение инстанса класс User в БД
     * @param DBUser Инстанс объекта
     * @return Идентификатор записи
     */
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
            throw new DBUserDaoException(ex);
        }
    }

    /**
     * Геттер менеджера сессий
     * @return менеджер сессий
     */
    @Override
    public SessionManager getSessionManager() {
        return sessionManager;
    }
}

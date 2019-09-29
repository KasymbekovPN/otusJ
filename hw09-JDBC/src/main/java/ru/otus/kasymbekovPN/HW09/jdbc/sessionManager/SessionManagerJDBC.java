package ru.otus.kasymbekovPN.HW09.jdbc.sessionManager;

import ru.otus.kasymbekovPN.HW09.api.sessionManager.DBSession;
import ru.otus.kasymbekovPN.HW09.api.sessionManager.SessionManager;
import ru.otus.kasymbekovPN.HW09.api.sessionManager.SessionManagerException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * РеализациЯ менеджера сессии
 */
public class SessionManagerJDBC implements SessionManager {
    private static final int TIMEOUT_IN_SECONDS = 5;

    /**
     * Источник данных
     */
    private final DataSource dataSource;

    /**
     * Соединение
     */
    private Connection connection;

    /**
     * Сессия
     */
    private DBSession dbSession;

    public SessionManagerJDBC(DataSource dataSource){
        if (null == dataSource)
            throw new SessionManagerException("DataSource is null");
        this.dataSource = dataSource;
    }

    /**
     * Начать сессию
     */
    @Override
    public void beginSession() {
        try{
            connection = dataSource.getConnection();
            dbSession = new DBSessionJDBC(connection);
        } catch (SQLException ex){
            throw new SessionManagerException(ex);
        }
    }

    /**
     * Фиксировать изменения
     */
    @Override
    public void commitSession() {
        checkConnection();
        try{
            connection.commit();
        } catch (SQLException ex){
            throw new SessionManagerException(ex);
        }
    }

    /**
     * Откатить изменения
     */
    @Override
    public void rollbackSession() {
        checkConnection();
        try{
            connection.rollback();
        } catch (SQLException ex){
            throw new SessionManagerException(ex);
        }
    }

    /**
     * Закруть сессию
     */
    @Override
    public void close() {
        checkConnection();
        try{
            connection.close();
        } catch (SQLException ex){
            throw new SessionManagerException(ex);
        }
    }

    /**
     * Геттер текущий сесси
     * @return Текущая сессия
     */
    @Override
    public DBSession getCurrentSession() {
        checkConnection();
        return dbSession;
    }

    /**
     * Проверка соединения
     */
    private void checkConnection(){
        try{
            if (null == connection || !connection.isValid(TIMEOUT_IN_SECONDS))
                throw new SessionManagerException("Connection is invalid");
        } catch(SQLException ex){
            throw new SessionManagerException(ex);
        }
    }

}

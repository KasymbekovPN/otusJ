package ru.otus.kasymbekovPN.HW09.jdbc.sessionManager;

import ru.otus.kasymbekovPN.HW09.api.sessionManager.DataBaseSession;
import ru.otus.kasymbekovPN.HW09.api.sessionManager.SessionManager;
import ru.otus.kasymbekovPN.HW09.api.sessionManager.SessionManagerException;
import ru.otus.kasymbekovPN.HW09.h2.DataSourceH2Impl;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class SessionManagerJDBCImpl implements SessionManager {
    private static final int TIMEOUT_IN_SECONDS = 5;

    private final DataSource dataSource;
    private Connection connection;
    private DataBaseSession dbSession;

    public SessionManagerJDBCImpl(DataSource dataSource){
        if (null == dataSource)
            throw new SessionManagerException("DataSource is null");
        this.dataSource = dataSource;
    }

    @Override
    public void beginSession() {
        try{
            this.connection = dataSource.getConnection();

            //< здесь не должны знать о DataBaseSessionJDBCImpl
            dbSession = new DataBaseSessionJDBCImpl(connection);
            //<
//            this.dbSession = dbSession;
        } catch (SQLException ex){
            throw new SessionManagerException(ex);
        }
    }

    @Override
    public void commitSession() {
        checkConnection();
        try{
            connection.commit();
        } catch (SQLException ex){
            throw new SessionManagerException(ex);
        }
    }

    @Override
    public void rollbackSession() {
        checkConnection();
        try{
            connection.rollback();
        } catch (SQLException ex){
            throw new SessionManagerException(ex);
        }
    }

    @Override
    public void close() {
        checkConnection();
        try{
            connection.close();
        } catch (SQLException ex){
            throw new SessionManagerException(ex);
        }
    }

    @Override
    public DataBaseSession getCurrentSession() {
        checkConnection();
        return dbSession;
    }

    private void checkConnection(){
        try{
            if (null == connection || !connection.isValid(TIMEOUT_IN_SECONDS))
                throw new SessionManagerException("Connection is invalid");
        } catch(SQLException ex){
            throw new SessionManagerException(ex);
        }
    }
}

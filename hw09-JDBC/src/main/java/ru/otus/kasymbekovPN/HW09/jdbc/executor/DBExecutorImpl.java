package ru.otus.kasymbekovPN.HW09.jdbc.executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.kasymbekovPN.HW09.PreparedInstanceData;
import ru.otus.kasymbekovPN.HW09.PreparedInstanceDataImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

public class DBExecutorImpl<T> implements DBExecutor<T> {

    private static final Logger logger = LoggerFactory.getLogger(DBExecutorImpl.class);

    static private Map<Class, PreparedInstanceData> existingMap = new HashMap<>();
    //<
//    static private Set<Class> tableExistingSet = new HashSet<>();
    static private Set<Class> notContainIdSet = new HashSet<>();

    @Override
    public void createRecord(T instance, Connection connection) throws IllegalAccessException {
//        System.out.println("createRecord");
//        logger.info("{}", this.getClass());
//        var type = instance.getClass();
//        System.out.println(type);
        //<
        var type = instance.getClass();
        if (!notContainIdSet.contains(type)){

            if (!existingMap.containsKey(type)){
                PreparedInstanceData preparedInstanceData = new PreparedInstanceDataImpl(instance);
                if (preparedInstanceData.isValidType()){
                    existingMap.put(type, preparedInstanceData);
                } else {
                    notContainIdSet.add(type);
                }
            }

            if (existingMap.containsKey(type)){
                //< !!! create record
            } else {
                //< ??? action ???
            }

        } else {
            //< ??? action ???
        }
    }

    @Override
    public void updateRecord(T instance, Connection connection) {

    }

    @Override
    public T loadRecord(long id, Class clazz, Connection connection) {
        return null;
    }

    private void createTable(T instance, Connection connection){
        Optional<String> optUrl = generateCreateTableUrl(instance);
        optUrl.ifPresentOrElse((url) -> {
            try(connection;
                PreparedStatement pst = connection.prepareStatement(url)){

                pst.executeUpdate();
            } catch (SQLException ex) {
                logger.error(ex.getMessage());
            }
            logger.info("table created");;
        },
        ()->{logger.error("table wasn't create");});
    }

    private Optional<String> generateCreateTableUrl(T instance){
        return Optional.empty();
    }



//    private void createTable(DataSource dataSource) throws SQLException {
//        try(Connection connection = dataSource.getConnection();
//            PreparedStatement pst = connection.prepareStatement("create table user(id long auto_increment, name varchar(50))")){
//            pst.executeUpdate();
//        }
//        logger.info("table created");
//    }
}

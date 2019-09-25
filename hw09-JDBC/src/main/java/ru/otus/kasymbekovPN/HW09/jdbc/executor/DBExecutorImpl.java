package ru.otus.kasymbekovPN.HW09.jdbc.executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.kasymbekovPN.HW09.Pair;
import ru.otus.kasymbekovPN.HW09.PreparedInstanceData;
import ru.otus.kasymbekovPN.HW09.PreparedInstanceDataImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

public class DBExecutorImpl<T> implements DBExecutor<T> {

    private static final Logger logger = LoggerFactory.getLogger(DBExecutorImpl.class);

    static private Map<Class, PreparedInstanceData> existingMap = new HashMap<>();
    static private Set<Class> notContainIdSet = new HashSet<>();

    @Override
    public void createRecord(T instance, Connection connection) throws IllegalAccessException, SQLException, NoSuchFieldException {
        boolean created = checkTableExisting(instance, connection, true);
        if (created){
            PreparedInstanceData preparedInstanceData = existingMap.get(instance.getClass());

            Pair<String, List<Object>> pair = preparedInstanceData.getInsertUrl(instance);
            System.out.println(pair.getFirst());
            System.out.println(pair.getSecond());
        }
    }

    @Override
    public void updateRecord(T instance, Connection connection) {

    }

    @Override
    public T loadRecord(long id, Class clazz, Connection connection) {
        return null;
    }

    private boolean checkTableExisting(Object instance, Connection connection, boolean create) throws IllegalAccessException, SQLException {
        Class<?> type = instance.getClass();
        if (!notContainIdSet.contains(type)) {
            if (!existingMap.containsKey(type)){
                PreparedInstanceData preparedInstanceData = new PreparedInstanceDataImpl(instance);
                if (preparedInstanceData.isValid()){

                    if (create) {
                        existingMap.put(type, preparedInstanceData);
                        String sql = preparedInstanceData.getCreateTableUrl();
                        try (PreparedStatement pst = connection.prepareStatement(sql)) {
                            pst.executeUpdate();
                            logger.info("table created : {}", sql);
                            return true;
                        }
                    } else {
                        logger.info("table wasn't create");
                        return false;
                    }

                } else {
                    notContainIdSet.add(type);
                    logger.info("not contain annotation ID");
                    return false;
                }
            } else {
                logger.info("contain annotation Id");
                return true;
            }
        } else {
            logger.info("not contain annotation Id");
            return false;
        }
    }

    //<
//    private void createTable(T instance, Connection connection){
//        Optional<String> optUrl = generateCreateTableUrl(instance);
//        optUrl.ifPresentOrElse((url) -> {
//            try(connection;
//                PreparedStatement pst = connection.prepareStatement(url)){
//
//                pst.executeUpdate();
//            } catch (SQLException ex) {
//                logger.error(ex.getMessage());
//            }
//            logger.info("table created");;
//        },
//        ()->{logger.error("table wasn't create");});
//    }

    //<
//    private Optional<String> generateCreateTableUrl(T instance){
//        return Optional.empty();
//    }



//    private void createTable(DataSource dataSource) throws SQLException {
//        try(Connection connection = dataSource.getConnection();
//            PreparedStatement pst = connection.prepareStatement("create table user(id long auto_increment, name varchar(50))")){
//            pst.executeUpdate();
//        }
//        logger.info("table created");
//    }
}

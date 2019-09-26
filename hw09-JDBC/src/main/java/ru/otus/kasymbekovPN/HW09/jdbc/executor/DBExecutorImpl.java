package ru.otus.kasymbekovPN.HW09.jdbc.executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.kasymbekovPN.HW09.Pair;
import ru.otus.kasymbekovPN.HW09.PreparedInstanceData;
import ru.otus.kasymbekovPN.HW09.PreparedInstanceDataImpl;
import ru.otus.kasymbekovPN.HW09.Trio;

import java.sql.*;
import java.util.*;

public class DBExecutorImpl<T> implements DBExecutor<T> {

    private static final Logger logger = LoggerFactory.getLogger(DBExecutorImpl.class);

    static private Map<Class, PreparedInstanceData> existingMap = new HashMap<>();
    static private Set<Class> notContainIdSet = new HashSet<>();

    @Override
    public void createRecord(T instance, Connection connection) throws IllegalAccessException, SQLException, NoSuchFieldException {
//        boolean created = checkTableExisting(instance, connection, true);
        if (checkTableExisting(instance, connection, true)){
            PreparedInstanceData preparedInstanceData = existingMap.get(instance.getClass());

            preparedInstanceData.setInstance(instance);
            final Trio<String, List<Object>, List<String>> trio = preparedInstanceData.getInsertUrl();

            String sql = trio.getFirst();
            List<Object> values = trio.getSecond();
            List<String> names = trio.getThird();

            try(PreparedStatement pst = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
                preparedInstanceData.fillPst(pst, values, names);
                pst.executeUpdate();

                try(ResultSet rs = pst.getGeneratedKeys()){
                    preparedInstanceData.setKeyField(rs);
                }
            }
        }
    }

    @Override
    public void updateRecord(T instance, Connection connection) {

    }

    @Override
    public Optional<T> loadRecord(long id, T dummy, Connection connection) throws SQLException, IllegalAccessException, NoSuchFieldException {

        if (checkTableExisting(dummy, connection, false)){

            PreparedInstanceData preparedInstanceData = existingMap.get(dummy.getClass());
            preparedInstanceData.setInstance(dummy);
            Pair<String, List<String>> selectSql = preparedInstanceData.getSelectSql();

            String sql = selectSql.getFirst();
            List<String> names = selectSql.getSecond();

            try(PreparedStatement pst = connection.prepareStatement(sql)){
                preparedInstanceData.fillPst(pst, List.of((Object)id), List.of("id"));
                try(ResultSet rs = pst.executeQuery()){

                    return (Optional<T>) Optional.of(preparedInstanceData.fillInstance(rs, names));
                }
            }
        } else {
            return Optional.empty();
        }
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
}

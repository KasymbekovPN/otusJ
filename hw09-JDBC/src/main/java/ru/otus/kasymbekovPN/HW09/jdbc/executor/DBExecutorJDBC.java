package ru.otus.kasymbekovPN.HW09.jdbc.executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.kasymbekovPN.HW09.query.PreparedInstanceData;
import ru.otus.kasymbekovPN.HW09.query.PreparedInstanceDataImpl;
import ru.otus.kasymbekovPN.HW09.utils.Trio;
import ru.otus.kasymbekovPN.HW09.api.executor.DBExecutor;

import java.sql.*;
import java.util.*;

/**
 * Реализация экзекутора для работы с JDBC
 * @param <T> Класс, с которым работает экзекутор
 */
public class DBExecutorJDBC<T> implements DBExecutor<T> {

    private static final Logger logger = LoggerFactory.getLogger(DBExecutorJDBC.class);

    /**
     * Содержит данные о существующих таблицах
     */
    static private Map<Class, PreparedInstanceData> existingMap = new HashMap<>();

    /**
     * Набор класс, неимеющих таблицы, вседствии отсутсвия в них аннотации @Id
     */
    static private Set<Class> notContainIdSet = new HashSet<>();

    /**
     * Запись в БД
     * @param instance Записываемый объект
     * @param connection Соединение
     * @return Записанный объект
     */
    @Override
    public Optional<T> createRecord(T instance, Connection connection) throws IllegalAccessException, SQLException, NoSuchFieldException {
        if (checkTableExisting(instance, connection, true)){
            PreparedInstanceData preparedInstanceData = existingMap.get(instance.getClass());

            preparedInstanceData.setInstance(instance);
            Trio<String, List<Object>, List<String>> trio = preparedInstanceData.getInsertQuery();

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

            return Optional.of(instance);
        }

        return Optional.empty();
    }

    /**
     * Обновление данных в БД, соответствующих объекту
     * @param instance объект
     * @param connection соединение
     * @return Объект
     */
    @Override
    public Optional<T> updateRecord(T instance, Connection connection) throws SQLException, IllegalAccessException, NoSuchFieldException {
        if (checkTableExisting(instance, connection, false)){
            PreparedInstanceData preparedInstanceData = existingMap.get(instance.getClass());
            preparedInstanceData.setInstance(instance);

            Trio<String, List<Object>, List<String>> trio = preparedInstanceData.getUpdateQuery();
            String sql = trio.getFirst();
            List<Object> values = trio.getSecond();
            List<String> names = trio.getThird();

            try(PreparedStatement pst = connection.prepareStatement(sql)){
                preparedInstanceData.fillPst(pst, values, names);
                pst.executeUpdate();
            }

            return Optional.of(instance);
        } else {
            logger.error("Table doesn't exist");
            return Optional.empty();
        }
    }

    /**
     * Выгрузка данных из БД по ключу
     * @param id значение ключа
     * @param dummy цель для выгрузки
     * @param connection соединение
     * @return объект с выгруженными данными
     */
    @Override
    public Optional<T> loadRecord(long id, T dummy, Connection connection) throws SQLException, IllegalAccessException, NoSuchFieldException {

        if (checkTableExisting(dummy, connection, false)){
            PreparedInstanceData preparedInstanceData = existingMap.get(dummy.getClass());
            preparedInstanceData.setInstance(dummy);
            Trio<String, String, List<String>> trio = preparedInstanceData.getSelectQuery();

            String sql = trio.getFirst();
            String idName = trio.getSecond();
            List<String> names = trio.getThird();

            try(PreparedStatement pst = connection.prepareStatement(sql)){
                preparedInstanceData.fillPst(pst, List.of((Object)id), List.of(idName));
                try(ResultSet rs = pst.executeQuery()){

                    return (Optional<T>) Optional.of(preparedInstanceData.fillInstance(rs, names));
                }
            }
        } else {
            return Optional.empty();
        }
    }

    /**
     * Проверка наличия необходимой таблизы
     * @param instance инстанс, по которому определяется нужная таблица
     * @param connection соединение
     * @param create true - (если таблица несуществет, то её нужно создать), false- (создавать не нужно)
     * @return успешность проверки
     */
    private boolean checkTableExisting(Object instance, Connection connection, boolean create) throws IllegalAccessException, SQLException {
        Class<?> type = instance.getClass();
        if (!notContainIdSet.contains(type)) {
            if (!existingMap.containsKey(type)){
                PreparedInstanceData preparedInstanceData = new PreparedInstanceDataImpl(instance);
                if (preparedInstanceData.isValid()){
                    if (create) {
                        existingMap.put(type, preparedInstanceData);
                        String sql = preparedInstanceData.getCreateTableQuery();
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

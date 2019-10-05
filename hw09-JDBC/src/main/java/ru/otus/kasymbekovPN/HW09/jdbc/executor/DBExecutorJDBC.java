package ru.otus.kasymbekovPN.HW09.jdbc.executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.kasymbekovPN.HW09.query.ExtractValuesHelperImpl;
import ru.otus.kasymbekovPN.HW09.query.PreparedInstanceData;
import ru.otus.kasymbekovPN.HW09.query.PreparedInstanceDataImpl;
import ru.otus.kasymbekovPN.HW09.api.executor.DBExecutor;
import ru.otus.kasymbekovPN.HW09.query.ResultSetHelperImpl;

import java.lang.reflect.InvocationTargetException;
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
    public Optional<T> createRecord(T instance, Connection connection) throws IllegalAccessException, SQLException, NoSuchFieldException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        if (checkTableExisting(instance.getClass(), connection, true)){
            PreparedInstanceData preparedInstanceData = existingMap.get(instance.getClass());

            String query = preparedInstanceData.getInsertQuery();
            try(PreparedStatement pst = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
                List<Object> values = new ExtractValuesHelperImpl().extractOtherValues(
                        instance,
                        preparedInstanceData.getOtherFields()
                );

                for(int i = 0; i < values.size(); i++){
                    pst.setObject(i+1, values.get(i));
                }
                pst.executeUpdate();

                try(ResultSet rs = pst.getGeneratedKeys()){
                    new ResultSetHelperImpl().setKeyField(
                            rs,
                            instance,
                            preparedInstanceData.getKeyField()
                    );
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
    public Optional<T> updateRecord(T instance, Connection connection) throws SQLException, IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        if (checkTableExisting(instance.getClass(), connection, false)){
            PreparedInstanceData preparedInstanceData = existingMap.get(instance.getClass());

            var helper = new ExtractValuesHelperImpl();
            List<Object> values = helper.extractOtherValues(instance, preparedInstanceData.getOtherFields());
            values.add(helper.extractKeyValue(instance, preparedInstanceData.getKeyField()));

            String query = preparedInstanceData.getUpdateQuery();
            try(PreparedStatement pst = connection.prepareStatement(query)){
                for(int i = 0; i < values.size(); i++){
                    pst.setObject(i+1, values.get(i));
                }
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
     * @param clazz класс для выгрузки
     * @param connection соединение
     * @return объект с выгруженными данными
     */
    @Override
    public Optional<T> loadRecord(long id, Class clazz, Connection connection) throws SQLException, IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException {

        if (checkTableExisting(clazz, connection, false)){
            PreparedInstanceData preparedInstanceData = existingMap.get(clazz);

            String query = preparedInstanceData.getSelectQuery();

            try(PreparedStatement pst = connection.prepareStatement(query)){
                pst.setObject(1, id);
                try(ResultSet rs = pst.executeQuery()){
                    return (Optional<T>)Optional.of(
                            new ResultSetHelperImpl().makeInstance(
                                    rs,
                                    clazz,
                                    preparedInstanceData.getKeyField(),
                                    preparedInstanceData.getOtherFields()
                            )
                    );
                }
            }
        } else {
            return Optional.empty();
        }
    }

    /**
     * Проверка наличия необходимой таблизы
     * @param clazz класс, по которому определяется нужная таблица
     * @param connection соединение
     * @param create true - (если таблица несуществет, то её нужно создать), false- (создавать не нужно)
     * @return успешность проверки
     */
    private boolean checkTableExisting(Class clazz, Connection connection, boolean create) throws IllegalAccessException, SQLException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        if (!notContainIdSet.contains(clazz)) {
            if (!existingMap.containsKey(clazz)){
                PreparedInstanceData preparedInstanceData = new PreparedInstanceDataImpl(clazz);
                if (preparedInstanceData.isValid()){
                    if (create) {
                        existingMap.put(clazz, preparedInstanceData);
                        String query = preparedInstanceData.getCreateTableQuery();
                        try (PreparedStatement pst = connection.prepareStatement(query)) {
                            pst.executeUpdate();
                            logger.info("table created : {}", query);
                            return true;
                        }
                    } else {
                        logger.info("table wasn't create");
                        return false;
                    }

                } else {
                    notContainIdSet.add(clazz);
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

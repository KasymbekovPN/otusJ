package ru.otus.kasymbekovPN.HW09.query;

import ru.otus.kasymbekovPN.HW09.annotation.Id;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Класса, реализующий подготовку данных для запросов в БД
 */
public class PreparedInstanceDataImpl implements PreparedInstanceData {

    /**
     * Препарируемый класс
     */
    private Class clazz;

    /**
     * Флаг валидности
     */
    private boolean isValid;

    /**
     * Ключевое поле класса
     */
    private Field keyField;

    /**
     * Неключевые поля класса
     */
    private List<Field> otherField;

    /**
     * Имя таблицы, соответсвтвующей классу объекта instance
     */
    private String tableName;

    /**
     * Запрос для создания таблицы
     */
    private String createTableQuery;

    /**
     * Запрос для вставку в таблицу
     */
    private String insertQuery;

    /**
     * Запрос для обновления записи
     */
    private String updateQuery;

    /**
     * Запрос для получения записи.
     */
    private String selectQuery;

    /**
     * Контструктор
     * @param clazz препарируемый класс.
     */
    public PreparedInstanceDataImpl(Class clazz) throws IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        this.clazz = clazz;
        this.tableName = makeTableName(clazz);
        traverse(clazz);
    }

    /**
     * Создаем имя таблицы для конкретного класса
     * @param clazz класс
     * @return имя таблицы
     */
    private String makeTableName(Class clazz){
        return "t" + clazz.getSimpleName();
    }

    private void traverse(Class clazz) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        List<QueryChunk> keys = new ArrayList<>();
        List<QueryChunk> others = new ArrayList<>();

        otherField = new ArrayList<>();

        Field[] fields = clazz.getDeclaredFields();
        Object instance = clazz.getConstructor().newInstance();

        for (Field field : fields) {
            field.setAccessible(true);
            if (Modifier.isStatic(field.getModifiers()))
                continue;

            if (field.isAnnotationPresent(Id.class)){
                keys.add(new QueryChunkImpl(field.getName(), field.get(instance)));
                keyField = field;
            } else {
                others.add(new QueryChunkImpl(field.getName(), field.get(instance)));
                otherField.add(field);
            }
        }

        isValid = 1 == keys.size();
        if (isValid){
            QueryChunk key = keys.get(0);

            StringBuilder createSb = new StringBuilder("CREATE TABLE ")
                    .append(tableName)
                    .append("(")
                    .append(key.getName())
                    .append(" ")
                    .append(key.getType())
                    .append(" NOT NULL AUTO_INCREMENT");

            StringBuilder insertFirstSb = new StringBuilder("INSERT INTO ").append(tableName).append("(");
            StringBuilder insertSecondSb = new StringBuilder(" VALUES ").append("(");

            StringBuilder selectSb = new StringBuilder("SELECT ").append(key.getName());

            StringBuilder updateSb = new StringBuilder("UPDATE ")
                    .append(tableName)
                    .append(" SET ");

            String delimiter = "";
            for (QueryChunk other : others) {
                createSb.append(", ")
                        .append(other.getName())
                        .append(" ")
                        .append(other.getType());

                insertFirstSb.append(delimiter).append(other.getName());
                insertSecondSb.append(delimiter).append("?");

                selectSb.append(", ").append(other.getName());

                updateSb.append(delimiter).append(other.getName()).append("=?");

                delimiter = ", ";
            }

            createSb.append(")");

            insertFirstSb.append(")").append(insertSecondSb).append(")");

            selectSb.append(" FROM ")
                    .append(tableName)
                    .append(" WHERE ")
                    .append(key.getName())
                    .append("=?");

            updateSb.append(" WHERE ").append(key.getName()).append("=?");

            createTableQuery = String.valueOf(createSb);
            insertQuery = String.valueOf(insertFirstSb);
            selectQuery = String.valueOf(selectSb);
            updateQuery = String.valueOf(updateSb);
        }
    }

    /**
     * Задаём значение ключевого поля инстанса
     * @param rs данные
     */
    @Override
    public void setKeyField(ResultSet rs, Object instance) throws SQLException, IllegalAccessException {
        rs.next();
        keyField.set(instance, rs.getObject(keyField.getName()));
    }

    /**
     * Проверяет является ли инстанс класса, реализубщего данный интерфейс, валидным
     * @return Результат проверки
     */
    @Override
    public boolean isValid() {
        return isValid;
    }

    /**
     * Геттер запроса для создания таблицы
     * @return Запрос
     */
    @Override
    public String getCreateTableQuery() {
        return createTableQuery;
    }

    /**
     * Гетер данных для выполнения вставки данных в таблицу
     * @return Данные для вставки
     */
    @Override
    public String getInsertQuery() {
        return insertQuery;
    }

    /**
     * Геттер данных для обноваления данных в таблице
     * @return Данные для обновления
     */
    @Override
    public String getUpdateQuery() {
        return updateQuery;
    }

    /**
     * Геттер данных для выборки
     * @return Данные для выборки
     */
    @Override
    public String getSelectQuery() {
        return selectQuery;
    }

    /**
     * Получаем значения неключевых полей инстанса
     * @param instance инстанс
     * @return Список значений
     */
    @Override
    public List<Object> extractValues(Object instance) throws IllegalAccessException {
        List<Object> values = new ArrayList<>();
        if (clazz.equals(instance.getClass())){
            for(Field field : otherField){
                field.setAccessible(true);
                if (Modifier.isStatic(field.getModifiers()))
                    continue;

                if (!field.isAnnotationPresent(Id.class)){
                    values.add(field.get(instance));
                }
            }
        }

        return values;
    }

    /**
     * Получаем значение ключевого поля инстанста
     * @param instance инстанс
     * @return Значение ключевого поля инстанса
     */
    @Override
    public Object extractKey(Object instance) throws IllegalAccessException {
        if (clazz.equals(instance.getClass()))
        {
            return keyField.get(instance);
        }
        return null;
    }

    /**
     * Заполняет инстанс, полученными из БД данными
     * @param rs полученные данные
     * @param clazz заполняемый класс
     * @return Модернизированый инстанс
     */
    @Override
    public Object fillInstance(ResultSet rs, Class clazz) throws SQLException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {

        Object instance = clazz.getConstructor().newInstance();
        rs.next();
        int index = 1;
        keyField.set(instance, rs.getObject(index++));
        for (Field field : otherField) {
            field.set(instance, rs.getObject(index++));
        }
        return instance;
    }
}

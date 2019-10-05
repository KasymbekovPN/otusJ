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
    private List<Field> otherFields;

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

    /**
     * Обход полей класса с  последующим созданием запросов
     * для работы с БД
     * @param clazz класс
     */
    private void traverse(Class clazz) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        List<QueryChunk> keys = new ArrayList<>();
        List<QueryChunk> others = new ArrayList<>();

        otherFields = new ArrayList<>();

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
                otherFields.add(field);
            }
        }

        isValid = 1 == keys.size();
        if (isValid){
            createTableQuery = makeCreateTableQuery(keys.get(0), others);
            insertQuery = makeInsertQuery(others);
            selectQuery = makeSelectQuery(keys.get(0), others);
            updateQuery = makeUpdateQuery(keys.get(0), others);
        }
    }

    /**
     * Создание запроса для создания таблицы
     * @param key ключевое поле
     * @param others прочие поля
     * @return запрос
     */
    private String makeCreateTableQuery(QueryChunk key, List<QueryChunk> others){
        StringBuilder createSb = new StringBuilder("CREATE TABLE ")
                .append(tableName)
                .append("(")
                .append(key.getName())
                .append(" ")
                .append(key.getType())
                .append(" NOT NULL AUTO_INCREMENT");

        for (QueryChunk other : others) {
            createSb.append(", ")
                    .append(other.getName())
                    .append(" ")
                    .append(other.getType());
        }

        createSb.append(")");

        return String.valueOf(createSb);
    }

    /**
     * Создание запроса для вставки записи
     * @param others Поля класса
     * @return Запрос
     */
    private String makeInsertQuery(List<QueryChunk> others){
        StringBuilder insertFirstSb = new StringBuilder("INSERT INTO ").append(tableName).append("(");
        StringBuilder insertSecondSb = new StringBuilder(" VALUES ").append("(");

        String delimiter = "";
        for (QueryChunk other : others) {
            insertFirstSb.append(delimiter).append(other.getName());
            insertSecondSb.append(delimiter).append("?");
            delimiter = ", ";
        }

        insertFirstSb.append(")").append(insertSecondSb).append(")");

        return String.valueOf(insertFirstSb);
    }

    /**
     * Создание запроса для выборки
     * @param key Ключевое поле
     * @param others Прочие поля
     * @return Запрос
     */
    private String makeSelectQuery(QueryChunk key, List<QueryChunk> others){
        StringBuilder selectSb = new StringBuilder("SELECT ").append(key.getName());
        for (QueryChunk other : others) {
            selectSb.append(", ").append(other.getName());
        }

        selectSb.append(" FROM ")
                .append(tableName)
                .append(" WHERE ")
                .append(key.getName())
                .append("=?");

        return String.valueOf(selectSb);
    }

    /**
     * Создание запроса для обновления
     * @param key Ключевое поле
     * @param others Прочие поля
     * @return Запрос
     */
    private String makeUpdateQuery(QueryChunk key, List<QueryChunk> others){
        StringBuilder updateSb = new StringBuilder("UPDATE ")
                .append(tableName)
                .append(" SET ");

        String delimiter = "";
        for (QueryChunk other : others) {
            updateSb.append(delimiter).append(other.getName()).append("=?");
            delimiter = ", ";
        }

        updateSb.append(" WHERE ").append(key.getName()).append("=?");

        return String.valueOf(updateSb);
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
     * Геттер ключевого поля
     * @return Ключевое поле
     */
    @Override
    public Field getKeyField() {
        return keyField;
    }

    /**
     * Геттер неключевых полей
     * @return Неключевые поля
     */
    @Override
    public List<Field> getOtherFields() {
        return otherFields;
    }
}

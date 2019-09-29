package ru.otus.kasymbekovPN.HW09.query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.kasymbekovPN.HW09.annotation.Id;
import ru.otus.kasymbekovPN.HW09.utils.Trio;
import ru.otus.kasymbekovPN.HW09.visitor.PrimitiveVE;
import ru.otus.kasymbekovPN.HW09.visitor.StringVE;
import ru.otus.kasymbekovPN.HW09.visitor.VisitorImpl;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Класса, реализующий подготовку данных для запросов в БД
 */
public class PreparedInstanceDataImpl implements PreparedInstanceData {

    private static Logger logger = LoggerFactory.getLogger(PreparedInstanceDataImpl.class);

    /**
     * Препарируемый инстанс
     */
    private Object instance;

    /**
     * Флаг валидности
     */
    private boolean isValid;

    /**
     * Данные о поле-ключе класса объекта instance.
     */
    private QueryChunk keyField;

    /**
     * Данные о неключевых полях класса объекта instance.
     */
    private List<QueryChunk> queryFields;

    /**
     * Имя таблицы, соответсвтвующей классу объекта instance
     */
    private String tableName;

    /**
     * Контструктор
     * @param instance инстанс, препарируемого класса.
     */
    public PreparedInstanceDataImpl(Object instance) throws IllegalAccessException {
        VisitorImpl visitor = traverse(instance);
        this.instance = instance;
        this.tableName = makeTableName(instance);
        this.isValid = visitor.isValid();
        if (this.isValid){
            this.keyField = visitor.getKeyField();
            this.queryFields = visitor.getFields();
        }
    }

    /**
     * Создаем имя таблицы для конкретного класса
     * @param instance инстанс класса
     * @return имя таблицы
     */
    private String makeTableName(Object instance){
        return "t" + instance.getClass().getSimpleName();
    }

    /**
     * Обход инстанса класса для получения даннх, необходимых для работы с БД
     * @param instance инстанс каласса
     * @return Визитор с данными.
     */
    private VisitorImpl traverse(Object instance) throws IllegalAccessException {
        VisitorImpl visitor = new VisitorImpl(Id.class);
        Field[] fields = instance.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);

            if (Modifier.isStatic(field.getModifiers()))
                continue;

            var fieldType = field.getType();
            if (fieldType.isPrimitive()){
                new PrimitiveVE(field, field.get(instance)).accept(visitor);
            } else if (String.class.isAssignableFrom(fieldType)){
                new StringVE(field, field.get(instance)).accept(visitor);
            }
        }

        return visitor;
    }

    /**
     * Геттер запроса для создания таблицы
     * @return Запрос
     */
    @Override
    public String getCreateTableQuery() {
        return makeCreateQuery();
    }

    /**
     * Гетер данных для выполнения вставки данных в таблицу
     * @return Данные для вставки
     */
    @Override
    public Trio<String, List<Object>, List<String>> getInsertQuery() throws NoSuchFieldException, IllegalAccessException {
        List<Object> values = new ArrayList<>();
        List<String> names = new ArrayList<>();

        for (QueryChunk queryField : queryFields) {
            String name = queryField.getName();
            Field declaredField = instance.getClass().getDeclaredField(name);
            declaredField.setAccessible(true);
            values.add(declaredField.get(instance));
            names.add(name);
        }

        return new Trio<>(makeInsertQuery(names), values, names);
    }

    /**
     * Геттер данных для обноваления данных в таблице
     * @return Данные для обновления
     */
    @Override
    public Trio<String, List<Object>, List<String>> getUpdateQuery() throws NoSuchFieldException, IllegalAccessException {
        List<String> names = new ArrayList<>();
        List<Object> values = new ArrayList<>();
        for (QueryChunk queryField : queryFields) {
            String name = queryField.getName();
            names.add(queryField.getName());
        }

        String keyFieldName = keyField.getName();
        String query = makeUpdateQuery(keyFieldName, names);
        names.add(keyFieldName);

        for (String name : names) {
            Field field = instance.getClass().getDeclaredField(name);
            field.setAccessible(true);
            values.add(field.get(instance));
        }

        return new Trio<>(query, values, names);
    }

    /**
     * Геттер данных для выборки
     * @return Данные для выборки
     */
    @Override
    public Trio<String, String, List<String>> getSelectQuery() {
        List<String> names = new ArrayList<>();
        String keyFieldName = keyField.getName();

        names.add(keyFieldName);
        for (QueryChunk queryField : queryFields)
            names.add(queryField.getName());

        return new Trio<>(makeSelectQuery(keyFieldName, names), keyFieldName, names);
    }

    /**
     * Заполняем pst переданными значениями
     * @param pst pst
     * @param values значения
     * @param names имена значений
     */
    @Override
    public void fillPst(PreparedStatement pst, List<Object> values, List<String> names) throws SQLException {
        for(int i = 0; i < names.size(); i++){
            String name = names.get(i);
            Object value = values.get(i);

            if (keyField.getName().equals(name)){
                keyField.fillPst(pst, value, i + 1);
            } else {
                for (QueryChunk queryField : queryFields) {
                    if (queryField.getName().equals(name)){
                        queryField.fillPst(pst, value, i + 1);
                        break;
                    }
                }
            }
        }
    }

    /**
     * Заполняет инстанс, полученными из БД данными
     * @param rs полученные данные
     * @param names имена данных
     * @return Модернизированый инстанс
     */
    @Override
    public Object fillInstance(ResultSet rs, List<String> names) throws SQLException, NoSuchFieldException, IllegalAccessException {
        rs.next();

        for (String name : names) {
            if (keyField.getName().equals(name)){
                keyField.setField(rs, instance.getClass().getDeclaredField(name), instance);
            } else {
                for (ru.otus.kasymbekovPN.HW09.query.QueryChunk queryField : queryFields) {
                    if (queryField.getName().equals(name)){
                        queryField.setField(rs, instance.getClass().getDeclaredField(name), instance);
                        break;
                    }
                }
            }
        }

        return instance;
    }

    /**
     * Задаём значение ключевого поля инстанса
     * @param rs данные
     */
    @Override
    public void setKeyField(ResultSet rs) throws NoSuchFieldException, SQLException, IllegalAccessException {
        rs.next();
        keyField.setField(rs, instance.getClass().getDeclaredField(keyField.getName()), instance);
    }

    /**
     * Задаем инстанс
     * @param instance инстанс
     */
    @Override
    public void setInstance(Object instance) {
        if (this.instance.getClass().equals(instance.getClass())){
            this.instance = instance;
        } else {
            logger.error("setInstance : wrong type");
        }
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
     * генерация запроса для создания таблицы
     * @return Запрос
     */
    private String makeCreateQuery(){
        StringBuilder sb = new StringBuilder("CREATE TABLE ")
                .append(tableName)
                .append("(")
                .append(keyField.getCreateChunk());
        for (QueryChunk queryField : queryFields) {
            sb.append(", ").append(queryField.getCreateChunk());
        }
        sb.append(")");

        return String.valueOf(sb);
    }

    /**
     * Генерация запроса для вставки
     * @param names имена вставляемых столбцов
     * @return запрос
     */
    private String makeInsertQuery(List<String> names){
        StringBuilder namesLine = new StringBuilder("(");
        StringBuilder questionMarksLine = new StringBuilder("(");
        String delimiter = "";
        for (String name : names) {
            namesLine.append(delimiter).append(name);
            questionMarksLine.append(delimiter).append("?");
            delimiter = ",";
        }

        return "INSERT INTO " +
                tableName +
                namesLine +
                ")" +
                " VALUES " +
                questionMarksLine +
                ")";
    }

    /**
     * Генерация запроса для выборки
     * @param key ключ
     * @param names запрашиваемые столбцы
     * @return Запрос
     */
    private String makeSelectQuery(String key, List<String> names){
        StringBuilder sb = new StringBuilder("SELECT");
        String delimiter = " ";
        for (String name : names) {
            sb.append(delimiter).append(name);
            delimiter = ", ";
        }
        sb.append(" FROM ")
                .append(tableName)
                .append(" WHERE ")
                .append(key)
                .append("=?");

        return String.valueOf(sb);
    }

    /**
     * Генерация запроса для обновления
     * @param key ключ
     * @param names обновляемые столбцы
     * @return Запрос
     */
    private String makeUpdateQuery(String key, List<String> names){
        StringBuilder sb = new StringBuilder("UPDATE ")
                .append(tableName)
                .append(" SET");
        String delimiter = " ";
        for (String name : names) {
            sb.append(delimiter)
                    .append(name)
                    .append("=?");
            delimiter = ", ";
        }
        sb.append(" WHERE ")
                .append(key)
                .append("=?");

        return String.valueOf(sb);
    }

}

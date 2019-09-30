package ru.otus.kasymbekovPN.HW09.query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Реализация класса, работающего с частью запроса.
 */
public class QueryChunkImpl implements QueryChunk {

    private static Logger logger = LoggerFactory.getLogger(QueryChunkImpl.class);

    /**
     * Имя столбка
     */
    private String name;

    /**
     * Тип столбка
     */
    private String type;

    /**
     * Является ли столбец ключевым
     */
    private boolean isKey;

    /**
     * Проверка типа
     * @param type тип
     * @return результат проверки
     */
    private static boolean isVARCHAR(String type){
        return type.split("\\(")[0].equals("VARCHAR");
    }

    /**
     * генерация строкового представления типа
     * на основе полученного инстанса
     * @param instance инстанс
     * @return Строковое представление типа
     */
    private static String makeType(Object instance){
        String type = "";
        if (String.class.isAssignableFrom(instance.getClass()))
            type = "VARCHAR(255)";
        else if (Integer.class.isAssignableFrom(instance.getClass()))
            type = "INT";
        else if (Long.class.isAssignableFrom(instance.getClass()))
            type = "LONG";
        else if (Double.class.isAssignableFrom(instance.getClass()))
            type = "DOUBLE";
        else
            logger.error("wrong type");

        return type;
    }

    /**
     * Конструктор
     * @param name имя столбца
     * @param instance инстанс
     * @param isKey является ли столбец ключевым
     */
    public QueryChunkImpl(String name, Object instance, boolean isKey) {
        this.name = name;
        this.type = makeType(instance);
        this.isKey = isKey;
    }

    /**
     * Геттер имени столбца
     * @return имя столбца
     */
    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getType() {
        return type;
    }

    /**
     * Геттер строки, соответствующей имени, для состовления запроса для для
     * создания таблицы
     * @return Строка
     */
    @Override
    public String getCreateChunk() {
        StringBuilder sb = new StringBuilder(name).append(" ").append(type);
        if (isKey){
            sb.append(" NOT NULL AUTO_INCREMENT");
        }

        return String.valueOf(sb);
    }

    /**
     * Заполнение pst значением соответствующем имени
     * @param pst pst
     * @param value значение
     * @param index инстанс
     */
    @Override
    public void fillPst(PreparedStatement pst, Object value, int index) throws SQLException {
        if (isVARCHAR(type))
            pst.setString(index, String.valueOf(value));
        else if (type.equals("INT"))
            pst.setInt(index, (Integer)value);
        else if (type.equals("LONG"))
            pst.setLong(index, (Long)value);
        else if (type.equals("DOUBLE"))
            pst.setDouble(index, (Double)value);
        else
            logger.error("wrong type");
    }

    /**
     * Задание полю истанса значения из полученныз данных
     * @param rs полученные данные
     * @param field поле
     * @param instance инстанс
     */
    @Override
    public void setField(ResultSet rs, Field field, Object instance) throws SQLException, IllegalAccessException {
        field.setAccessible(true);
        if (isVARCHAR(type)){
            field.set(instance, rs.getString(name));
        } else if (type.equals("INT")){
            field.setInt(instance, rs.getInt(name));
        } else if (type.equals("LONG")){
            field.setLong(instance, rs.getLong(name));
        } else if (type.equals("DOUBLE")){
            field.setDouble(instance, rs.getDouble(name));
        } else {
            logger.error("setField : wrong type");
        }
    }
}

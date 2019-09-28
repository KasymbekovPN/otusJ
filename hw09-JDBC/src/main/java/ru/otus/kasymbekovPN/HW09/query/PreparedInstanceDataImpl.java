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

public class PreparedInstanceDataImpl implements PreparedInstanceData {

    private static Logger logger = LoggerFactory.getLogger(PreparedInstanceDataImpl.class);

    private Object instance;
    private boolean isValid;
    private QueryChunk keyField;
    private List<QueryChunk> queryFields;
    private String tableName;

    //< хэш мапы запросов select, update

//    private String createTableUrl;

    public PreparedInstanceDataImpl(Object instance) throws IllegalAccessException {
        this.instance = instance;

        //< !!! более элегантно, более полное имя !!!
        this.tableName = "t" + instance.getClass().getSimpleName();

        //< Здесь не должны знать о VisitorImpl
        VisitorImpl visitor = new VisitorImpl(Id.class);

        Field[] fields = this.instance.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);

            if (Modifier.isStatic(field.getModifiers()))
                continue;

            var fieldType = field.getType();
            if (fieldType.isPrimitive()){
                new PrimitiveVE(field, field.get(this.instance)).accept(visitor);
            } else if (String.class.isAssignableFrom(fieldType)){
                new StringVE(field, field.get(this.instance)).accept(visitor);
            }
        }

        isValid = visitor.isValid();
        if (isValid){
            keyField = visitor.getKeyField();
            queryFields = visitor.getFields();
        }
    }

    @Override
    public boolean isValid() {
        return isValid;
    }

    @Override
    public String getCreateTableUrl() {
        StringBuilder sb = new StringBuilder("CREATE TABLE ")
                .append(tableName)
                .append("(")
                .append(keyField.getQueryChunk());
        for (QueryChunk queryField : queryFields) {
            sb.append(", ")
                    .append(queryField.getQueryChunk());
        }
        sb.append(")");

        return sb.toString();
    }

    @Override
    public void setInstance(Object instance) {
        if (this.instance.getClass().equals(instance.getClass())){
            this.instance = instance;
        } else {
            //<
            logger.error("setInstance : wrong type");
        }
    }

    @Override
    public Trio<String, List<Object>, List<String>> getInsertUrl() throws NoSuchFieldException, IllegalAccessException {

        StringBuilder fieldNames = new StringBuilder("(");
        StringBuilder questionMarks = new StringBuilder("(");
        List<Object> values = new ArrayList<>();
        List<String> names = new ArrayList<>();

        //< change with delimiter
        boolean first = true;

        for (QueryChunk queryField : queryFields) {
            String name = queryField.getName();

            if (first){
                first = false;
            } else {
                fieldNames.append(",");
                questionMarks.append(",");
            }

            fieldNames.append(name);
            questionMarks.append("?");

            Field declaredField = instance.getClass().getDeclaredField(name);
            declaredField.setAccessible(true);
            values.add(declaredField.get(instance));
            names.add(name);
        }
        fieldNames.append(")");
        questionMarks.append(")");

        StringBuilder sql = new StringBuilder("INSERT INTO ")
                .append(tableName)
                .append(fieldNames)
                .append(" VALUES ")
                .append(questionMarks);

        return new Trio<>(String.valueOf(sql), values, names);
    }

    @Override
    public void fillPst(PreparedStatement pst, List<Object> values, List<String> names) throws SQLException {

        for(int i = 0; i < names.size(); i++){
            String name = names.get(i);
            Object value = values.get(i);

            String type = "";

            if (keyField.getName().equals(name)){
                type = keyField.getType();
            } else {
                for (QueryChunk queryField : queryFields) {
                    if (queryField.getName().equals(name)) {
                        type = queryField.getType();
                        break;
                    }
                }
            }

            if (!type.equals("")){
                if (isVARCHAR(type)){
                    pst.setString(i+1, String.valueOf(value));
                } else if (type.equals("INT")){
                    pst.setInt(i+1, (Integer)value);
                } else if (type.equals("LONG")){
                    pst.setLong(i+1, (Long)value);
                } else if (type.equals("DOUBLE")){
                    pst.setDouble(i+1, (Double)value);
                }
            } else {
                //<
                logger.error("empty type");
            }
        }
    }

    @Override
    public void setKeyField(ResultSet rs) throws NoSuchFieldException, SQLException, IllegalAccessException {
        String name = keyField.getName();
        String type = keyField.getType();
        Field field = instance.getClass().getDeclaredField(name);
        field.setAccessible(true);

        rs.next();
        if (type.equals("INT")){
            field.setInt(instance, rs.getInt(1));
        } else if(type.equals("LONG")){
            field.setLong(instance, rs.getLong(1));
        }
    }

    @Override
    public Trio<String, String, List<String>> getSelectSql() {

        StringBuilder sql = new StringBuilder("SELECT ");
        List<String> names = new ArrayList<>();

        String keyFieldName = keyField.getName();
        sql.append(keyFieldName);
        names.add(keyFieldName);

        for (QueryChunk queryField : queryFields) {
            String name = queryField.getName();
            sql.append(", ").append(name);
            names.add(name);
        }

        sql.append(" FROM ")
                .append(tableName)
                .append(" WHERE ")
                .append(keyFieldName)
                .append(" = ?");

        return new Trio<>(String.valueOf(sql), keyFieldName, names);
    }

    @Override
    public Object fillInstance(ResultSet rs, List<String> names) throws SQLException, NoSuchFieldException, IllegalAccessException {
        rs.next();

        for (String name : names) {
            Field field = instance.getClass().getDeclaredField(name);
            field.setAccessible(true);

            String type = "";
            if (keyField.getName().equals(name)){
                type = keyField.getType();
            } else {
                for (QueryChunk queryField : queryFields) {
                    if (queryField.getName().equals(name)){
                        type = queryField.getType();
                        break;
                    }
                }
            }

            if (isVARCHAR(type)){
                field.set(instance, rs.getString(name));
            } else if (type.equals("INT")){
                field.setInt(instance, rs.getInt(name));
            } else if (type.equals("LONG")){
                field.setLong(instance, rs.getLong(name));
            } else if (type.equals("DOUBLE")){
                field.setDouble(instance, rs.getDouble(name));
            } else {
                logger.error("fillInstance : wrong type");
            }
        }

        return instance;

    }

    @Override
    public Trio<String, List<Object>, List<String>> getUpdateSql() throws NoSuchFieldException, IllegalAccessException {
        StringBuilder sql = new StringBuilder("UPDATE ").append(tableName).append(" SET");
        List<String> names = new ArrayList<>();
        List<Object> values = new ArrayList<>();

        String delimiter = "";
        for (QueryChunk queryField : queryFields) {
            String name = queryField.getName();
            sql.append(delimiter).append(" ").append(name).append("=?");
            delimiter = ",";
            names.add(name);
        }
        sql.append(" WHERE ").append(keyField.getName()).append("=?");
        names.add(keyField.getName());

        for (String name : names) {
            Field field = instance.getClass().getDeclaredField(name);
            field.setAccessible(true);

            values.add(field.get(instance));
        }

//        return new Pair<>(String.valueOf(sql), values);
        //<
        return new Trio<>(String.valueOf(sql), values, names);
    }


    private boolean isVARCHAR(String line){
        return line.split("\\(")[0].equals("VARCHAR");
    }
}

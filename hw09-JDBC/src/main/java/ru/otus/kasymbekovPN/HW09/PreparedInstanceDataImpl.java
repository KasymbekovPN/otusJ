package ru.otus.kasymbekovPN.HW09;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.print.DocFlavor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public Pair<String, List<Object>> getInsertUrl(Object instance) throws NoSuchFieldException, IllegalAccessException {

        if (this.instance.getClass().equals(instance.getClass())){

            StringBuilder fieldNames = new StringBuilder("(");
            StringBuilder questionMarks = new StringBuilder("(");
            List<Object> values = new ArrayList<>();

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
            }
            fieldNames.append(")");
            questionMarks.append(")");

            StringBuilder sql = new StringBuilder("INSERT INTO ")
                    .append(tableName)
                    .append(fieldNames)
                    .append(" VALUES ")
                    .append(questionMarks);

            return new Pair<>(String.valueOf(sql), values);

        } else {
            logger.info("getSelectUrl wrong instance type");
            return null;
        }
    }
}

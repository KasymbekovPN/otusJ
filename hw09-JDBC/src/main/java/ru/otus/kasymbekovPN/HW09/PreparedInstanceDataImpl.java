package ru.otus.kasymbekovPN.HW09;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class PreparedInstanceDataImpl implements PreparedInstanceData {

    private static Logger logger = LoggerFactory.getLogger(PreparedInstanceDataImpl.class);

    private Object instance;
    private boolean isValidType;

    private String createTableUrl;

    public PreparedInstanceDataImpl(Object instance) throws IllegalAccessException {
        this.instance = instance;

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

    }

    @Override
    public boolean isValidType() {
        return isValidType;
    }

    @Override
    public String getCreateTableUrl() {
        return createTableUrl;
    }
}

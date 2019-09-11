package ru.otus.kasymbekovPN.HW08.javaObjectWriter;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class ObjectVisitedElement implements VisitedElement {

    private Field field;
    private Object instance;

    public ObjectVisitedElement(Field field, Object instance) {
        this.field = field;
        this.instance = instance;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void traverse(Visitor visitor){
        Field[] fields = instance.getClass().getDeclaredFields();

        for(int i = 0; i < fields.length; i++){
            fields[i].setAccessible(true);

            if (Modifier.isStatic(fields[i].getModifiers()))
                continue;

            Class<?> type = fields[i].getType();
            if (type.isPrimitive()){
                new PrimitiveVisitedElement(fields[i], instance).accept(visitor);
            }
            //< ??? array
            //< ??? object
            //< ??? collections

            if (i < fields.length - 1){
                ((VisitorImpl)visitor).addDelimiter();
            }
        }
    }
}

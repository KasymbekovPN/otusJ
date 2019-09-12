package ru.otus.kasymbekovPN.HW08.javaObjectWriter;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Optional;

public class ObjectVisitedElement implements VisitedElement {

    private Field field;
    private Object instance;

    ObjectVisitedElement(Field field, Object instance) {
        this.field = field;
        this.instance = instance;
    }

    @Override
    public void accept(Visitor visitor) throws IllegalAccessException {
        visitor.visit(this);
    }

    void traverse(Visitor visitor) throws IllegalAccessException {
        if (instance != null){
            Field[] fields = instance.getClass().getDeclaredFields();

            for(int i = 0; i < fields.length; i++){
                fields[i].setAccessible(true);

                if (Modifier.isStatic(fields[i].getModifiers()))
                    continue;

                Class<?> type = fields[i].getType();
                if (type.isPrimitive()){
                    new PrimitiveVisitedElement(fields[i], instance).accept(visitor);
                } else if (type.isArray()) {
                    //<
                    System.out.println(fields[i]);
                    System.out.println(fields[i].getName());
                    //<
                } else {
                    new ObjectVisitedElement(fields[i], fields[i].get(instance)).accept(visitor);
                }
                //< ??? array
                //< ??? collections

                if (i < fields.length - 1){
                    ((VisitorImpl)visitor).addDelimiter();
                }
            }
        }
    }

    Optional<String> getFieldName(){
        Optional<String> res = Optional.empty();
        if (field != null){
            res = Optional.of(field.getName());
        }

        return res;
    }

    boolean instanceNotNull(){
        return instance != null;
    }
}

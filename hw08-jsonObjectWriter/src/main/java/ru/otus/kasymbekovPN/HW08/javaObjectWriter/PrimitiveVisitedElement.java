package ru.otus.kasymbekovPN.HW08.javaObjectWriter;

import java.lang.reflect.Field;

public class PrimitiveVisitedElement implements VisitedElement {

    private Field field;
    private Object instance;

    public PrimitiveVisitedElement(Field field, Object instance) {
        this.field = field;
        this.instance = instance;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public String getName(){
        return field.getName();
    }

    public Class<?> getType(){
        return field.getType();
    }

    public void test(){

        //<
        System.out.println("*-------");

        try {
            Object o = field.get(instance);
            System.out.println(o);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public Field get(){
        return field;
    }


}

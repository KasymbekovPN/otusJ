package ru.otus.kasymbekovPN.HW08.javaObjectWriter;

import java.lang.reflect.Field;

public class VisitorImpl implements Visitor {



    @Override
    public void visit(PrimitiveVisitedElement primitiveVisitedElement){

        //<
//        System.out.println(primitiveVisitedElement.getName());
//        System.out.println(primitiveVisitedElement.getType());
//        System.out.println(primitiveVisitedElement.get());
//        System.out.println(" ");

        String name = primitiveVisitedElement.getName();
        Class<?> type = primitiveVisitedElement.getType();
        Field field = primitiveVisitedElement.get();

        primitiveVisitedElement.test();

//        Object value = field.get

//        try {
//            final int anInt = field.getInt(new Object());
//            System.out.println(anInt);
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
    }
}

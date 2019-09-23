package ru.otus.kasymbekovPN.HW09;

import java.lang.annotation.Annotation;

public class VisitorImpl implements Visitor {

    //< должен возвращать QueryChunk - ключ
    //< List<QueryChunk> - поля

    private Class annotationKey;

    public VisitorImpl(Class annotationKey) {
        this.annotationKey = annotationKey;
    }

    @Override
    public void visit(PrimitiveVE primitiveVE) {
        //<
        System.out.println("name : " + primitiveVE.getName());
        System.out.println("type : " + primitiveVE.getType());
        System.out.println("bad : " + primitiveVE.isBadType());
        var annotations = primitiveVE.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println("an : " + annotation);
        }
        System.out.println("---------");
        //<
    }

    @Override
    public void visit(StringVE stringVE) {
        //<
        System.out.println("name : " + stringVE.getName());
        System.out.println("type : " + stringVE.getType());
        var annotations = stringVE.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println("an : " + annotation);
        }
        System.out.println(" ");
        //<
    }
}

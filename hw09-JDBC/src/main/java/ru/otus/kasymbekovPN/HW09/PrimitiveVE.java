package ru.otus.kasymbekovPN.HW09;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class PrimitiveVE implements VisitedElement, VisitedElementData {
    private Field field;
    private String type;
    private boolean badType;

    public PrimitiveVE(Field field, Object instance) {
        this.field = field;
        this.badType = false;
        this.type = makeType(instance);
    }

    private String makeType(Object instance) {
        String res = "";
        var instanceType = instance.getClass();
        if (Integer.class.isAssignableFrom(instanceType)) {
            res = "INT";
        } else if (Long.class.isAssignableFrom(instanceType)){
            res = "LONG";
        } else {
            badType = true;
        }

        return res;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String getName() {
        return field.getName();
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public boolean isAnnotationPresent(Class annotation) {
        return field.isAnnotationPresent(annotation);
    }

    public boolean isBadType() {
        return badType;
    }
}

package ru.otus.kasymbekovPN.HW09;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class PrimitiveVE implements VisitedElement {
    private String type;
    private String name;
    private Annotation[] annotations;
    private boolean badType;

    public PrimitiveVE(Field field, Object instance) {
        this.name = field.getName();
        this.annotations = field.getAnnotations();
        this.badType = false;
        this.type = makeType(instance);
    }

    private String makeType(Object instance) {
        String res = "";
        var instanceType = instance.getClass();
        if (Integer.class.isAssignableFrom(instanceType)) {
            res = "int";
        } else if (Long.class.isAssignableFrom(instanceType)){
            res = "long";
        } else {
            badType = true;
        }

        return res;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public String getName() {
        return name;
    }

    public Annotation[] getAnnotations() {
        return annotations;
    }

    public String getType() {
        return type;
    }

    public boolean isBadType() {
        return badType;
    }
}

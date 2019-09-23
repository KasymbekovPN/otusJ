package ru.otus.kasymbekovPN.HW09;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class StringVE implements VisitedElement {

    static final private String type = "varchar(255)";

    private String name;
    private Annotation[] annotations;

    public StringVE(Field field, Object instance) {
        this.name = field.getName();
        this.annotations = field.getAnnotations();
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
}

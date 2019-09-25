package ru.otus.kasymbekovPN.HW09;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class StringVE implements VisitedElement, VisitedElementData {

    static final private String type = "VARCHAR(255)";

    private Field field;

    public StringVE(Field field, Object instance) {
        this.field = field;
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
}

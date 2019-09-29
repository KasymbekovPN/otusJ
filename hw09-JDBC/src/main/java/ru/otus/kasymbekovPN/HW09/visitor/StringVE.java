package ru.otus.kasymbekovPN.HW09.visitor;

import ru.otus.kasymbekovPN.HW09.visitor.VisitedElement;
import ru.otus.kasymbekovPN.HW09.visitor.VisitedElementData;
import ru.otus.kasymbekovPN.HW09.visitor.Visitor;

import java.lang.reflect.Field;

public class StringVE implements VisitedElement, VisitedElementData {

//    static final private String type = "VARCHAR(255)";

    private Field field;
    private Object instance;

    public StringVE(Field field, Object instance) {
        this.field = field;
        this.instance = instance;
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
    public Object getInstance() {
        return instance;
    }

    //    @Override
//    public String getType() {
//        return type;
//    }

    @Override
    public boolean isAnnotationPresent(Class annotation) {
        return field.isAnnotationPresent(annotation);
    }
}

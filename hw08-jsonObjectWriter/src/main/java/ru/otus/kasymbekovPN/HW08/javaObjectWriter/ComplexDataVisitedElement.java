package ru.otus.kasymbekovPN.HW08.javaObjectWriter;

import java.lang.reflect.Field;
import java.util.Optional;

class ComplexDataVisitedElement {

    protected Field field;
    protected Object instance;

    ComplexDataVisitedElement(Field field, Object instance) {
        this.field = field;
        this.instance = instance;
    }

    boolean addDelimiter(boolean first, Visitor visitor){
        if (!first)
            ((VisitorImpl)visitor).addDelimiter();
        return false;
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

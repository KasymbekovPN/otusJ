package ru.otus.kasymbekovPN.HW08.javaObjectWriter;

import java.util.Optional;

public class ArPrimitiveVisitedElement implements VisitedElement {

    private Object instance;

    public ArPrimitiveVisitedElement(Object instance) {
        this.instance = instance;
    }

    @Override
    public void accept(Visitor visitor) throws IllegalAccessException {
        visitor.visit(this);
    }

    public Optional<String> getLine(){
        Optional<String> res = Optional.empty();

        if (instance.getClass().equals(Character.class)){
            char ch = (Character) instance;
            res = Optional.of(String.valueOf((int)ch));
        } else {
            res = Optional.of(instance.toString());
        }

        return res;
    }

}

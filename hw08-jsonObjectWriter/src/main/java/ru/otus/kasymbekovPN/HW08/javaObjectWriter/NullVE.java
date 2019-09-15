package ru.otus.kasymbekovPN.HW08.javaObjectWriter;

public class NullVE implements VisitedElement {

    @Override
    public void accept(Visitor visitor) throws IllegalAccessException, NoSuchFieldException {
        visitor.visit(this);
    }
}

package ru.otus.kasymbekovPN.HW08.javaObjectWriter;

public interface VisitedElement {
    void accept(Visitor visitor) throws IllegalAccessException, NoSuchFieldException;
}

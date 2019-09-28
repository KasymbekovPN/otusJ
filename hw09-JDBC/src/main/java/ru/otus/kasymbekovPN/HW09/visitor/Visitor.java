package ru.otus.kasymbekovPN.HW09.visitor;

public interface Visitor {
    void visit(PrimitiveVE primitiveVE);
    void visit(StringVE stringVE);
}

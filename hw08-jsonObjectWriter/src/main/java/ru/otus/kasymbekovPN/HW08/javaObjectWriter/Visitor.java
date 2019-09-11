package ru.otus.kasymbekovPN.HW08.javaObjectWriter;

public interface Visitor {
    void visit(PrimitiveVisitedElement primitiveVisitedElement);
    void visit(ObjectVisitedElement objectVisitedElement);
}

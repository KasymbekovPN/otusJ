package ru.otus.kasymbekovPN.HW08.javaObjectWriter;

public interface Visitor {
    void visit(PrimitiveVisitedElement primitiveVisitedElement);
    void visit(ObjectVisitedElement objectVisitedElement) throws IllegalAccessException;
    void visit(ArrayVisitedElement arrayVisitedElement) throws IllegalAccessException;
    void visit(ArPrimitiveVisitedElement arPrimitiveVisitedElement);
    void visit(CollectionVisitedElement collectionVisitedElement) throws IllegalAccessException;
}
